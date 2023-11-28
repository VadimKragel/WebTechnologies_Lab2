package by.bsuir.lab2.service.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BcryptHasher extends PasswordHasher {

    private static final String HASH_METHOD = "BCrypt";
    private static final int COST = 10;
    @Override
    public String getHashMethod() {
        return HASH_METHOD;
    }

    @Override
    public String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(COST, password.toCharArray());
    }

    @Override
    public boolean verifyPassword(String password, String hash) {
        try {
            return BCrypt.verifyer().verify(password.toCharArray(), hash).verified;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
