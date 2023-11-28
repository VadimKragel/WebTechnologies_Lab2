package by.bsuir.lab2.service.util;

public abstract class PasswordHasher {

    public abstract String getHashMethod();
    public abstract String hashPassword(String password);
    public abstract boolean verifyPassword(String password, String hash);
}
