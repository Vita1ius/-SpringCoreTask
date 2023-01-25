package com.epam.esm.repository.impl;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {
    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<GiftCertificate> getById(Integer id) {
        List<GiftCertificate> giftCertificates = jdbcTemplate.query("SELECT * FROM gift_certificate WHERE id=?",
                new BeanPropertyRowMapper<>(GiftCertificate.class), id);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(giftCertificates));
    }

    @Override
    public List<Optional<GiftCertificate>> getByTagName(String tagName) {
        Integer tagId = jdbcTemplate.queryForObject("SELECT id FROM tag WHERE name = ?", Integer.class, tagName);
        List<Integer> idList = jdbcTemplate.queryForList("SELECT gift_certificate_id FROM gift_certificate_has_tag"+
                " WHERE tag_id=?", Integer.class, tagId);
        return idList.stream().map(this::getById).collect(Collectors.toList());
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement("INSERT INTO gift_certificate" +
                                        " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", new String[] {"id"});
                        ps.setString(1, giftCertificate.getName());
                        ps.setString(2, giftCertificate.getDescription());
                        ps.setBigDecimal(3, giftCertificate.getPrice());
                        ps.setInt(4, giftCertificate.getDuration());
                        ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                        ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                        return ps;
                    }
                },
                keyHolder);
        giftCertificate.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        List<Tag> tags = giftCertificate.getTags();
        if (tags != null) {
            for (Tag tag: tags){
                stitchById(tag.getId(), giftCertificate.getId());
            }
        }

        return getById(giftCertificate.getId()).get();
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        jdbcTemplate.update("UPDATE gift_certificate SET name=?, description=?," +
                        " price=?, duration=?, last_update_date=? WHERE id=?",
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                giftCertificate.getLastUpdateDate(),
                giftCertificate.getId());
        if (giftCertificate.getTags() != null) {
            stitchById(giftCertificate.getTags().get(0).getId(), giftCertificate.getId());
        }
        return getById(giftCertificate.getId()).get();
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM gift_certificate_has_tag WHERE gift_certificate_id=?", id);
        jdbcTemplate.update("DELETE FROM gift_certificate WHERE id=?", id);
    }

    public void stitchById(Integer tagId, Integer giftCertificateId) {
        jdbcTemplate.update("INSERT INTO gift_certificate_has_tag(gift_certificate_id, tag_id) VALUES (?, ?)",
                giftCertificateId,
                tagId);
    }
    @Override
    public List<Integer> getTagsId(Integer id){
        return jdbcTemplate.queryForList("SELECT tag_id FROM gift_certificate_has_tag WHERE gift_certificate_id=?",
                Integer.class, id);
    }
    public Optional<GiftCertificate> getByName(String name) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM gift_certificate WHERE name=?",
                new BeanPropertyRowMapper<>(GiftCertificate.class), name));

    }
}
