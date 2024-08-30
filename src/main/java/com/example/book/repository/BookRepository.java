package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    //Book c'est pour la classe de notre table
    //Integer est le type de données de notre id
}
