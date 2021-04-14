package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//FIXME удалите не нужные импорты
import javax.jws.WebParam;
import java.util.List;


@Controller
@RequestMapping(value = "books")
public class BookShelfController {
    //FIXME final
    private Logger logger = Logger.getLogger(BookShelfController.class);
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
        //FIXME проверку вынесите в отдельный метод в сервисе, котоырй возращает boolean, здесь вызывайте его
        if (book.getAuthor().length() > 0 || book.getTitle().length() > 0 || book.getSize() != null){
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
        //FIXME if не начто не влияет, используйте логгер для логики
        if (bookService.removeBookByAuthor(bookAuthorToRemove)) {
            return "redirect:/books/shelf";
        } else {
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove) {
        //FIXME if не начто не влияет, используйте логгер для логики
        if (bookService.removeBookById(bookIdToRemove)) {
            return "redirect:/books/shelf";
        } else {
            logger.info("incorrect ID to remove");
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeAllTitles")
    public String removeBookTitles(@RequestParam(value = "bookTitleToRemove") String bookTitleToRemove) {
        //FIXME if не начто не влияет, используйте логгер для логики
        if (bookService.removeBookByTitle(bookTitleToRemove)) {
            return "redirect:/books/shelf";
        } else {
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeAllSizes")
    public String removeBookSizes(@RequestParam(value = "bookSizeToRemove") Integer bookSizeToRemove) {
        //FIXME if не начто не влияет, используйте логгер для логики
        if (bookService.removeBookBySize(bookSizeToRemove)) {
            return "redirect:/books/shelf";
        } else {
            return "redirect:/books/shelf";
        }
    }
//FIXME если не нужно удалите
   /*@PostMapping("/findAllAuthors")
    public String findBookAuthors(@RequestParam(value = "bookAuthorToFind") String bookAuthorToFind) {
        bookService.findBookByAuthor(bookAuthorToFind);
        return "redirect:/books/shelf";
    }*/

    @PostMapping("/findAllBooks")
    public String findBookTitles(@RequestParam(required = false, value = "criteria") String criteria,
                                 @RequestParam(value = "nameCriteria") String nameCriteria) {
        bookService.findBook(criteria, nameCriteria);
        return "redirect:/books/shelf";
    }


}
