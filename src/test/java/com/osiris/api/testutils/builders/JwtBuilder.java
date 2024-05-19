package com.osiris.api.testutils.builders;

import com.osiris.api.enums.UserRolesEnum;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Map;

import static com.osiris.api.constants.JWTClaims.CLAIM_REALM_ACCESS;
import static com.osiris.api.constants.JWTClaims.CLAIM_ROLES;

/**
 * Created By francislagueu on 5/17/24
 */
@UtilityClass
public class JwtBuilder {
    public static JwtAuthenticationToken jwt(final String companySlug, final UserRolesEnum role) {
        return jwt(companySlug, role.getName());
    }

    public static JwtAuthenticationToken jwt(final String companySlug, final String role) {

        final var authority = AuthorityUtils.createAuthorityList("ROLE_" + role);
        final var roleClaim =
                Map.of(
                        CLAIM_REALM_ACCESS,
                        Map.of(CLAIM_ROLES, List.of(StringUtils.isNotBlank(role) ? role : StringUtils.EMPTY)));

        final var jwt =
                Jwt.withTokenValue("token")
                        .header("alg", "none")
                        .claim("scope", "read")
                        .claim("company_slug", companySlug)
                        .claim("realms", roleClaim)
                        .build();

        return new JwtAuthenticationToken(jwt, authority);
    }
}
