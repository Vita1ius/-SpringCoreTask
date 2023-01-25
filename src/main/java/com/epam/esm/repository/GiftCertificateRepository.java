package com.epam.esm.repository;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;

import java.util.List;
import java.util.Optional;
/**
 * An interface that provides db connectivity for CRUD operations with GiftCertificate entities
 */
public interface GiftCertificateRepository {
    /**
     * retrieves the GiftCertificate from db by id
     * @param id id of the requested entity
     * @return requested entity
     */
    Optional<GiftCertificate> getById(Integer id);
    /**
     * retrieves the list of GiftCertificates from db by tag name
     * @param tagName a name of the Tag that is connected to the requested entities
     * @return list of requested entities
     */
    List<Optional<GiftCertificate>> getByTagName(String tagName);
    /**
     * creates new GiftCertificate in db with the given parameters and optionally connects new Tag to it (if present)
     * @param giftCertificate new GiftCertificate data
     * @return requested entity
     */
    GiftCertificate create(GiftCertificate giftCertificate);
    /**
     * updates the GiftCertificate in db by id with the given parameters
     * @param giftCertificate  new data for the GiftCertificate to be updated with
     * @param id id of the to-be-updated entity
     * @param tag new Tag that needs to be connected with the updated GiftCertificate (or null value if no tag
     * is provided by service)
     * @return requested entity
     */
    GiftCertificate update(GiftCertificate giftCertificate);
    /**
     * deletes the specified GiftCertificate from db by id
     * @param id id of the GiftCertificate that needs to be deleted
     */
    void deleteById(Integer id);
    /**
     * retrieves the list of numbers from db by tag id
     * @param id an id of the Tag that is connected to the requested entities
     * @return list of requested id
     */
    List<Integer> getTagsId(Integer id);
}
