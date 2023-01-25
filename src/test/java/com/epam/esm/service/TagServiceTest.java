package com.epam.esm.service;


import com.epam.esm.dto.request.TagRequestDto;
import com.epam.esm.dto.response.TagResponseDto;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagMapper tagMapper;
    private Tag tag;
    private TagRequestDto tagRequestDto;

    private TagResponseDto tagResponseDto;

    @InjectMocks
    private TagServiceImpl tagService;

    @BeforeEach
    void setUp() {
        tag = new Tag();
        tag.setId(1);
        tag.setName("tagName");

        tagRequestDto = new TagRequestDto();
        tagRequestDto.setName("tagName");

        tagResponseDto = new TagResponseDto();
        tagResponseDto.setId(1);
        tagResponseDto.setName("tagName");

    }

    @Test
    void getByIdTest() {
        Mockito.when(tagRepository.getById(1)).thenReturn(Optional.ofNullable(tag));
        Mockito.when(tagMapper.toResponseDto(tag)).thenReturn(tagResponseDto);
        tagService.getById(1);

        Mockito.verify(tagRepository).getById(1);
        Mockito.verify(tagMapper).toResponseDto(tag);
    }

    @Test
    void createTest() {
        Mockito.when(tagMapper.toEntity(tagRequestDto)).thenReturn(tag);
        tagService.create(tagRequestDto);
        Mockito.verify(tagRepository).create(tag);
    }
    @Test
    void getByName(){
        when(tagRepository.getByName(tag.getName())).thenReturn(Optional.ofNullable(tag));
        tagService.getByName(tag.getName());
        Mockito.verify(tagRepository).getByName(tag.getName());
    }
    @Test
    void deleteByIdTest() {
        Mockito.when(tagRepository.getById(1)).thenReturn(Optional.ofNullable(tag));
        tagService.deleteById(1);
        Mockito.verify(tagRepository).deleteById(1);
    }
}