package com.epam.esm.repository;


import com.epam.esm.TestConfig;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {TestConfig.class},
        loader = AnnotationConfigContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificateRepositoryTestIT {

    @Autowired
    GiftCertificateRepository giftCertificateRepository;

    @Autowired
    TagRepository tagRepository;

    @Test
    @Order(1)
    void getByIdTest() {
        assertThat(giftCertificateRepository.getById(1)).isNotNull();
    }

    @Test
    @Order(3)
    void createTest() {
        List<Tag> tags = new ArrayList<>();
        tags.add(tagRepository.getById(1).orElse(null));
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("Name");
        giftCertificate.setDescription("Description");
        giftCertificate.setPrice(BigDecimal.valueOf(100));
        giftCertificate.setDuration(10);
        giftCertificate.setTags(tags);

        giftCertificateRepository.create(giftCertificate);

        assertThat(giftCertificateRepository.getById(2)).isNotNull();
        assertThat(Objects.requireNonNull(giftCertificateRepository.getById(2).orElse(null)).
                getDescription()).isEqualTo("Description");
    }

    @Test
    @Order(4)
    void updateTest() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1);
        giftCertificate.setName("newName");
        giftCertificate.setDescription("newDescription");
        giftCertificate.setPrice(BigDecimal.valueOf(200));
        giftCertificate.setDuration(10);
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        giftCertificate.setTags(null);

        String firstName = Objects.requireNonNull(giftCertificateRepository.getById(1).orElse(null)).getName();
        giftCertificateRepository.update(giftCertificate);
        String updatedName = Objects.requireNonNull(giftCertificateRepository.getById(1).orElse(null)).getName();

        assertThat(firstName).isNotEqualTo(updatedName);
    }


    @Test
    void deleteByIdTest(){
        giftCertificateRepository.deleteById(1);
        assertThat(giftCertificateRepository.getById(1)).isEmpty();
    }
}
