package by.epamtc.jwd.auth.service.ajax;

import by.epamtc.jwd.auth.model.ajax.AjaxCountry;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public interface AjaxFetchService {
    List<AjaxCountry> fetchCountries(String countryElement)
            throws ServiceException;
}
