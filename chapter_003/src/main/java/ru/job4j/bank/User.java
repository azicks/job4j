package ru.job4j.bank;

import java.util.Objects;

public class User {

    private String name;
    private String passport;

    public User(String passport) {
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (this.getClass() == obj.getClass()) {
                User u = (User) obj;
                result = this.passport.equals(u.getPassport());
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
