package com.scorefilms.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Sessions session;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;

    public Carts(Sessions session, Users owner) {
        this.session = session;
        this.owner = owner;
    }
}
