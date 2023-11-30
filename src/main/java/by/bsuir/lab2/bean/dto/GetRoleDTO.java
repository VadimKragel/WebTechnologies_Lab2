package by.bsuir.lab2.bean.dto;

import by.bsuir.lab2.bean.Role;

public class GetRoleDTO {

    private int id;
    private String name;

    public GetRoleDTO() {
    }

    public static GetRoleDTO mapRole(Role role) {
        if (role == null)
            return null;
        GetRoleDTO roleDTO = new GetRoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
