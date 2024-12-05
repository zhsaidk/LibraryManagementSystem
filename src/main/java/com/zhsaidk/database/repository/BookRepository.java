package com.zhsaidk.database.repository;

import com.zhsaidk.database.Entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.person is null")
    List<Book> findFreeBooks();

    @Query("select b from Book b where b.person is not null")
    List<Book> findNotFreeBooks();

    @EntityGraph(attributePaths = {"person", "author"})
    List<Book> findAll();

    @EntityGraph(attributePaths = {"person", "author"})
    Optional<Book> findById(Long id);
}
