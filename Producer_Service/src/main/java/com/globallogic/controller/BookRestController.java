package com.globallogic.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.model.Book;

@RestController
@RequestMapping("/book")
public class BookRestController {

      @Autowired
      Environment environment;

      @GetMapping("/data")
      public String getBookData() {

         return "data of BOOK-SERVICE, Running on port: "
           +environment.getProperty("local.server.port");
      }

      @GetMapping("/{id}")
      public Book getBookById(@PathVariable Integer id) {
         return new Book(id, "Java", 500.75);
      }

      @GetMapping("/all")
      public List<Book> getAll(){
         return List.of(
                new Book(501, "Java", 439.75),
                new Book(502, "Spring ", 340.75),
                new Book(503, "Hibernate", 355.75)
         );
      }

      @GetMapping("/entity")
      public ResponseEntity<String> getEntityData() {
         return new ResponseEntity<String>("Hello from BookRestController",null, HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED);
      }
}