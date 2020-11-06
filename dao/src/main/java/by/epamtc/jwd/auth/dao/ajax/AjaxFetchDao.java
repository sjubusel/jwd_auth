package by.epamtc.jwd.auth.dao.ajax;

import java.util.List;

public interface AjaxFetchDao {
    List<String> fetchCountries(String countryPart);
}
