package com.osiris.api.mappers;

import com.osiris.api.entities.Company;
import com.osiris.api.mappers.base.ManagementBaseMapper;
import com.osiris.api.requests.management.CreateCompanyManagementRequest;
import com.osiris.api.requests.management.UpdateCompanyManagementRequest;
import com.osiris.api.responses.management.CompanyManagementResponse;
import org.mapstruct.Mapper;

/**
 * Created By francislagueu on 5/13/24
 */
@Mapper(componentModel = "spring")
public interface CompanyMapper extends ManagementBaseMapper<
        Company,
        CreateCompanyManagementRequest,
        UpdateCompanyManagementRequest,
        CompanyManagementResponse> {
}
