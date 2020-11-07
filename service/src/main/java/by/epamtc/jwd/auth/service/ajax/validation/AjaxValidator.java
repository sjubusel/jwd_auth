package by.epamtc.jwd.auth.service.ajax.validation;

import by.epamtc.jwd.auth.service.exception.ServiceException;

public class AjaxValidator {
    public boolean isFetchInputValid(String fetchInput) {
        return fetchInput.matches("[А-Яа-яЁё \\-]{1,255}");
    }

    public boolean isInputValidForDependentFetch(String countryId,
            String regionInput) throws ServiceException {
        try {
            Integer.parseInt(countryId);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong country id was generated " +
                    "on a client-side by JS");
        }
        return regionInput.matches("[А-Яа-яЁё \\-]{1,255}");
    }
}
