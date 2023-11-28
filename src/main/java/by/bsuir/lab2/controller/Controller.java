package by.bsuir.lab2.controller;

import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.command.CommandFactory;
import by.bsuir.lab2.dao.connection.ConnectionPool;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Controller extends HttpServlet {
    public static final Logger logger = LogManager.getLogger(Controller.class);
    private static final String DB_PROPERTIES_FILENAME = "db";
    public Controller() {
        super();
        try (InputStream dbPropertiesStream = Controller.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILENAME  + ".properties")) {
            Properties dbProperties = new Properties();
            dbProperties.load(dbPropertiesStream);
            ConnectionPool.getInstance().init(dbProperties);
        } catch (ConnectionPoolException | IOException e) {
            logger.fatal("Exception during initialization of connection pool!", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getInstance().getCommand(request);
        command.execute(request, response);
    }


}