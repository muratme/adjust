package com.adjust.testlibrary.test.api.test;

import com.adjust.testlibrary.model.Pet;
import com.adjust.testlibrary.model.base.Response;
import com.adjust.testlibrary.test.api.mock.MockPet;
import com.adjust.testlibrary.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Constants.JUnit.API_TEST)
public class PetAPITest extends AbstractAPITest {

    @Test
    @DisplayName("GET [404]: get pet by not existingId")
    public void testFindPet() {
        Response response = petHelper.getByPetId(-1);
        assertErrorCode(404, response);
    }

    @Test
    @DisplayName("POST [200]: create pet by mock")
    public void testCreatePet() {
        Pet pet = MockPet.mockPet();
        Response response = petHelper.create(pet);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    @DisplayName("UPDATE [200]: update pet]")
    public void testUpdatePet() {
        Pet pet = MockPet.mockPet();
        Response response = petHelper.update(pet);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    @DisplayName("DELETE [404]: delete pet by notExistingId")
    public void testDeleteNotExistingPet() {
        Response response = petHelper.deleteByPetId(-1);
        assertErrorCode(404, response);
    }
}
