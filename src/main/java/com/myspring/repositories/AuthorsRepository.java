package com.myspring.repositories;

import com.myspring.models.Author;
import com.myspring.models.NewsCard;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorsRepository {

    private final Session session;

    @Transactional
    public void save(Author author) {
        session.beginTransaction();
        session.persist(author);
        session.getTransaction().commit();
    }

    @Transactional
    public void updateById(long id, Author author) {
        session.beginTransaction();
        Author authorFromDb = session.get(Author.class, id);
        authorFromDb.setFullName(author.getFullName());
        session.getTransaction().commit();
    }

    @Transactional
    public void deleteById(long id) {
        session.beginTransaction();
        session.remove(session.get(Author.class, id));
        session.getTransaction().commit();
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        session.beginTransaction();
        List<Author> authors = session.createQuery("FROM Author", Author.class).getResultList();
        session.getTransaction().commit();
        return authors;
    }

    @Transactional(readOnly = true)
    public Optional<Author> findByNewsCardId(long newsCardId) {
        session.beginTransaction();
        NewsCard newsCard = session.get(NewsCard.class, newsCardId);
        session.getTransaction().commit();
        return Optional.of(newsCard.getAuthor());
    }

    @Transactional(readOnly = true)
    public Optional<Author> findById(long id) {
        session.beginTransaction();
        Author author = session.get(Author.class, id);
        session.getTransaction().commit();
        return Optional.of(author);
    }
}