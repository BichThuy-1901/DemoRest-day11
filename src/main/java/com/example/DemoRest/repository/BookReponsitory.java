package com.example.DemoRest.repository;

import com.example.DemoRest.entity.Bookentity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookReponsitory {
    private List<Bookentity> bookList = new ArrayList();

    @PostConstruct
    public void init(){
        bookList.add(new Bookentity(1, "Java A-Z", 13.2, "Roger"));
        bookList.add(new Bookentity(2, ".Net tutorial", 23.9, "Peter"));
    }
    public List<Bookentity> findAll() {
        return bookList;
    }

    public Bookentity save (Bookentity bookentity) {
        bookList.add(bookentity);
        return bookentity;
    }

    public Bookentity update(Bookentity newBookData){
        boolean isFound = false;
        Bookentity foundBook = null;
        //check if book Id is existing
        for(Bookentity book: bookList){
            if(book.getId() == newBookData.getId()){
                isFound = true;
                foundBook = book;
                break;
            }
        }
        if(!isFound){
            return null;
        }
        bookList.remove(foundBook);
        newBookData.setId(foundBook.getId());
        bookList.add(newBookData);
        return newBookData;
    }

    public boolean delete(int bookID){
        boolean isFound = false;
        Bookentity foundBook = null;
        //check if book id is existing
        for(Bookentity book: bookList){
            if(book.getId() == bookID){
                isFound = true;
                foundBook = book;
                break;
            }
        }
        if(!isFound){
            return false;
        }
        bookList.remove(foundBook);
        return true;
    }
}
