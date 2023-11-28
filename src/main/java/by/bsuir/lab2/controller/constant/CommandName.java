package by.bsuir.lab2.controller.constant;

public class CommandName {

    private CommandName() { }
    public static final String GO_TO_LOGIN_PAGE_COMMAND = "/login";

    public static final String GO_TO_REGISTRATION_PAGE_COMMAND = "/register";

    public static final String REGISTER_COMMAND = "/actions/register";
    
    public static final String LOGIN_COMMAND = "/actions/login";
    
    public static final String LOGOUT_COMMAND = "/actions/logout";

    public static final String DEFAULT_COMMAND = "/";

    public static final String GO_TO_HOME_COMMAND = "/";

    public static final String GO_TO_ERROR_503_COMMAND = "/error503";

    public static final String GO_TO_ERROR_404_COMMAND = "/error404";

    public static final String UNKNOWN_COMMAND = "/error404";
    
    //ADMIN

    public static final String GO_TO_USERS_EDITOR_COMMAND = "/admin/users";
    
    public static final String SHOW_USER_COMMAND = "/admin/users/show";
}
