package by.epamtc.jwd.auth.service.ajax_service.validation;

import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.service.exception.ServiceException;

public class AjaxValidator {
    public boolean isFetchInputValid(String fetchInput) {
        return fetchInput.matches(RegistrationInfoPattern.AJAX_INPUT_PATTERN);
    }

    public boolean isInputValidForDependentFetch(String countryId,
            String regionInput) throws ServiceException {
        try {
            Integer.parseInt(countryId);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong country id was generated " +
                    "on a client-side by JS");
        }
        return isFetchInputValid(regionInput);
    }
}
