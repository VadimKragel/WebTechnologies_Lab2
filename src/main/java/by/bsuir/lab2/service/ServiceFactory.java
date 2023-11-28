package by.bsuir.lab2.service;

import by.bsuir.lab2.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    
    private static final UserService userService = new UserServiceImpl();
    
    private ServiceFactory() {
    }
    
    public static ServiceFactory getInstance() {
        return instance;
    }
    
    public UserService getUserService() {
        return userService;
    }
}
