package com.osiris.api.controllers.backoffice;

import com.osiris.api.BaseIntegrationTest;
import com.osiris.api.enums.UserRolesEnum;
import com.osiris.api.testutils.builders.JwtBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.osiris.api.enums.UserRolesEnum.BACK_OFFICE_USER;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created By francislagueu on 5/17/24
 */
public class AuthorizationBackOfficeControllerTest extends BaseIntegrationTest {
    private final String URL = BackOfficeController.BASE_URL + "/hello-world";

    @BeforeAll
    void init() {}

    @Test
    void return_401_IfNoJwtPassed() throws Exception {
        this.mockMvc
                .perform(get(this.URL).with(authentication(null)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void return_401_IfInvalidRole() throws Exception {
        this.mockMvc
                .perform(get(this.URL).with(authentication(JwtBuilder.jwt(random(), random()))))
                .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @EnumSource(
            value = UserRolesEnum.class,
            names = {"BACK_OFFICE_USER", "BACK_OFFICE_ADMIN"},
            mode = EnumSource.Mode.EXCLUDE)
    void return_401_IfNotAValidRole(final UserRolesEnum role) throws Exception {
        this.mockMvc
                .perform(get(this.URL).with(authentication(JwtBuilder.jwt(random(), role))))
                .andExpect(status().isForbidden());
    }

    @Test
    void return_200() throws Exception {
        this.mockMvc
                .perform(get(this.URL).with(authentication(JwtBuilder.jwt(random(), BACK_OFFICE_USER))))
                .andExpect(status().isOk());
    }
}
