package by.bsuir.lab2.bean.dto;

import by.bsuir.lab2.bean.EnumRole;
import by.bsuir.lab2.bean.User;

import java.util.Date;

public class UserDTO {
    private int id;

    private EnumRole role;

    private String email;

    private String username;

    private String passwordHash;

    private String name;

    private String surname;

    private String patronymic;

    private Date birthDate;

    public static UserDTO mapUser(User user) {

        if (user == null)
            return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(EnumRole.fromId(user.getRoleId()));
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setBirthDate(user.getBirthDate());
        return userDTO;
    }

    public UserDTO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
