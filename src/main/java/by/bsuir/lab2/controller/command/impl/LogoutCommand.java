package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import by.bsuir.lab2.controller.util.UrlUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static by.bsuir.lab2.controller.constant.CommandName.GO_TO_HOME_COMMAND;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USER_DTO);
        Map<String, String[]> parameters = UrlUtil.getQueryParameters(UrlUtil.getRefererUri(request));
        String appendParameters = "";
        if (parameters != null) {
            String[] langParameterValue = parameters.get("lang");
            if (langParameterValue.length > 0) {
                appendParameters = "?lang=" + langParameterValue[0];
            }
        }
        response.sendRedirect(request.getContextPath() + GO_TO_HOME_COMMAND + appendParameters);
    }
}
