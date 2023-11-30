package by.bsuir.lab2.controller.command.impl.admin;

import by.bsuir.lab2.bean.dto.GetUserDTO;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.UrlConstants;
import by.bsuir.lab2.controller.util.UrlUtil;
import by.bsuir.lab2.service.RoleService;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GetUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetUserCommand.class);
    private static final String USER_ID_PARAM = "id";
    private static final String LIST_ROLES_ATTR = "availableRoles";
    private static final String USER_ATTR = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter(USER_ID_PARAM));
            UserService userService = ServiceFactory.getInstance().getUserService();
            GetUserDTO user = userService.getUser(userId);
            request.setAttribute(USER_ATTR, user);
            RoleService roleService = ServiceFactory.getInstance().getRoleService();
            request.setAttribute(LIST_ROLES_ATTR, roleService.getRoles());
            request.getRequestDispatcher(UrlConstants.COMMON_PAGES_PATH + UrlConstants.ADMIN_EDIT_FORM_JSP).forward(request, response);
        } catch (NumberFormatException e){
            logger.error("Invalid query parameter", e);
            response.sendRedirect(UrlUtil.getRefererUrl(request));
        } catch (ServiceException e) {
            logger.error("Unexpected error happened during show user by id", e);
            response.sendRedirect(request.getContextPath() + CommandName.GO_TO_ERROR_500_COMMAND);
        }
    }
}
