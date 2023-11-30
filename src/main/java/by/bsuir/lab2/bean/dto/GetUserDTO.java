package by.bsuir.lab2.bean.dto;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.User;

import java.sql.Date;

public class GetUserDTO {
    private int id;

    private Role role;

    private String email;

    private String username;

    private String name;

    private String surname;

    private String patronymic;

    private Date birthDate;

    public GetUserDTO() {
    }

    public static GetUserDTO mapUser(User user) {
        if (user == null)
            return null;
        GetUserDTO userDTO = new GetUserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setBirthDate(user.getBirthDate());
        return userDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
