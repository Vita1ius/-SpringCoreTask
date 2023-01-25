package com.epam.esm.controller;

import com.epam.esm.dto.request.GiftCertificateRequestDto;
import com.epam.esm.dto.response.GiftCertificateResponseDto;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 *  Controller for the Gift Certificate entity
 * </p>
 */
@RestController
@RequestMapping(value = "gift_certificate")
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;

    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }
    /**
     * <p>
     *  Returns the Gift Certificate by its id
     *  </p>
     * @param id The id of the Gift Certificate that needs to be received
     * @return The representation of the requested Gift Certificate
     */
    @GetMapping("/{id}")
    public GiftCertificateResponseDto getById(@PathVariable Integer id) {
        return giftCertificateService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody GiftCertificateRequestDto giftCertificateRequestDto) {
        giftCertificateService.create(giftCertificateRequestDto);
    }

    /**
     * <p>
     *  Returns the Gift Certificate by the name of the tag that is connected to it
     *  </p>
     * @param tagName The name of the tag that is connected to the Gift Certificate that needs to be received
     * @return The representation of the requested Gift Certificate
     */
    @GetMapping("/tagname/{tagName}")
    public List<GiftCertificateResponseDto> getByTagName(@PathVariable String tagName) {
        return giftCertificateService.getByTagName(tagName);
    }

    /**
     *  <p>
     *  Deletes the Gift Certificate by its id
     *  </p>
     * @param id The id of the Gift Certificate that needs to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        giftCertificateService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public GiftCertificateResponseDto update(@RequestBody GiftCertificateRequestDto giftCertificateRequestDto,
                                 @PathVariable Integer id) {
        return giftCertificateService.update(giftCertificateRequestDto,id);
    }
}
