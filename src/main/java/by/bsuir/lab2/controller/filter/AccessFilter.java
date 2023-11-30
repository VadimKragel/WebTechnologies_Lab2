package by.bsuir.lab2.controller.filter;

import by.bsuir.lab2.bean.dto.GetUserDTO;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import by.bsuir.lab2.controller.data.EnumRole;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        EnumRole role = getRoleFromSession(session);
        if (role == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + CommandName.GO_TO_HOME_COMMAND);
            return;
        }
        switch (role) {
            case ADMIN -> chain.doFilter(request, response);
            default -> httpResponse.sendRedirect(httpRequest.getContextPath() + CommandName.GO_TO_HOME_COMMAND);
        }
    }

    private EnumRole getRoleFromSession(HttpSession session) {
        GetUserDTO sessionUser = (GetUserDTO) session.getAttribute(SessionAttribute.USER_DTO);
        if (sessionUser != null) {
            return EnumRole.fromId(sessionUser.getRole().getId());
        }
        return null;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
