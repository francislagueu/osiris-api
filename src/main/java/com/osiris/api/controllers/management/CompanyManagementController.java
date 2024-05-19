package com.osiris.api.controllers.management;

import com.osiris.api.constants.AppUrls;
import com.osiris.api.controllers.management.base.BaseManagementController;
import com.osiris.api.entities.Company;
import com.osiris.api.mappers.CompanyMapper;
import com.osiris.api.requests.management.CreateCompanyManagementRequest;
import com.osiris.api.requests.management.UpdateCompanyManagementRequest;
import com.osiris.api.responses.management.CompanyManagementResponse;
import com.osiris.api.services.CompanyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
@RestController
@RequestMapping(CompanyManagementController.BASE_URL)
@RequiredArgsConstructor
public class CompanyManagementController extends BaseManagementController<
        Company,
        CreateCompanyManagementRequest,
        UpdateCompanyManagementRequest,
        CompanyManagementResponse> {
    public static final String BASE_URL = AppUrls.MANAGEMENT + "/companies";

    @Getter
    private final CompanyService service;
    @Getter private final CompanyMapper mapper;
}
