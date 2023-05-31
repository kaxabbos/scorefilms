package com.scorefilms.models;

import com.scorefilms.models.enums.Genre;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String pub;
    @Column(length = 5000)
    private String description;
    private String date;
    private String poster;
    private String[] screenshots;
    private int year;
    private int count;
    private int price;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne(fetch = FetchType.LAZY)
    private Directors director;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comments> comments;

    private int acting;
    private int actingCount;
    private int music;
    private int musicCount;
    private int plot;
    private int plotCount;

    public Sessions(String name, String pub, String description, String date, String poster, String[] screenshots, int year, int price, Genre genre) {
        this.name = name;
        this.pub = pub;
        this.description = description;
        this.date = date;
        this.poster = poster;
        this.screenshots = screenshots;
        this.year = year;
        this.count = 0;
        this.genre = genre;
        this.price = price;
        this.acting = 0;
        this.actingCount = 0;
        this.music = 0;
        this.musicCount = 0;
        this.plot = 0;
        this.plotCount = 0;
    }

    public void addComment(Comments comment) {
        comments.add(comment);
        comment.setBook(this);
    }

    public void score(int acting, int music, int plot) {
        actingCount++;
        musicCount++;
        plotCount++;
        this.acting += acting;
        this.music += music;
        this.plot += plot;

    }
}
