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
import by.epamtc.jwd.auth.web.util.impl.go_to.GoToContactsCommand;
import by.epamtc.jwd.auth.web.util.impl.go_to.GoToLoginCommand;
import by.epamtc.jwd.auth.web.util.impl.go_to.GoToNewsCommand;
import by.epamtc.jwd.auth.web.util.impl.go_to.GoToPatientsCommand;
import by.epamtc.jwd.auth.web.util.impl.go_to.GoToProfileCommand;
import by.epamtc.jwd.auth.web.util.impl.go_to.GoToRegisterCommand;
import by.epamtc.jwd.auth.web.util.impl.go_to.GoToStaffCommand;

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
