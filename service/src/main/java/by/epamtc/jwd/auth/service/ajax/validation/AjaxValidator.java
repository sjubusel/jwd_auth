package by.epamtc.jwd.auth.service.ajax.validation;

public class AjaxValidator {
    public boolean isFetchInputValid(String fetchInput) {
        return fetchInput.matches("[А-Яа-яЁё \\-]{1,255}");
    }
}
