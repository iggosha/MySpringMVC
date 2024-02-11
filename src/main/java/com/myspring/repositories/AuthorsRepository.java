package com.myspring.repositories;

import com.myspring.models.Author;
import com.myspring.models.NewsCard;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorsRepository {

    private final SessionFactory sessionFactory;

    @Transactional
    public void save(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(author);
    }

    @Transactional
    public void updateById(long id, Author author) {
        Session session = sessionFactory.getCurrentSession();
        Author authorFromDb = session.get(Author.class, id);
        authorFromDb.setFullName(author.getFullName());
    }

    @Transactional
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Author.class, id));
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Author> authors = session.createQuery("FROM Author", Author.class).getResultList();
        return authors;
    }

    @Transactional(readOnly = true)
    public Optional<Author> findByNewsCardId(long newsCardId) {
        Session session = sessionFactory.getCurrentSession();
        NewsCard newsCard = session.get(NewsCard.class, newsCardId);
        return Optional.of(newsCard.getAuthor());
    }

    @Transactional(readOnly = true)
    public Optional<Author> findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.get(Author.class, id);
        return Optional.of(author);
    }
}