package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;
    //Permet de sauvegarder les données dans la table Book
    public void save(Book b) {
        //Book est la classe de la table Book
        bRepo.save(b);
    }

    //Permet de récupérer tous les données de Book dans la base de données
    public List<Book> getAllBook() {
        return bRepo.findAll();
        //la méthode findAll() permet de récupérer des données par l'id
    }

    //Book c'est pour la table Book
    public Book getBookById(int id) {
        return bRepo.findById(id).get();
    }
    //Pour supprimer un livre
    public void deleteById(int id) {
        bRepo.deleteById(id);
    }
}
