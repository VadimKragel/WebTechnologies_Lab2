package by.bsuir.lab2.service;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.GetUserDTO;
import by.bsuir.lab2.bean.dto.LoginUserDTO;
import by.bsuir.lab2.bean.dto.RegisterUserDTO;
import by.bsuir.lab2.bean.dto.UpdateUserDTO;
import by.bsuir.lab2.service.exception.DuplicateUniqueException;
import by.bsuir.lab2.service.exception.LastAdminException;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;

import java.util.List;

public interface UserService {
    int register(RegisterUserDTO registerUserDTO) throws ServiceException, ValidationException;

    User login(LoginUserDTO loginUserDTO) throws ServiceException, ValidationException;

    List<GetUserDTO> getUsers() throws ServiceException;

    GetUserDTO getUser(int userID) throws ServiceException;

    boolean updateUser(UpdateUserDTO updateUserDTO) throws ServiceException, ValidationException, DuplicateUniqueException, LastAdminException;
}
