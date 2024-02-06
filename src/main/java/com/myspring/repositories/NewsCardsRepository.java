package com.myspring.repositories;

import com.myspring.models.Author;
import com.myspring.models.NewsCard;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class NewsCardsRepository {

    private final Session session;

    @Transactional
    public void save(NewsCard newsCard) {
        session.beginTransaction();
        newsCard.setAuthor(session.get(Author.class, newsCard.getAuthor().getId()));
        if (newsCard.getCreationDate()!=null) {
            newsCard.setCreationDate(newsCard.getCreationDate());
        } else {
            newsCard.setCreationDate(LocalDateTime.now());
        }
        session.persist(newsCard);
        session.getTransaction().commit();
    }

    @Transactional
    public void updateById(long id, NewsCard newsCard) {
        session.beginTransaction();
        NewsCard newsCardFromDb = session.get(NewsCard.class, id);
        newsCardFromDb.setHeader(newsCard.getHeader());
        newsCardFromDb.setContent(newsCard.getContent());
        if (newsCard.getCreationDate()!=null) {
            newsCardFromDb.setCreationDate(newsCard.getCreationDate());
        } else {
            newsCardFromDb.setCreationDate(LocalDateTime.now());
        }
        newsCardFromDb.setAuthor(session.get(Author.class, newsCard.getAuthor().getId()));
        session.getTransaction().commit();
    }

    @Transactional
    public void deleteById(long id) {
        session.beginTransaction();
        session.remove(session.get(NewsCard.class, id));
        session.getTransaction().commit();
    }

    @Transactional(readOnly = true)
    public List<NewsCard> findAll() {
        session.beginTransaction();
        List<NewsCard> newsCards = session
                .createQuery("FROM NewsCard", NewsCard.class)
                .getResultList();
        session.getTransaction().commit();
        return newsCards;
    }

    @Transactional(readOnly = true)
    public List<NewsCard> findBunchOfLastLimited(int limit) {
        session.beginTransaction();
        Query<NewsCard> query = session.
                createQuery("FROM NewsCard ORDER BY id DESC", NewsCard.class);
        query.setMaxResults(limit);
        List<NewsCard> newsCards = query.getResultList();
        session.getTransaction().commit();
        return newsCards;
    }

    @Transactional(readOnly = true)
    public List<NewsCard> findByAuthorId(long authorId) {
        session.beginTransaction();
        Author author = session.get(Author.class, authorId);
        session.getTransaction().commit();
        return author.getNewsCards();
    }

    @Transactional(readOnly = true)
    public Optional<NewsCard> findById(long id) {
        session.beginTransaction();
        Optional<NewsCard> newsCard = Optional.of(session.get(NewsCard.class, id));
        session.getTransaction().commit();
        return newsCard;
    }
}
