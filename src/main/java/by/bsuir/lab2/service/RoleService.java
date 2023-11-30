package by.bsuir.lab2.service;

import by.bsuir.lab2.bean.dto.GetRoleDTO;
import by.bsuir.lab2.service.exception.ServiceException;

import java.util.List;

public interface RoleService {
    List<GetRoleDTO> getRoles() throws ServiceException;
    GetRoleDTO getRoleById(int roleId) throws ServiceException;
}
