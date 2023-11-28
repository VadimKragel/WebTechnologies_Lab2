package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.UrlConstants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToCommand implements Command {
    private final String page;

    public GoToCommand(String page) {
        this.page = page;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(UrlConstants.COMMON_PAGES_PATH + page);
        dispatcher.forward(request, response);
    }
}
