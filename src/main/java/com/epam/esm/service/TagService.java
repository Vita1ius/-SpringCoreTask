package com.epam.esm.service;

import com.epam.esm.dto.request.TagRequestDto;
import com.epam.esm.dto.response.TagResponseDto;
/**
 * A service for CRD operations with Tag
 */
public interface TagService {
    /**
     * returns the TagDto by id
     * @param id id of the requested entity
     * @return DTO representation of requested entity
     */
    TagResponseDto getById(Integer id);
    /**
     * creates new Tag with the given parameters
     * @param tag new Tag data
     */
    TagResponseDto create(TagRequestDto tag);
    /**
     * deletes the specified Tag by id
     * @param id id of the Tag that needs to be deleted
     */
    void deleteById(Integer id);
}
