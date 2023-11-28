package by.bsuir.lab2.service;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.LoginUserDTO;
import by.bsuir.lab2.bean.dto.RegisterUserDTO;
import by.bsuir.lab2.bean.dto.UserDTO;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;

import java.util.List;

public interface UserService {
    int register(RegisterUserDTO registerUserDTO) throws ServiceException, ValidationException;
    
    User login(LoginUserDTO loginUserDTO) throws ServiceException, ValidationException;
    
    List<UserDTO> getUsers() throws ServiceException;
    
    UserDTO getUser(int userID) throws ServiceException;
}
