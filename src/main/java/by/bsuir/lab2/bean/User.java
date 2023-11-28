package by.bsuir.lab2.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 3572218873972204184L;
    private int id;

    private int roleId;

    private String email;

    private String username;

    private String passwordHash;

    private String name;

    private String surname;

    private String patronymic;

    private Date birthDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && email.equals(user.email) && username.equals(user.username) && passwordHash.equals(user.passwordHash) && name.equals(user.name) && surname.equals(user.surname) && Objects.equals(patronymic, user.patronymic) && birthDate.equals(user.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, email, username, passwordHash, name, surname, patronymic, birthDate);
    }
}