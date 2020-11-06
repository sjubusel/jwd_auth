package by.epamtc.jwd.auth.service.ajax.impl;

import by.epamtc.jwd.auth.dao.ajax.AjaxDaoFactory;
import by.epamtc.jwd.auth.dao.ajax.AjaxFetchDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.ajax.AjaxCountry;
import by.epamtc.jwd.auth.service.ajax.AjaxFetchService;
import by.epamtc.jwd.auth.service.ajax.validation.AjaxValidator;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public class DefaultAjaxFetchService implements AjaxFetchService {
    private AjaxDaoFactory ajaxDaoFactory = AjaxDaoFactory.getInstance();
    private AjaxFetchDao ajaxFetchDao = ajaxDaoFactory.getAjaxFetchDao();
    private AjaxValidator ajaxValidator = new AjaxValidator();


    @Override
    public List<AjaxCountry> fetchCountries(String countryElement)
            throws ServiceException {
        if (ajaxValidator.isFetchInputValid(countryElement)) {
            try {
                return ajaxFetchDao.fetchCountries(countryElement);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }
}
