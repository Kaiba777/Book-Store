package com.example.book.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.book.entity.Book;
import com.example.book.entity.MyBookList;
import com.example.book.service.BookService;
import com.example.book.service.MyBookListService;






@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        List<Book>list=service.getAllBook();
        //ModelAndView m=new ModelAndView();
        //m.setViewName("bookList");
        //m.addObject("book", list)
        return new ModelAndView("bookList", "book", list);
        //bookList est le fichier html qui va afficher nos données
        //book est le nom qui va permet de pouvoir afficher nos données
    }

    //Comme le formulaire est en post
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        //la methode save provient de BookService
        service.save(b);
        //Après le traitement des données nous redirigeons cette fonction  vers une url
        return "redirect:/available_books";
    }
    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList>list=myBookService.getAllMyBooks();
        model.addAttribute("book", list);
        return "myBooks";
    }
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book b=service.getBookById(id);
        MyBookList mb=new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
        myBookService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book b=service.getBookById(id);
        model.addAttribute("book", b);
        //book c'est pour pouvoir modifier nos données dans le fichier html
        //bookEdit est le fichier html qui va permet de modifier nos données
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        service.deleteById(id);
        //redirige vers la page available_books
        return "redirect:/available_books";
    }
    
    
    

}
