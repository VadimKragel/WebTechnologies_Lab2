package by.bsuir.lab2.service;

import by.bsuir.lab2.service.impl.RoleServiceImpl;
import by.bsuir.lab2.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    
    private static final UserService userService = new UserServiceImpl();

    private static final RoleService roleService = new RoleServiceImpl();

    private ServiceFactory() {
    }
    
    public static ServiceFactory getInstance() {
        return instance;
    }
    
    public UserService getUserService() {
        return userService;
    }

    public RoleService getRoleService(){
        return roleService;
    }
}
