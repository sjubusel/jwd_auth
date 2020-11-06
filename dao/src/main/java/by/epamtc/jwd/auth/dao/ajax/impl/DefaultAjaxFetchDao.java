package by.epamtc.jwd.auth.dao.ajax.impl;

import by.epamtc.jwd.auth.dao.ajax.AjaxFetchDao;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;

import java.util.List;

public class DefaultAjaxFetchDao implements AjaxFetchDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public List<String> fetchCountries(String countryPart) {

        return null;
    }
}
