package by.bsuir.lab2.service.validation;

import by.bsuir.lab2.bean.dto.LoginUserDTO;
import by.bsuir.lab2.bean.dto.RegisterUserDTO;
import by.bsuir.lab2.bean.dto.UpdateUserDTO;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=. {1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");

    public static boolean isEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean minLength(String value, int min) {
        return value.length() >= min;
    }

    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isRegistrationDataValid(RegisterUserDTO registerUserDTO) {
        String email = registerUserDTO.getEmail();
        String username = registerUserDTO.getUsername();
        String password = registerUserDTO.getPassword();

        if (isEmail(email)) {
            return false;
        }
        if (isNullOrBlank(username)) {
            return false;
        }
        if (isNullOrBlank(password) || !minLength(password, 7)) {
            return false;
        }
        return true;
    }

    public static boolean isLoginDataValid(LoginUserDTO loginUserDTO) {
        String login = loginUserDTO.getLogin();
        String password = loginUserDTO.getPassword();

        if (isNullOrBlank(login)) {
            return false;
        }
        if (isNullOrBlank(password)) {
            return false;
        }
        return true;
    }

    public static boolean isUpdateDataValid(UpdateUserDTO updateUserDTO){
        String email = updateUserDTO.getEmail();
        String username = updateUserDTO.getUsername();
        if (isEmail(email)) {
            return false;
        }
        if (isNullOrBlank(username)) {
            return false;
        }
        return true;
    }
}
