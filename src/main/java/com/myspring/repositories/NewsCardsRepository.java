package com.myspring.repositories;

import com.myspring.models.Author;
import com.myspring.models.NewsCard;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class NewsCardsRepository {

    private final SessionFactory sessionFactory;

    @Transactional
    public void save(NewsCard newsCard) {
        Session session = sessionFactory.getCurrentSession();
        newsCard.setAuthor(session.get(Author.class, newsCard.getAuthor().getId()));
        if (newsCard.getCreationDate() != null) {
            newsCard.setCreationDate(newsCard.getCreationDate());
        } else {
            newsCard.setCreationDate(LocalDateTime.now());
        }
        session.persist(newsCard);
    }

    @Transactional
    public void updateById(long id, NewsCard newsCard) {
        Session session = sessionFactory.getCurrentSession();
        NewsCard newsCardFromDb = session.get(NewsCard.class, id);
        newsCardFromDb.setHeader(newsCard.getHeader());
        newsCardFromDb.setContent(newsCard.getContent());
        if (newsCard.getCreationDate() != null) {
            newsCardFromDb.setCreationDate(newsCard.getCreationDate());
        } else {
            newsCardFromDb.setCreationDate(LocalDateTime.now());
        }
        newsCardFromDb.setAuthor(session.get(Author.class, newsCard.getAuthor().getId()));
    }

    @Transactional
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(NewsCard.class, id));
    }

    @Transactional(readOnly = true)
    public List<NewsCard> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<NewsCard> newsCards = session
                .createQuery("FROM NewsCard", NewsCard.class)
                .getResultList();
        return newsCards;
    }

    @Transactional(readOnly = true)
    public List<NewsCard> findBunchOfLastLimited(int limit) {
        Session session = sessionFactory.getCurrentSession();
        Query<NewsCard> query = session.
                createQuery("FROM NewsCard ORDER BY id DESC", NewsCard.class);
        query.setMaxResults(limit);
        List<NewsCard> newsCards = query.getResultList();
        return newsCards;
    }

    @Transactional(readOnly = true)
    public List<NewsCard> findByAuthorId(long authorId) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.get(Author.class, authorId);
        return author.getNewsCards();
    }

    @Transactional(readOnly = true)
    public Optional<NewsCard> findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional<NewsCard> newsCard = Optional.of(session.get(NewsCard.class, id));
        return newsCard;
    }
}
