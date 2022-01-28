package com.adjust.testlibrary.test.api.test;

import com.adjust.testlibrary.model.base.Response;
import com.adjust.testlibrary.model.User;
import com.adjust.testlibrary.test.api.mock.MockUser;
import com.adjust.testlibrary.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Constants.JUnit.API_TEST)
public class UserAPITest extends AbstractAPITest {

    private static final String NOT_EXISTING_USERNAME = "notExisting";

    @Test
    @DisplayName("GET [404]: get user by notExisting username")
    public void testFindUser() {
        Response response = userHelper.getByUsername(NOT_EXISTING_USERNAME);
        assertErrorCode(404, response);
    }

    @Test
    @DisplayName("POST [200]: create user by mock")
    public void testCreateUser() {
        User user = MockUser.mockUser();
        Response response = userHelper.create(user);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    @DisplayName("UPDATE [200]: update user by [user1]")
    public void testUpdateUser() {
        User user = MockUser.mockUser();
        Response response = userHelper.updateByUsername("user1", user);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    @DisplayName("DELETE [404]: delete user by notExistingUser")
    public void testDeleteNotExistingUser() {
        Response response = userHelper.deleteByUsername(NOT_EXISTING_USERNAME);
        assertErrorCode(404, response);
    }
}
