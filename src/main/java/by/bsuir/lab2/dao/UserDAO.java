package by.bsuir.lab2.dao;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.RegisterUserDTO;
import by.bsuir.lab2.bean.dto.UpdateUserDTO;
import by.bsuir.lab2.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {

    int addUser(String username, String email, String passwordHash) throws DAOException;

    boolean isUserExists(RegisterUserDTO registerUserDTO) throws DAOException;

    User getUser(String login) throws DAOException;
    
    User getUserById(int userId) throws DAOException;

    boolean updateUser(UpdateUserDTO updateUserDTO) throws IllegalStateException, DAOException;
    
    List<User> getUsers() throws DAOException;
}
