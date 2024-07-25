package com.bytecoder.DesignPatterns.creational.Singleton;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public
class User {
    private String name;

    private String role;

    private static User user;

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    private User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;

        private String role;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder role(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this.name, this.role);
        }

    }
}
