package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Keyword {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "keyword_id_seq",
            sequenceName = "keyword_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "keyword_id_seq"
    )
    private Long id;

    @Basic(optional = false)
    @Column(name = "word", nullable = false, unique = true)
    private String word;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return Objects.equals(id, keyword.id) && Objects.equals(word, keyword.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word);
    }
}
