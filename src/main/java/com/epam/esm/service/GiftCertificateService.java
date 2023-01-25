package com.epam.esm.service;

import com.epam.esm.dto.request.GiftCertificateRequestDto;
import com.epam.esm.dto.response.GiftCertificateResponseDto;

import java.util.List;
/**
 * A service for CRUD operations with GiftCertificate
 */
public interface GiftCertificateService {
    /**
     * returns the GiftCertificateDto by id
     * @param id id of the requested entity
     * @return DTO representation of requested entity
     */
    GiftCertificateResponseDto getById(Integer id);
    /**
     * returns the list of GiftCertificateDtos by tag name
     * @param tagName a name of the Tag that is connected to the requested entities
     * @return DTO representations of requested entities
     */
    List<GiftCertificateResponseDto> getByTagName(String tagName);
    /**
     * creates new GiftCertificate with the given parameters and optionally creates new Tag connected to it
     * @param giftCertificate new GiftCertificate data
     */
    GiftCertificateResponseDto create(GiftCertificateRequestDto giftCertificate);
    /**
     * updates the GiftCertificate by id with the given parameters
     * @param giftCertificateRequestDto new GiftCertificate data and optionally creates new Tag connected to it
     * @param id id of the to-be-updated entity
     */
    GiftCertificateResponseDto update(GiftCertificateRequestDto giftCertificateRequestDto, Integer id);
    /**
     * deletes the specified GiftCertificate by id
     * @param id id of the GiftCertificate that needs to be deleted
     */
    void deleteById(Integer id);
}
