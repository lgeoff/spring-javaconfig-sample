package com.example.springtemplate.controllers;

import com.example.springtemplate.exceptions.InvalidBookException;
import com.example.springtemplate.exceptions.InvalidRestBookException;
import com.example.springtemplate.models.Book;
import com.example.springtemplate.services.BookService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by lgeoff on 02.07.2015.
 */
@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job job;

    Logger logger= Logger.getLogger(BookController.class.getName());

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
    public String createBookFromWeb(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidBookException("Invalid book entry", bindingResult);
        }
        this.createBook(book);
        return "redirect:/books";

    }
    @RequestMapping(value = "/books/remove/{bookId}", method = RequestMethod.GET)
    public String removeBook(@PathVariable Integer bookId) {

        bookService.removeById(bookId);
        return "redirect:/books";

    }

    @RequestMapping(value = "/books/generateCsv", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource generateCsv(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, JobParameter> confMap = new HashMap<String, JobParameter>();
            confMap.put("time", new JobParameter(System.currentTimeMillis()));
            JobParameters jobParameters = new JobParameters(confMap);

            JobExecution jobExecution=jobLauncher.run(job, jobParameters);
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=export.csv");
            return new FileSystemResource(new File("C:\\test.txt"));

        } catch (Exception e){
            logger.severe(e.getMessage());
            return null;
        }

    }
}
