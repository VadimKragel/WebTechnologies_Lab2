package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.dto.RegisterUserDTO;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.util.UrlUtil;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.bsuir.lab2.controller.constant.ApplicationAttribute.IS_REGISTER_ERROR;

public class RegisterCommand implements Command {
    public static final Logger logger = LogManager.getLogger(RegisterCommand.class);
    private static final String EMAIL_PARAM = "email";
    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterUserDTO registerUserDTO = getRegisterUserDTO(request);
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            int userID = userService.register(registerUserDTO);
            request.getServletContext().setAttribute(IS_REGISTER_ERROR, false);
            response.sendRedirect(UrlUtil.getRefererUrl(request));
        } catch (ValidationException e) {
            logger.warn("Invalid user data for registration, validation failed!", e);
            request.getServletContext().setAttribute(IS_REGISTER_ERROR, true);
            response.sendRedirect(UrlUtil.getRefererUrl(request));
        } catch (ServiceException e) {
            logger.error("Unexpected error happened during registration. Registration is cancelled!", e);
            response.sendRedirect(request.getContextPath() + CommandName.GO_TO_ERROR_500_COMMAND);
        }
    }

    private RegisterUserDTO getRegisterUserDTO(HttpServletRequest request) {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUsername(request.getParameter(USERNAME_PARAM));
        registerUserDTO.setEmail(request.getParameter(EMAIL_PARAM));
        registerUserDTO.setPassword(request.getParameter(PASSWORD_PARAM));
        return registerUserDTO;
    }
}
