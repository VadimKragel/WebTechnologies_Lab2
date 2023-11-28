package by.bsuir.lab2.controller.util;

import by.bsuir.lab2.controller.constant.SessionAttribute;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    private SessionUtil() {
    }
    public static boolean isAuthenticatedUser(HttpSession session) {
        return session.getAttribute(SessionAttribute.USER_DTO) != null;
    }
}
