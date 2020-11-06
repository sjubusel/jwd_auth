package by.epamtc.jwd.auth.web.ajax;

import by.epamtc.jwd.auth.model.ajax.AjaxCommandName;
import by.epamtc.jwd.auth.web.ajax.impl.FetchCountryInChangePatientInfoJspAjaxCommand;
import by.epamtc.jwd.auth.web.ajax.impl.FetchRegionInChangePatientInfoJspAjaxCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AjaxCommandProvider {
    private static volatile AjaxCommandProvider instance;

    private final HashMap<String, AjaxCommand> repository = new HashMap<>();

    private AjaxCommandProvider() {
        repository.put(AjaxCommandName.FETCH_COUNTRY_IN_CHANGE_PATIENT_INFO_JSP,
                new FetchCountryInChangePatientInfoJspAjaxCommand());
        repository.put(AjaxCommandName.FETCH_REGION_IN_CHANGE_PATIENT_INFO_JSP,
                new FetchRegionInChangePatientInfoJspAjaxCommand());
    }

    public static AjaxCommandProvider getInstance() {
        AjaxCommandProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (AjaxCommandProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AjaxCommandProvider();
                }
            }
        }
        return localInstance;
    }

    public void execute(String commandName, HttpServletRequest req,
            HttpServletResponse res) throws ServletException, IOException {
        AjaxCommand command = repository.get(commandName);
        command.execute(req, res);
    }
}
