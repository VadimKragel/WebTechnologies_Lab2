package by.bsuir.lab2.service.impl;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.dto.GetRoleDTO;
import by.bsuir.lab2.dao.DAOFactory;
import by.bsuir.lab2.dao.DAOManager;
import by.bsuir.lab2.dao.RoleDAO;
import by.bsuir.lab2.dao.StorageType;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.service.RoleService;
import by.bsuir.lab2.service.exception.ServiceException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoleServiceImpl implements RoleService {

    private static final DAOManager daoManager = DAOFactory.getDAOManager(StorageType.MY_SQL);
    private static final RoleDAO roleDAO = daoManager.getRoleDAO();
    @Override
    public List<GetRoleDTO> getRoles() throws ServiceException {
        List<GetRoleDTO> roleDTOs = null;
        try {
            List<Role> roles = roleDAO.getRoles();
            roleDTOs= roles.stream()
                    .filter(Objects::nonNull)
                    .map(GetRoleDTO::mapRole)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting list of all users", e);
        }
        return roleDTOs;
    }

    @Override
    public GetRoleDTO getRoleById(int roleId) throws ServiceException {
        Role role = null;
        try {
            role = roleDAO.getRoleById(roleId);
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting role by ID", e);
        }
        return GetRoleDTO.mapRole(role);
    }
}
