package com.epam.esm.controller;


import com.epam.esm.dto.request.TagRequestDto;
import com.epam.esm.dto.response.TagResponseDto;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  Controller for the Tag entity
 * </p>
 */
@RestController
@RequestMapping(value = "/tags")
public class TagController {
    private final TagService tagService;
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    /**
     * <p>
     *  Returns the tag by its id
     *  </p>
     * @param id The id of the Tag that needs to be received
     * @return The representation of the requested Tag
     */
    @GetMapping("/{id}")
    public TagResponseDto getById(@PathVariable int id) {
        return tagService.getById(id);
    }
    /**
     *  <p>
     *   Creates the tag using given input
     *  </p>
     * @param tag The tag data that needs to be stored
     */
    @PostMapping
    public TagResponseDto create(@RequestBody TagRequestDto tag) {
        return tagService.create(tag);
    }
    /**
     *  <p>
     *  Deletes the tag by its id
     *  </p>
     * @param id The id of the Tag that needs to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {

        tagService.deleteById(id);

    }
}
