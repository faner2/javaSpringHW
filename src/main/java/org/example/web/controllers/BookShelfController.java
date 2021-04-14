package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "books")
public class BookShelfController {
    private final Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model){
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book){
        if (bookService.checkBook(book)){
            bookService.saveBook(book);
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
        else {
            logger.info("info about book is not filled : " + book);
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeAllAuthors")
    public String removeBookAuthors(@RequestParam(value = "bookAuthorToRemove") String bookAuthorToRemove) {
        if (bookService.removeBookByAuthor(bookAuthorToRemove)) {
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        } else {
            logger.info("incorrect Book ID to remove");
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove) {
        if (bookService.removeBookById(bookIdToRemove)) {
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        } else {
            logger.info("incorrect ID to remove");
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeAllTitles")
    public String removeBookTitles(@RequestParam(value = "bookTitleToRemove") String bookTitleToRemove) {
        if (bookService.removeBookByTitle(bookTitleToRemove)) {
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        } else {
            logger.info("incorrect Book title to remove");
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeAllSizes")
    public String removeBookSizes(@RequestParam(value = "bookSizeToRemove") Integer bookSizeToRemove) {
        if (bookService.removeBookBySize(bookSizeToRemove)) {
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        } else {
            logger.info("incorrect Book size to remove");
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/findAllBooks")
    public String findBookTitles(@RequestParam(required = false, value = "criteria") String criteria,
                                 @RequestParam(value = "nameCriteria") String nameCriteria) {
        bookService.findBook(criteria, nameCriteria);
        return "redirect:/books/shelf";
    }


}
