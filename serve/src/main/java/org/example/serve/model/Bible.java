package org.example.serve.model;
import jakarta.persistence.*;

@Entity
public class Bible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bibleID;

    private String book;
    private String chapter;
    private String verse;

    @Lob
    private String content;

}