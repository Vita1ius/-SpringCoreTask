package com.epam.esm.repository;

import com.epam.esm.TestConfig;
import com.epam.esm.model.Tag;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {TestConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class TagRepositoryTestIT {
    @Autowired
    private TagRepository tagRepository;

    @Test
    void getByIdTest() {
        Tag tag = tagRepository.getById(1).orElse(null);
        assertThat(tag).isNotNull();
        assertThat(tag.getId()).isEqualTo(1);
    }

    @Test
    void getByNameTest() {
        Optional<Tag> byName = tagRepository.getByName("name");
        if (!byName.isPresent()){
            throw new EntityNotFoundException();
        }
        assertThat(byName).isNotNull();
        assertThat(byName.get().getName()).isEqualTo("name");
    }

    @Test
    @Order(1)
    void createTest() {
        Tag tag = new Tag(2, "createName");
        tagRepository.create(tag);
        Tag byName = tagRepository.getByName("createName").orElse(null);

        assertThat(byName.getName()).isEqualTo("createName");
    }

    @Test
    @Order(2)
    void isPresentTest() {
        assertTrue(tagRepository.isPresent("createName"));
    }
    @Test
    @Order(3)
    void deleteById(){
        tagRepository.deleteById(2);
        assertFalse(tagRepository.isPresent("createName"));
    }
}