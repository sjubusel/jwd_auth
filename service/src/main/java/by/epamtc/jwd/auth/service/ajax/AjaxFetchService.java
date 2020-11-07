package by.epamtc.jwd.auth.service.ajax;

import by.epamtc.jwd.auth.model.ajax.AjaxArea;
import by.epamtc.jwd.auth.model.ajax.AjaxCountry;
import by.epamtc.jwd.auth.model.ajax.AjaxRegion;
import by.epamtc.jwd.auth.model.ajax.AjaxSettlement;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public interface AjaxFetchService {
    List<AjaxCountry> fetchCountries(String countryElement)
            throws ServiceException;

    List<AjaxRegion> fetchRegions(String countryId, String regionInput)
            throws ServiceException;

    List<AjaxArea> fetchAreas(String regionId, String areaInput)
            throws ServiceException;

    List<AjaxSettlement> fetchSettlements(String areaId, String settlementInput)
            throws ServiceException;
}
