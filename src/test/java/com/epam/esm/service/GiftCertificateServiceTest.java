package com.epam.esm.service;

import com.epam.esm.dto.request.GiftCertificateRequestDto;
import com.epam.esm.dto.response.GiftCertificateResponseDto;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceTest {

    @Mock
    private GiftCertificateRepository giftCertificateRepository;
    @Mock
    private GiftCertificateMapper giftCertificateMapper;


    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;
    private final List<Optional<GiftCertificate>> gcList = new ArrayList<>();
    private GiftCertificate giftCertificate;
    private GiftCertificateRequestDto giftCertificateRequestDto;
    private GiftCertificateResponseDto giftCertificateResponseDto;

    @BeforeEach
    void setUp() {
        giftCertificate = new GiftCertificate(
                1, "name", "description", BigDecimal.valueOf(200),
                1, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>()
        );
        giftCertificateResponseDto = new GiftCertificateResponseDto(
                1, "name", "description", BigDecimal.valueOf(200),
                1, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>()
        );
        giftCertificateRequestDto = new GiftCertificateRequestDto("name", "description",
                BigDecimal.valueOf(200),
                1,  new ArrayList<>());
    }

    @Test
    void getById() {
        when(giftCertificateRepository.getById(1)).thenReturn(Optional.ofNullable(giftCertificate));
        when(giftCertificateMapper.toResponseDto(giftCertificate)).thenReturn(giftCertificateResponseDto);
        giftCertificateService.getById(1);

        verify(giftCertificateRepository).getById(1);
        verify(giftCertificateMapper).toResponseDto(giftCertificate);
    }

    @Test
    void getByTagName() {
        gcList.add(Optional.ofNullable(giftCertificate));
        when(giftCertificateRepository.getByTagName("tagName")).thenReturn(gcList);
        when(giftCertificateMapper.toResponseDto(giftCertificate)).thenReturn(giftCertificateResponseDto);
        giftCertificateService.getByTagName("tagName");

        verify(giftCertificateRepository).getByTagName("tagName");
        verify(giftCertificateMapper).toResponseDto(giftCertificate);
    }

    @Test
    void create() {
        when(giftCertificateMapper.toEntity(giftCertificateRequestDto)).thenReturn(giftCertificate);
        giftCertificateService.create(giftCertificateRequestDto);
        verify(giftCertificateRepository).create(giftCertificate);
    }

    @Test
    void update() {
        when(giftCertificateRepository.getById(1)).thenReturn(Optional.ofNullable(giftCertificate));
        when(giftCertificateMapper.toEntity(giftCertificateRequestDto)).thenReturn(giftCertificate);
        when(giftCertificateRepository.update(giftCertificate)).thenReturn(giftCertificate);

        giftCertificateService.update(giftCertificateRequestDto, 1);
        giftCertificateService.update(giftCertificateRequestDto, 1);
        giftCertificateService.update(giftCertificateRequestDto, 1);
        giftCertificateService.update(giftCertificateRequestDto, 1);
        verify(giftCertificateRepository,times(4)).update(giftCertificate);
    }

    @Test
    void deleteById() {
        giftCertificateService.deleteById(1);

        verify(giftCertificateRepository).deleteById(1);
    }
}
