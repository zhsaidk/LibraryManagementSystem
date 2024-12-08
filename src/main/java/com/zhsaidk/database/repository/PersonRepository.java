    package com.zhsaidk.database.repository;

    import com.zhsaidk.database.Entity.Person;
    import org.springframework.data.jpa.repository.EntityGraph;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;
    import java.util.Optional;

    public interface PersonRepository extends JpaRepository<Person, Long> {

        @EntityGraph(attributePaths = {"books", "books.author"})
        List<Person> findAll();

        @EntityGraph(attributePaths = {"books"})
        Optional<Person> findById(Long id);

        Optional<Person> findByEmail(String email);

    }
