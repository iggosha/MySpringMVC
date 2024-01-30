package com.myspring.repositories;

import com.myspring.models.NewsCard;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class NewsCardsRepository {

    private final JdbcTemplate jdbcTemplate;


    public void save(NewsCard newsCard) {
        jdbcTemplate.update("INSERT INTO newscards(header, content) VALUES(?,?)",
                newsCard.getHeader(),
                newsCard.getContent()
        );
    }

    public void updateById(long id, NewsCard newsCard) {
        jdbcTemplate.update("UPDATE newscards SET header = ?, content = ? WHERE id = ?",
                newsCard.getHeader(),
                newsCard.getContent(),
                id);
    }

    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM newscards WHERE id = ?",
                id);
    }

    public List<NewsCard> findAll() {
        return jdbcTemplate.query("SELECT * FROM newscards",
                new BeanPropertyRowMapper<>(NewsCard.class));
    }

    public List<NewsCard> findBunchOfLastLimited(int limit) {
        return jdbcTemplate.query("SELECT * FROM newscards ORDER BY id DESC LIMIT ?",
                new BeanPropertyRowMapper<>(NewsCard.class),
                limit);
    }

    public Optional<NewsCard> findById(long id) {
        return jdbcTemplate.query("SELECT * FROM newscards WHERE id = ?",
                        new BeanPropertyRowMapper<>(NewsCard.class),
                        id)
                .stream()
                .findAny();
    }
}
