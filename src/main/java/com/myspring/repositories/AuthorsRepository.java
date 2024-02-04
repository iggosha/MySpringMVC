package com.myspring.repositories;

import com.myspring.models.Author;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorsRepository {

    private final JdbcTemplate jdbcTemplate;


    public void save(Author author) {
        jdbcTemplate.update("INSERT INTO authors(full_name) VALUES(?)",
                author.getFullName()
        );
    }

    public void updateById(long id, Author author) {
        jdbcTemplate.update(
                "UPDATE authors SET full_name = ? WHERE id = ?",
                author.getFullName(),
                id);
    }

    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM authors WHERE id = ?",
                id);
    }

    public List<Author> findAll() {
        return jdbcTemplate.query("SELECT * FROM authors",
                new BeanPropertyRowMapper<>(Author.class));
    }

    public Optional<Author> findByNewsCardId(long newsCardId) {
        return jdbcTemplate.query("SELECT a.* FROM authors a JOIN newscards n ON a.id = n.author_id WHERE n.id = ?",
                        new BeanPropertyRowMapper<>(Author.class),
                        newsCardId)
                .stream()
                .findAny();
    }

    public Optional<Author> findById(long id) {
        return jdbcTemplate.query("SELECT * FROM authors WHERE id = ?",
                        new BeanPropertyRowMapper<>(Author.class),
                        id)
                .stream()
                .findAny();
    }
}