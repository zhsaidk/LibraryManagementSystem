    package com.zhsaidk.database.repository;

    import com.zhsaidk.database.Entity.Person;
    import org.springframework.data.jpa.repository.EntityGraph;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;

    import java.util.List;

    public interface PersonRepository extends JpaRepository<Person, Long> {

        @EntityGraph(attributePaths = "books")
        List<Person> findAll();
    }
