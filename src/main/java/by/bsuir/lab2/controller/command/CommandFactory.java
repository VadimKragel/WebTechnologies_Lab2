package by.bsuir.lab2.controller.command;

import by.bsuir.lab2.controller.command.impl.GoToCommand;
import by.bsuir.lab2.controller.command.impl.LoginCommand;
import by.bsuir.lab2.controller.command.impl.LogoutCommand;
import by.bsuir.lab2.controller.command.impl.RegisterCommand;
import by.bsuir.lab2.controller.command.impl.admin.ShowUserCommand;
import by.bsuir.lab2.controller.command.impl.admin.ListUsersCommand;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static by.bsuir.lab2.controller.constant.CommandName.*;
import static by.bsuir.lab2.controller.constant.UrlConstants.*;


public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();
    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {
        commands.put(GO_TO_LOGIN_PAGE_COMMAND, new GoToCommand(LOGIN_JSP));
        commands.put(GO_TO_REGISTRATION_PAGE_COMMAND, new GoToCommand(REGISTRATION_JSP));
        commands.put(DEFAULT_COMMAND, new GoToCommand(HOME_JSP));
        commands.put(GO_TO_ERROR_503_COMMAND, new GoToCommand(REDIRECT_503));
        commands.put(GO_TO_ERROR_404_COMMAND, new GoToCommand(REDIRECT_404));
        commands.put(REGISTER_COMMAND, new RegisterCommand());
        commands.put(LOGIN_COMMAND, new LoginCommand());
        commands.put(LOGOUT_COMMAND, new LogoutCommand());
        commands.put(GO_TO_USERS_EDITOR_COMMAND, new ListUsersCommand());
        commands.put(SHOW_USER_COMMAND, new ShowUserCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String commandName = request.getServletPath();
        Command command = commands.get(commandName);
        if (null == command) {
            return commands.get(GO_TO_ERROR_404_COMMAND);
        }
        return command;
    }

    public static CommandFactory getInstance() {
        return instance;
    }
}
