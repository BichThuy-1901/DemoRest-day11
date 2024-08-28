package com.example.DemoRest.controller;

import com.example.DemoRest.entity.Bookentity;
import com.example.DemoRest.repository.BookReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

@RestController
@RequestMapping (value = "/")
public class BookController {
    @Autowired
    BookReponsitory bookReponsitory;
    @RequestMapping (method = RequestMethod.GET)
    public Object getAllBook() {
        List<Bookentity> bookEntityList = bookReponsitory.findAll();

        return bookEntityList;
    }
    @RequestMapping (method = RequestMethod.POST)
    public Object addNewBook(@RequestBody Bookentity newBookentity) {
        Bookentity result = bookReponsitory.save(newBookentity);

        return result;
    }

    @PutMapping
    public Object updateBook(@RequestBody Bookentity updateBookEntity) {
        Bookentity result = bookReponsitory.update(updateBookEntity);
        if (result == null) {
            Map<String, String> error = new HashMap<String, String>() {{
                put("error", updateBookEntity.getId() + " does not exist");
            }};
            return error;
        }
        return result;
    }

    @RequestMapping(value = "/{bookID}", method = RequestMethod.DELETE)
    public Object deleteBook(@PathVariable(value="bookID") String bookID){
        Boolean result=bookReponsitory.delete(Integer.valueOf(bookID));
        if(!result){
            Map<String,String> error=new HashMap<String,String>(){{
                put("error", "A book which book Id= "+bookID + " does not exist");
            }};
            return error;
        } else {
            Map<String,String> success=new HashMap<String,String>(){{
                put("success", "A book which book Id= "+bookID + " has been deleted successfully");
            }};
            return success;
        }
    }
}
