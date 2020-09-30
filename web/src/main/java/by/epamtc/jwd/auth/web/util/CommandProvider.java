package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.web.util.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CommandProvider {
    private static volatile CommandProvider instance;

    private final HashMap<String, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put("MAIN", new MainCommand());
        repository.put("GO-TO-LOGIN", new GoToLoginCommand());
        repository.put("LOGIN", new LoginCommand());
        repository.put("LOGOUT", new LogOutCommand());
        repository.put("CHANGE-LANGUAGE", new ChangeLanguageCommand());
        repository.put("ERROR", new ErrorCommand());
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
            command = repository.get("ERROR");
        }
        command.execute(req, res);
    }
}
