package com.epam.esm.repository;

import com.epam.esm.model.Tag;

import java.util.Optional;
/**
 * An interface that provides db connectivity for CRUD operations with Tag entities
 */
public interface TagRepository {
    /**
     * retrieves the Tag from db by id
     * @param id id of the requested entity
     * @return the requested entity
     */
    Optional<Tag> getById(Integer id);
    /**
     * retrieves the Tag from db by name
     * @param name name of the requested entity
     * @return the requested entity
     */
    Optional<Tag> getByName(String name);
    /**
     * deletes the specified Tag from db by id
     * @param id id of the Tag that needs to be deleted
     */
    void deleteById(Integer id);
    /**
     * creates a new Tag in db with the given parameters
     * @param tag new Tag data
     */
    Tag create(Tag tag);
    /**
     * updates the Tag in db by id with the given parameters
     * @param tag  new data for the GiftCertificate to be updated with
     * @param id id of the to-be-updated entity
     */
    Tag update(Tag tag);
    /**
     * returns true if Tag with specified name exists in the db
     * @param name name of the Tag that needs to be searched
     * @return boolean value
     */
    boolean isPresent(String name);
}
