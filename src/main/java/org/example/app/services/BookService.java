package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {

        return bookRepo.retreiveAll();
    }


    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookByAuthor(String bookAuthorToRemove) {
        return bookRepo.removeItemByAuthor(bookAuthorToRemove);
    }

    public boolean removeBookByTitle(String bookTitleToRemove) {
        return bookRepo.removeItemByTitle(bookTitleToRemove);
    }

    public boolean removeBookBySize(Integer bookSizeToRemove) {
        return bookRepo.removeItemBySize(bookSizeToRemove);
    }

    public void findBook(String criteria, String nameCriteria) { // метод поиска нужных книг по критерию (название, автор или кол-во страниц) и наименованию

        bookRepo.findItem(criteria, nameCriteria);
    }


}
