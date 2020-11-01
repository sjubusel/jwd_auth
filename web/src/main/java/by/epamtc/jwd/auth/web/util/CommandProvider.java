package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.model.constant.CommandName;
import by.epamtc.jwd.auth.web.util.impl.ChangeLanguageCommand;
import by.epamtc.jwd.auth.web.util.impl.ErrorCommand;
import by.epamtc.jwd.auth.web.util.impl.LogOutCommand;
import by.epamtc.jwd.auth.web.util.impl.LoginCommand;
import by.epamtc.jwd.auth.web.util.impl.MainCommand;
import by.epamtc.jwd.auth.web.util.impl.RegisterCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToAboutUsCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToContactsCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToLoginCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToNewsCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToPatientsCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToProfileCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToRegisterCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToStaffCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileAllergicReactionsCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangeEmailCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangePasswordCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangePatientInformationCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangePhotoCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangeStaffInformationCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangeStaffPhotoCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileExtremelyHazardousDiseasesCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileStaffHistoryCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileStaffInformationCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CommandProvider {
    private static volatile CommandProvider instance;

    private final HashMap<String, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.MAIN, new MainCommand());
        repository.put(CommandName.GO_TO_LOGIN, new GoToLoginCommand());
        repository.put(CommandName.LOGIN, new LoginCommand());
        repository.put(CommandName.LOGOUT, new LogOutCommand());
        repository.put(CommandName.GO_TO_REGISTER, new GoToRegisterCommand());
        repository.put(CommandName.REGISTER, new RegisterCommand());
        repository.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        repository.put(CommandName.GO_TO_PROFILE, new GoToProfileCommand());
        repository.put(CommandName.GO_TO_ABOUT_US, new GoToAboutUsCommand());
        repository.put(CommandName.GO_TO_CONTACTS, new GoToContactsCommand());
        repository.put(CommandName.GO_TO_NEWS, new GoToNewsCommand());
        repository.put(CommandName.GO_TO_PATIENTS, new GoToPatientsCommand());
        repository.put(CommandName.GO_TO_STAFF, new GoToStaffCommand());

        repository.put("GO-TO-PROFILE-ALLERGIC-REACTIONS",
                new GoToProfileAllergicReactionsCommand());
        repository.put("GO-TO-PROFILE-EMAIL-CHANGE",
                new GoToProfileChangeEmailCommand());
        repository.put("GO-TO-PROFILE-CHANGE-PASSWORD",
                new GoToProfileChangePasswordCommand());
        repository.put("GO-TO-PROFILE-CHANGE-PATIENT-INFO",
                new GoToProfileChangePatientInformationCommand());
        repository.put("GO-TO-PROFILE-CHANGE-PHOTO",
                new GoToProfileChangePhotoCommand());
        repository.put("GO-TO-PROFILE-CHANGE-STAFF-INFO",
                new GoToProfileChangeStaffInformationCommand());
        repository.put("GO-TO-PROFILE-CHANGE-STAFF-PHOTO",
                new GoToProfileChangeStaffPhotoCommand());
        repository.put("GO-TO-PROFILE-EXTREMELY-HAZARDOUS-DISEASES",
                new GoToProfileExtremelyHazardousDiseasesCommand());
        repository.put("GO-TO-PROFILE-STAFF-HISTORY",
                new GoToProfileStaffHistoryCommand());
        repository.put("GO-TO-PROFILE-STAFF-INFO",
                new GoToProfileStaffInformationCommand());

        repository.put(CommandName.ERROR, new ErrorCommand());
    }

    public static CommandProvider getInstance() {
        CommandProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandProvider();
                }
            }
        }
        return localInstance;
    }

    public void execute(String commandName, HttpServletRequest req,
            HttpServletResponse res) throws ServletException, IOException {
        Command command = repository.get(commandName);
        if (command == null) {
            command = repository.get(CommandName.ERROR);
        }
        command.execute(req, res);
    }
}
