package by.bsuir.lab2.controller.constant;

public class CommandName {

    private CommandName() { }
    public static final String GO_TO_LOGIN_PAGE_COMMAND = "/login";

    public static final String GO_TO_REGISTRATION_PAGE_COMMAND = "/register";

    public static final String REGISTER_COMMAND = "/actions/register";
    
    public static final String LOGIN_COMMAND = "/actions/login";
    
    public static final String LOGOUT_COMMAND = "/actions/logout";

    public static final String EDIT_USER_COMMAND = "/actions/user/edit";

    public static final String GO_TO_DEFAULT_COMMAND = "/";

    public static final String GO_TO_HOME_COMMAND = "/";

    public static final String GO_TO_ERROR_500_COMMAND = "/error500";

    public static final String GO_TO_ERROR_404_COMMAND = "/error404";

    //ADMIN

    public static final String LIST_USERS_COMMAND = "/admin/users";
    public static final String GO_TO_ADMIN_USER_COMMAND = "/admin/user";
}
