package com.scorefilms.models;

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
public class Directors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private int birthday;
    private int die;
    private String poster;
    @Column(length = 5000)
    private String description;
    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sessions> sessions;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;

    public Directors(String name, int birthday, int die, String description, String poster) {
        this.name = name;
        this.birthday = birthday;
        this.die = die;
        this.description = description;
        this.poster = poster;
    }

    public void addSession(Sessions session) {
        this.sessions.add(session);
        session.setDirector(this);
    }

    public void removeSession(Sessions session) {
        this.sessions.remove(session);
        session.setDirector(null);
    }
}
