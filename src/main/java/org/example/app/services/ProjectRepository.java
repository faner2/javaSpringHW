package org.example.app.services;

import org.example.web.dto.Book;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean removeItemByAuthor(String bookAuthorToRemove);

    boolean removeItemByTitle(String bookTitleToRemove);

    boolean removeItemBySize(Integer bookSizeToRemove);

    boolean removeItemById(Integer bookIdToRemove);

    void findItem(String criteria, String name);

}
