package com.example.springtemplate.controllers;

import com.example.springtemplate.exceptions.InvalidRestBookException;
import com.example.springtemplate.models.Book;
import com.example.springtemplate.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lgeoff on 03.07.2015.
 */
@RestController
@RequestMapping("/rest/books")
public class BookRestController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET,produces="application/json")
    public Book getById(@PathVariable Integer bookId) {
        return bookService.findById(bookId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET,produces="application/json")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,produces="application/json")
    public Book add(@RequestBody @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRestBookException("Invalid book entry", bindingResult);
        }
         return bookService.addBook(book);
    }
}
