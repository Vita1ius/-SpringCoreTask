package com.epam.esm.service.impl;

import com.epam.esm.dto.request.GiftCertificateRequestDto;
import com.epam.esm.dto.response.GiftCertificateResponseDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.IncorrectParameterException;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.exception.ErrorCode.CERTIFICATE_NOT_FOUND;

@Service
@Transactional
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateRepository giftCertificateRepository;
    private final GiftCertificateMapper giftCertificateMapper;
    private final TagRepository tagRepository;


    public GiftCertificateServiceImpl(GiftCertificateRepository giftCertificateRepository,
                                      GiftCertificateMapper giftCertificateMapper, TagRepository tagRepository) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.giftCertificateMapper = giftCertificateMapper;
        this.tagRepository = tagRepository;
    }

    @Override
    public GiftCertificateResponseDto getById(Integer id) {
        Optional<GiftCertificate> giftCertificate = giftCertificateRepository.getById(id);
        if (!giftCertificate.isPresent()){
            throw new com.epam.esm.exception.EntityNotFoundException(id, CERTIFICATE_NOT_FOUND);
        }
        return toDtoWithTags(giftCertificateMapper.toResponseDto(giftCertificate.get()),id);
    }

    @Override
    public List<GiftCertificateResponseDto> getByTagName(String tagName) {
        List<Optional<GiftCertificate>> optionals = giftCertificateRepository.getByTagName(tagName);
        if (optionals.isEmpty()){
            throw new IncorrectParameterException("name",tagName, CERTIFICATE_NOT_FOUND);
        }

        List<GiftCertificateResponseDto> gcList = new ArrayList<>();
        for (Optional<GiftCertificate> gc: optionals){
            gc.ifPresent(giftCertificate ->
                            gcList.add(toDtoWithTags(giftCertificateMapper.toResponseDto(giftCertificate),
                                    giftCertificate.getId())));
        }
        return gcList;
    }

    @Override
    public GiftCertificateResponseDto create(GiftCertificateRequestDto giftCertificate) {

        GiftCertificate gc = giftCertificateRepository.create(giftCertificateMapper.toEntity(giftCertificate));

        return giftCertificateMapper.toResponseDto(gc);
    }

    @Override
    public GiftCertificateResponseDto update(GiftCertificateRequestDto giftCertificateRequestDto, Integer id) {
        if (!giftCertificateRepository.getById(id).isPresent()){
            throw new EntityNotFoundException(id,CERTIFICATE_NOT_FOUND);
        }
        GiftCertificate giftCertificate = giftCertificateMapper.toEntity(giftCertificateRequestDto);
        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        giftCertificate.setId(id);
        giftCertificateRepository.update(giftCertificate);

        return getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        giftCertificateRepository.deleteById(id);
    }
    public List<Tag> getTags(Integer id) {
        List<Integer> tagIds = giftCertificateRepository.getTagsId(id);
        List<Tag> tags =new ArrayList<>();
        for (Integer num: tagIds){
            Optional<Tag> optional = tagRepository.getById(num);
            optional.ifPresent(tags::add);
        }
        return tags;
    }
    public GiftCertificateResponseDto toDtoWithTags(GiftCertificateResponseDto giftCertificateResponseDto,
                                                    Integer certificateId) {

        List<Tag> tags = getTags(certificateId);
        if (!(tags.size() == 0)) {
            giftCertificateResponseDto.setTags(tags);
        }
        return giftCertificateResponseDto;
    }
}
