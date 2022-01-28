package com.adjust.testlibrary.test.api.mock;

import com.adjust.testlibrary.model.User;
import com.adjust.testlibrary.util.MockUtils;

public class MockUser {

    private MockUser() {
        throw new IllegalStateException("Mock class");
    }

    public static User mockUser() {
        User user = new User();
        user.setUsername(MockUtils.MOCK.name().username());
        user.setFirstName(MockUtils.MOCK.name().firstName());
        user.setLastName(MockUtils.MOCK.name().lastName());
        user.setEmail(MockUtils.MOCK.bothify("????????##@adjust.com"));
        user.setPhone(MockUtils.MOCK.phoneNumber().phoneNumber());
        user.setUserStatus(1);
        return user;
    }
}
