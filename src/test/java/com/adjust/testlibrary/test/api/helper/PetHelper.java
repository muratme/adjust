package com.adjust.testlibrary.test.api.helper;

import com.adjust.testlibrary.model.Pet;
import com.adjust.testlibrary.model.base.Response;
import com.adjust.testlibrary.util.resource.ResourceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PetHelper {

    @Value("${petstore.host}/pet")
    private String RESOURCE_BASE_URL;

    @Autowired
    private ResourceHelper resourceHelper;

    public Response getByPetId(int petId) {
        String url = UriComponentsBuilder
                .fromUriString(RESOURCE_BASE_URL)
                .path("/" + petId)
                .build().toUriString();

        return resourceHelper.get(url);
    }

    public Response create(Pet pet) {
        return resourceHelper.post(RESOURCE_BASE_URL, pet);
    }

    public Response deleteByPetId(int petId) {
        String url = UriComponentsBuilder
                .fromUriString(RESOURCE_BASE_URL)
                .path("/" + petId)
                .build().toUriString();

        return resourceHelper.delete(url);
    }

    public Response update(Pet pet) {
        return resourceHelper.put(RESOURCE_BASE_URL, pet);
    }
}
