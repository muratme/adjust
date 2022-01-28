package com.adjust.testlibrary.test.api.helper;

import com.adjust.testlibrary.model.base.Response;
import com.adjust.testlibrary.model.User;
import com.adjust.testlibrary.util.resource.ResourceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    @Value("${petstore.host}/user")
    private String RESOURCE_BASE_URL;

    @Autowired
    private ResourceHelper resourceHelper;

    public Response getByUsername(String username) {
        String path = RESOURCE_BASE_URL + "/" + username;
        return resourceHelper.get(path);
    }

    public Response create(User user) {
        return resourceHelper.post(RESOURCE_BASE_URL, user);
    }

    public Response deleteByUsername(String username) {
        String path = RESOURCE_BASE_URL + "/" + username;
        return resourceHelper.delete(path);
    }

    public Response updateByUsername(String username, User user) {
        String path = RESOURCE_BASE_URL + "/" + username;
        return resourceHelper.put(path, user);
    }
}
