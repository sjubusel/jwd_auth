package by.epamtc.jwd.auth.dao.ajax;

import by.epamtc.jwd.auth.dao.exception.DaoException;

import java.util.List;

public interface AjaxFetchDao {
    List<String> fetchCountries(String countryPart) throws DaoException;
}
