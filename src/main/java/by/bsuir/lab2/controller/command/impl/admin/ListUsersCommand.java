package by.bsuir.lab2.controller.command.impl.admin;

import by.bsuir.lab2.bean.dto.UserDTO;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.UrlConstants;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class ListUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ListUsersCommand.class);
    private static final String LIST_USERS_PARAM = "listUsers";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPath;
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            List<UserDTO> usersDTO = userService.getUsers();
            request.setAttribute(LIST_USERS_PARAM, usersDTO);
            urlPath = UrlConstants.COMMON_PAGES_PATH + UrlConstants.FORWARD_USERS_EDITOR;
        } catch (ServiceException e) {
            logger.error("Unexpected error happened during get all users", e);
            response.sendRedirect(request.getContextPath() + CommandName.GO_TO_ERROR_503_COMMAND);
        }

    }
}
