package com.example.springtemplate.services;

import com.example.springtemplate.models.Book;
import com.example.springtemplate.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lgeoff on 02.07.2015.
 */
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.addBook(book);
    }

    public Book findById(Integer id){
        return bookRepository.findById(id);
    }

    public void removeById(Integer bookId) {
        bookRepository.remove(bookId);
    }
}
