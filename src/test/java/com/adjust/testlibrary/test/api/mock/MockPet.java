package com.adjust.testlibrary.test.api.mock;

import com.adjust.testlibrary.model.Category;
import com.adjust.testlibrary.model.Pet;
import com.adjust.testlibrary.model.Tag;
import com.adjust.testlibrary.util.MockUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockPet {

    private MockPet() {
        throw new IllegalStateException("Mock class");
    }

    public static Pet mockPet() {
        Pet pet = new Pet();
        pet.setName(MockUtils.MOCK.animal().name());
        pet.setCategory(mockCategory());
        pet.setTags(mockTags());
        pet.setPhotoUrls(mockPhotoUrls());
        pet.setStatus("available");

        return pet;
    }

    public static Category mockCategory() {
        Category category = new Category();
        category.setName(MockUtils.MOCK.color().name());

        return category;
    }

    public static List<Tag> mockTags() {

        List<Tag> tags = new ArrayList<>();
        IntStream.range(0, 3).mapToObj(i -> new Tag()).forEach(tag -> {
            tag.setName(MockUtils.MOCK.funnyName().name());
            tags.add(tag);
        });

        return tags;
    }

    public static List<String> mockPhotoUrls() {
        return IntStream.range(0, 3).mapToObj(i -> MockUtils.MOCK.internet().image()).collect(Collectors.toList());
    }
}
