package by.bsuir.lab2.controller.command.impl.admin;

import by.bsuir.lab2.bean.dto.UserDTO;
import by.bsuir.lab2.controller.command.Command;
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

public class ShowUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowUserCommand.class);
    private static final String USER_ID_PARAM = "userId";
    private static final String USER_PARAM = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter(USER_ID_PARAM));
        String urlPath;
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            UserDTO user = userService.getUser(userID);
            request.setAttribute(USER_PARAM, user);
            urlPath = UrlConstants.COMMON_PAGES_PATH + UrlConstants.FORWARD_EDIT_USER_FORM;
        } catch (ServiceException e) {
            logger.error("Unexpected error happened during show user by id", e);
            urlPath = UrlConstants.COMMON_PAGES_PATH + UrlConstants.REDIRECT_503;
        }
        request.getRequestDispatcher(urlPath).forward(request, response);
    }
}
