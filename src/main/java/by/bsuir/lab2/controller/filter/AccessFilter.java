package by.bsuir.lab2.controller.filter;

import by.bsuir.lab2.bean.EnumRole;
import by.bsuir.lab2.bean.dto.UserDTO;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.SessionAttribute;
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
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        EnumRole role = ((UserDTO) session.getAttribute(SessionAttribute.USER_DTO)).getRole();
        if (role == null) {
            httpServletResponse.sendRedirect(httpRequest.getContextPath() + CommandName.GO_TO_HOME_COMMAND);
            return;
        }
        switch (role) {
            case ADMIN -> chain.doFilter(request, response);
            case USER -> httpServletResponse.sendRedirect(httpRequest.getContextPath() + CommandName.GO_TO_HOME_COMMAND);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
