package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private final List<Book> repoCopy = new ArrayList<>();

    @Override
    //FIXME орфографическая ошибка
    public List<Book> retreiveAll() {

        return new ArrayList<>(repo);
    }



    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book : " + book);
        repo.add(book);
    }


    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByAuthor(String bookAuthorToRemove) { // метод удаления всех книг по автору
        boolean found = false;
        for (Book book : retreiveAll()){
            if (book.getAuthor().equals(bookAuthorToRemove)){
                logger.info("remove book completed " + book);
                repo.remove(book);
                found = true;
            }
        }
        if (!found)
            logger.info("incorrect book_author to remove! Try again!");
        return found;
    }

    @Override
    public boolean removeItemByTitle(String bookTitleToRemove) { // метод удаления всех книг по названию
        boolean found = false;
        for (Book book : retreiveAll()){
            if (book.getTitle().equals(bookTitleToRemove)){
                logger.info("remove book completed " + book);
                repo.remove(book);
                found = true;
            }
        }
        if (!found)
            logger.info("incorrect book_title to remove! Try again!");
        return found;
    }

    @Override
    public boolean removeItemBySize(Integer bookSizeToRemove) { // метод удаления всех книг по размеру
        boolean found = false;
        for (Book book : retreiveAll()){
            if (book.getSize() == bookSizeToRemove){
                logger.info("remove book completed " + book);
                repo.remove(book);
                found = true;
            }
        }
        if (!found)
            logger.info("incorrect book_title to remove! Try again!");
        return found;
    }

    @Override
    public void findItem(String criteria, String name) { // метод по поиску нужных книг

        if (repoCopy.size() > 0) { // в repocopy всегда содержатся все книги, чтобы потом их отобразить
            repo.clear();
            for (Book book : repoCopy)
                repo.add(book);
            repoCopy.clear();
        }
        if (criteria == null || name == null) {
            retreiveAll();
        }
        else {
            switch (criteria) {
                case "authors" : {
                    for (Book book : retreiveAll()){
                        repoCopy.add(book);
                        if (!book.getAuthor().equals(name)){
                            repo.remove(book); // фильтрация книг происходит путем удаления ненужных из repo
                        }
                    }
                    break;
                }

                case "titles" : {
                    for (Book book : retreiveAll()){
                        repoCopy.add(book);
                        if (!book.getTitle().equals(name)){
                            repo.remove(book); // фильтрация книг происходит путем удаления ненужных из repo
                        }
                    }
                    break;
                }

                case "pages" : {
                    for (Book book : retreiveAll()){
                        repoCopy.add(book);
                        logger.info(book.getSize() + "-" + name + "-");
                        if (book.getSize() != Integer.parseInt(name)){
                            repo.remove(book); // фильтрация книг происходит путем удаления ненужных из repo
                        }
                    }
                    break;
                }
            }
        }



    }
}
