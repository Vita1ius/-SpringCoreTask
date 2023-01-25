package com.epam.esm.mapper;

import com.epam.esm.dto.request.TagRequestDto;
import com.epam.esm.dto.response.TagResponseDto;
import com.epam.esm.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagRequestDto toRequestDto(Tag tag){
        TagRequestDto tagRequestDto = new TagRequestDto();
        tagRequestDto.setName(tag.getName());
        return tagRequestDto;
    }
    public Tag toEntity(TagRequestDto tagRequestDto){
        Tag tag = new Tag();
        tag.setName(tagRequestDto.getName());
        return tag;
    }
    public TagResponseDto toResponseDto(Tag tag){
        TagResponseDto tagResponseDto = new TagResponseDto();
        tagResponseDto.setId(tag.getId());
        tagResponseDto.setName(tag.getName());
        return tagResponseDto;
    }
}
