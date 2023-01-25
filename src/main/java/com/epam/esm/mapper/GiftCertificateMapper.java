package com.epam.esm.mapper;

import com.epam.esm.dto.request.GiftCertificateRequestDto;
import com.epam.esm.dto.response.GiftCertificateResponseDto;
import com.epam.esm.model.GiftCertificate;
import org.springframework.stereotype.Component;


@Component
public class GiftCertificateMapper {
    public GiftCertificateRequestDto toRequestDto(GiftCertificate giftCertificate) {
        GiftCertificateRequestDto dto = new GiftCertificateRequestDto();

        dto.setDescription(giftCertificate.getDescription());
        dto.setDuration(giftCertificate.getDuration());
        dto.setName(giftCertificate.getName());
        dto.setPrice(giftCertificate.getPrice());
        dto.setTags(giftCertificate.getTags());
        return dto;
    }
    public GiftCertificate toEntity(GiftCertificateRequestDto giftCertificateRequestDto){
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName(giftCertificateRequestDto.getName());
        giftCertificate.setDescription(giftCertificateRequestDto.getDescription());
        giftCertificate.setPrice(giftCertificateRequestDto.getPrice());
        giftCertificate.setDuration(giftCertificateRequestDto.getDuration());
        giftCertificate.setTags(giftCertificateRequestDto.getTags());

        return giftCertificate;
    }
    public GiftCertificateResponseDto toResponseDto(GiftCertificate giftCertificate) {
        GiftCertificateResponseDto dto = new GiftCertificateResponseDto();
        dto.setId(giftCertificate.getId());
        dto.setDescription(giftCertificate.getDescription());
        dto.setDuration(giftCertificate.getDuration());
        dto.setName(giftCertificate.getName());
        dto.setPrice(giftCertificate.getPrice());
        dto.setTags(giftCertificate.getTags());
        dto.setCreateDate(giftCertificate.getCreateDate());
        dto.setLastUpdateDate(giftCertificate.getLastUpdateDate());
        return dto;
    }
}
