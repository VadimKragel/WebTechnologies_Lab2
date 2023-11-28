package by.bsuir.lab2.service.impl;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.LoginUserDTO;
import by.bsuir.lab2.bean.dto.RegisterUserDTO;
import by.bsuir.lab2.bean.dto.UserDTO;
import by.bsuir.lab2.dao.DAOFactory;
import by.bsuir.lab2.dao.DAOManager;
import by.bsuir.lab2.dao.StorageType;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import by.bsuir.lab2.service.util.BcryptHasher;
import by.bsuir.lab2.service.util.PasswordHasher;
import by.bsuir.lab2.service.validation.Validator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private static final PasswordHasher passwordHasher = new BcryptHasher();
    private static final DAOManager daoManager = DAOFactory.getDAOManager(StorageType.MY_SQL);
    private static final UserDAO userDAO = daoManager.getUserDAO();

    @Override
    public int register(RegisterUserDTO registerUserDTO) throws ServiceException, ValidationException {
        if (!Validator.isRegistrationDataValid(registerUserDTO)) {
            throw new ValidationException("Registration data is invalid.");
        }
        try {
            if (userDAO.isUserExists(registerUserDTO)) {
                throw new ValidationException("User with specified login or email is already registered.");
            }
            String passwordHash = passwordHasher.hashPassword(registerUserDTO.getPassword());
            return userDAO.addUser(registerUserDTO.getUsername(), registerUserDTO.getEmail(), passwordHash);
        } catch (DAOException e) {
            throw new ServiceException("Error during registration of a new user.");
        }
    }

    @Override
    public User login(LoginUserDTO loginUserDTO) throws ServiceException, ValidationException {
        if (!Validator.isLoginDataValid(loginUserDTO)) {
            throw new ValidationException("Invalid data for processing user login.");
        }
        User user;
        try {
            user = userDAO.getUser(loginUserDTO.getLogin());
        } catch (DAOException e) {
            throw new ServiceException("Exception during processing user login.", e);
        }
        if (user != null) {
            if (!passwordHasher.verifyPassword(loginUserDTO.getPassword(), user.getPasswordHash())) {
                user = null;
            }
        }
        return user;
    }

    @Override
    public List<UserDTO> getUsers() throws ServiceException {
        List<UserDTO> userDTOs = null;
        try {
            List<User> users = userDAO.getUsers();
            userDTOs = users.stream()
                    .filter(Objects::nonNull)
                    .map(UserDTO::mapUser)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting list of all users", e);
        }
        return userDTOs;
    }

    @Override
    public UserDTO getUser(int userID) throws ServiceException {
        User user = null;
        try {
            user = userDAO.getUserById(userID);
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting user by ID", e);
        }
        return UserDTO.mapUser(user);
    }
}