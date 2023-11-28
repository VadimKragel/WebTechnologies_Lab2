package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.EnumRole;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.LoginUserDTO;
import by.bsuir.lab2.bean.dto.UserDTO;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import by.bsuir.lab2.controller.util.UrlUtil;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.bsuir.lab2.controller.constant.SessionAttribute.IS_LOGIN_ERROR;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginUserDTO loginUserDTO = getLoginUserDTO(request);
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.login(loginUserDTO);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttribute.USER_DTO, UserDTO.mapUser(user));
                request.getServletContext().setAttribute(IS_LOGIN_ERROR, false);
                response.sendRedirect(UrlUtil.getRefererUri(request));
            } else {
                request.getServletContext().setAttribute(IS_LOGIN_ERROR, true);
                response.sendRedirect(UrlUtil.getRefererUri(request));
            }
        } catch (ValidationException e) {
            logger.warn("Invalid user credentials for login.", e);
            request.getServletContext().setAttribute(IS_LOGIN_ERROR, true);
            response.sendRedirect(UrlUtil.getRefererUri(request));
        } catch (ServiceException e) {
            logger.error("Unexpected error happened during login. Login is cancelled!", e);
            response.sendRedirect(request.getContextPath() + CommandName.GO_TO_ERROR_503_COMMAND);
        }

    }

    private LoginUserDTO getLoginUserDTO(HttpServletRequest request) {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setLogin(request.getParameter(LOGIN_PARAM));
        loginUserDTO.setPassword(request.getParameter(PASSWORD_PARAM));
        return loginUserDTO;
    }
}
