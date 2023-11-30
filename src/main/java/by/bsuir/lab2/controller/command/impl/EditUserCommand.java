package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.dto.GetUserDTO;
import by.bsuir.lab2.bean.dto.UpdateUserDTO;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import by.bsuir.lab2.controller.constant.UrlConstants;
import by.bsuir.lab2.controller.data.UpdateState;
import by.bsuir.lab2.controller.util.UrlUtil;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.DuplicateUniqueException;
import by.bsuir.lab2.service.exception.LastAdminException;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;

import static by.bsuir.lab2.controller.constant.ApplicationAttribute.UPDATE_USER_STATE;

public class EditUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final String USER_ID_PARAM = "id";
    private static final String USER_ROLE_PARAM = "roleId";
    private static final String USER_USERNAME_PARAM = "username";
    private static final String USER_EMAIL_PARAM = "email";
    private static final String USER_NAME_PARAM = "name";
    private static final String USER_SURNAME_PARAM = "surname";
    private static final String USER_PATRONYMIC_PARAM = "patronymic";
    private static final String USER_BIRTHDATE_PARAM = "birthDate";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UpdateUserDTO updateUserDTO = new UpdateUserDTO();
            updateUserDTO.setId(Integer.parseInt(request.getParameter(USER_ID_PARAM)));
            updateUserDTO.setRoleId(Integer.parseInt(request.getParameter(USER_ROLE_PARAM)));
            updateUserDTO.setUsername(request.getParameter(USER_USERNAME_PARAM));
            updateUserDTO.setEmail(request.getParameter(USER_EMAIL_PARAM));
            updateUserDTO.setName(request.getParameter(USER_NAME_PARAM));
            updateUserDTO.setSurname(request.getParameter(USER_SURNAME_PARAM));
            updateUserDTO.setPatronymic(request.getParameter(USER_PATRONYMIC_PARAM));
            updateUserDTO.setBirthDate(getDateFromParameter(request.getParameter(USER_BIRTHDATE_PARAM)));
            UserService userService = ServiceFactory.getInstance().getUserService();
            boolean updated = userService.updateUser(updateUserDTO);
            HttpSession session = request.getSession();
            if (updated && updateUserDTO.getId() == getIdFromSession(session)) {
                response.sendRedirect(request.getContextPath() + CommandName.LOGOUT_COMMAND);
            } else {
                request.getServletContext().setAttribute(UPDATE_USER_STATE, updated ? UpdateState.UPDATED : UpdateState.NOT_CHANGED);
                response.sendRedirect(UrlUtil.getRefererUrl(request));
            }
        } catch (IllegalArgumentException e) {
            logger.error("Invalid query parameter for update", e);
            request.getRequestDispatcher(UrlConstants.COMMON_PAGES_PATH + UrlConstants.ERROR_404_JSP).forward(request, response);
        } catch (ValidationException | DuplicateUniqueException e) {
            logger.warn("Invalid user data for update.", e);
            request.getServletContext().setAttribute(UPDATE_USER_STATE, UpdateState.ERROR);
            response.sendRedirect(UrlUtil.getRefererUrl(request));
        } catch (LastAdminException e) {
            logger.warn("Must be at least one administrator.", e);
            request.getServletContext().setAttribute(UPDATE_USER_STATE, UpdateState.LAST_ADMIN);
            response.sendRedirect(UrlUtil.getRefererUrl(request));
        } catch (ServiceException e) {
            logger.error("Unexpected error happened during update user", e);
            response.sendRedirect(request.getContextPath() + CommandName.GO_TO_ERROR_500_COMMAND);
        }
    }

    private Date getDateFromParameter(String parameterValue) throws IllegalArgumentException {
        if (parameterValue.isBlank()) {
            return null;
        }
        return Date.valueOf(parameterValue);
    }

    private int getIdFromSession(HttpSession session) {
        GetUserDTO sessionUser = (GetUserDTO) session.getAttribute(SessionAttribute.USER_DTO);
        if (sessionUser != null) {
            return sessionUser.getId();
        }
        return 0;
    }
}
