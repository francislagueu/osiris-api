package com.osiris.api.requests.management;

import java.math.BigDecimal;

/**
 * Created By francislagueu on 5/13/24
 */
public record UpdateCompanyManagementRequest(
        String slug,
        String name,
        String officialName,
        String federalTaxId,
        String stateTaxId,
        String phone,
        String email,
        String addressStreet,
        String addressStreetNumber,
        String addressComplement,
        String addressCityDistrict,
        String addressPostCode,
        String addressCity,
        String addressStateCode,
        String addressCountry,
        BigDecimal addressLatitude,
        BigDecimal addressLongitude,
        Boolean isManagement,
        Boolean isInternal,
        Boolean isPlatform) {}
