package com.epam.esm.repository.impl;

import com.epam.esm.model.Tag;
import com.epam.esm.repository.TagRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TagRepositoryImpl implements TagRepository {
    private final JdbcTemplate jdbcTemplate;

    public TagRepositoryImpl(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public Optional<Tag> getById(Integer id) {
        List<Tag> tags = jdbcTemplate.query("SELECT * FROM tag WHERE id=?",
                new BeanPropertyRowMapper<>(Tag.class), id);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(tags));
    }

    @Override
    public Optional<Tag> getByName(String name) {
        List<Tag> tags = jdbcTemplate.query("SELECT * FROM tag WHERE name=?",
                new BeanPropertyRowMapper<>(Tag.class), name);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(tags));

    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM gift_certificate_has_tag WHERE tag_id=?", id);
        jdbcTemplate.update("DELETE FROM tag WHERE id=?", id);
    }

    @Override
    public Tag create(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement("INSERT INTO tag VALUES (DEFAULT , ?)",
                                    new String[] {"id"});
                    ps.setString(1, tag.getName());
                    return ps;
                },
                keyHolder);
        tag.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return tag;
    }

    @Override
    public Tag update(Tag tag) {
        if (getById(tag.getId()).isPresent()) {
            jdbcTemplate.update("UPDATE tag SET name=? WHERE id=?",
                    tag.getName(),
                    tag.getId());
        }
        return getById(tag.getId()).get();
    }

    @Override
    public boolean isPresent(String name) {
        List<String> tagNames = jdbcTemplate.queryForStream("SELECT * FROM tag",
                new BeanPropertyRowMapper<>(Tag.class)).map(Tag::getName).collect(Collectors.toList());
        return tagNames.contains(name);
    }
}
