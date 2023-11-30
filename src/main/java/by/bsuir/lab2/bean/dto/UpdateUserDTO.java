package by.bsuir.lab2.bean.dto;

import java.sql.Date;

public class UpdateUserDTO {
    private int id;

    private int roleId;

    private String email;

    private String username;

    private String name;

    private String surname;

    private String patronymic;

    private Date birthDate;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(int id, int roleId, String email, String username, String name, String surname, String patronymic, Date birthDate) {
        this.id = id;
        this.roleId = roleId;
        this.email = email;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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