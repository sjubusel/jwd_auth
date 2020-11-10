package by.epamtc.jwd.auth.service.ajax;

import by.epamtc.jwd.auth.model.ajax.*;
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

    List<AjaxRoad> fetchRoads(String settlementId, String roadInput)
            throws ServiceException;

    List<AjaxPerson> fetchPersons(String recipientPerson) throws ServiceException;
}
