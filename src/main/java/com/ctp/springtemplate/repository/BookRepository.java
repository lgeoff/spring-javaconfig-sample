package com.ctp.springtemplate.repository;

import com.ctp.springtemplate.models.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

/**
 * Created by lgeoff on 02.07.2015.
 */
@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager em;


    public List<Book> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Book.class));
        return em.createQuery(cq).getResultList();
    }

    @Transactional
    public Book addBook(Book book) {
        em.persist(book);
        return book;
    }

    @Transactional
    public void remove(Integer bookId) {
        Book book=em.find(Book.class,bookId);
        em.remove(book);
    }

    public Book findById(Integer id) {
       return em.find(Book.class,id);
    }
}
