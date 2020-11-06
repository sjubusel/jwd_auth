package by.epamtc.jwd.auth.service.ajax.impl;

import by.epamtc.jwd.auth.dao.ajax.AjaxDaoFactory;
import by.epamtc.jwd.auth.service.ajax.AjaxFetchService;
import by.epamtc.jwd.auth.service.ajax.validation.AjaxValidator;

import java.util.List;

public class DefaultAjaxFetchService implements AjaxFetchService {
    private AjaxDaoFactory ajaxDaoFactory = AjaxDaoFactory.getInstance();
    private AjaxValidator ajaxValidator = new AjaxValidator();


    @Override
    public List<String> fetchCountries(String countryElement) {

        return null;
    }
}
