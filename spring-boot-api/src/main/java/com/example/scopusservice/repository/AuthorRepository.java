package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author a left join a.organization where a.name = ?1")
    Page<Author> findAllByName(String name, Pageable pageable);

    // TODO: how to use regex
    @Query("select a from Author a left join a.organization where a.name like concat(?2, '%', ?1, '%')")
    Page<Author> findByNameAndName(String firstname, String lastname, Pageable pageable);

    @Query("select a from Author a left join a.organization where a.name like concat('%', ?1, '%')")
    Page<Author> findByName(String name, Pageable pageable);

    @Query("select a from Author a left join a.organization where a.name like concat(?2, '%', ?1, '%') " +
            "and a.hIndex between ?3 and ?4")
    Page<Author> findByNameLikeAndHIndexBetween(String firstname, String lastname, Long hIndexLow, Long hIndexHigh, Pageable pageable);

    @Query("select a from Author a left join a.organization where a.scopusId = ?1")
    Author findAuthorByScopusId(Long scopusId);
}
