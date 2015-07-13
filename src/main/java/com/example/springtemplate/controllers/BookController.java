package com.example.springtemplate.controllers;

import com.example.springtemplate.models.Book;
import com.example.springtemplate.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lgeoff on 02.07.2015.
 */
@Controller
public class BookController {

    @Autowired
    BookService bookService;


    @RequestMapping("/books")
    public ModelAndView getBooks(ModelMap model){
        model.put("books", bookService.findAll());
        return new ModelAndView("books",model);
    }

    @RequestMapping(value = "/rest/create", method = RequestMethod.PUT)
    public @ResponseBody
    Book createBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String createBookFromWeb(@ModelAttribute Book book) {
        this.createBook(book);
        return "redirect:/books";

    }
    @RequestMapping(value = "/books/remove/{bookId}", method = RequestMethod.GET)
    public String removeBook(@PathVariable Integer bookId) {

        bookService.removeById(bookId);
        return "redirect:/books";

    }
}
