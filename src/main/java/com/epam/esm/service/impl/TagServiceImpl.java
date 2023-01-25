package com.epam.esm.service.impl;

import com.epam.esm.dto.request.TagRequestDto;
import com.epam.esm.dto.response.TagResponseDto;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.IncorrectParameterException;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import static com.epam.esm.exception.ErrorCode.*;


@Service
@Transactional
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public TagResponseDto getById(Integer id) {
        Optional<Tag> tagOptional = tagRepository.getById(id);
        if (!tagOptional.isPresent()){
            throw new EntityNotFoundException(id, TAG_NOT_FOUND);
        }
        return tagMapper.toResponseDto(tagOptional.get());
    }

    @Override
    public TagResponseDto create(TagRequestDto tag) {
        validateTagDto(tag);
        if (tagRepository.getByName(tag.getName()).isPresent()){
            throw new DuplicateEntityException(tag.getName(), DUPLICATE_TAG_NAME);
        }
        Tag tagInDB = tagRepository.create(tagMapper.toEntity(tag));

        return tagMapper.toResponseDto(tagInDB);
    }

    @Override
    public void deleteById(Integer id) {
        if (!tagRepository.getById(id).isPresent()){
            throw new EntityNotFoundException(id, TAG_NOT_FOUND);
        }
        tagRepository.deleteById(id);
    }

    public void validateTagDto(TagRequestDto tagRequestDto){
        if (tagRequestDto.getName() == null || tagRequestDto.getName().trim().length() < 1) {
            throw new IncorrectParameterException("name",tagRequestDto, INCORRECT_TAG_PARAMETER);
        }
    }
    public Integer getByName(String name){
        Optional<Tag> optional = tagRepository.getByName(name);
        if (!optional.isPresent()){
            throw new IncorrectParameterException("name",name,TAG_NOT_FOUND);
        }
        return optional.get().getId();
    }
}
