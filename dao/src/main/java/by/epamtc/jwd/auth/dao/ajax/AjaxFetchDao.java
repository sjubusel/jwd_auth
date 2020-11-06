package by.epamtc.jwd.auth.dao.ajax;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.ajax.AjaxCountry;

import java.util.List;

public interface AjaxFetchDao {
    List<AjaxCountry> fetchCountries(String countryPart) throws DaoException;
}
