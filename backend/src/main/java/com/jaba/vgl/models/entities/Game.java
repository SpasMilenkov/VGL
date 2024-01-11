package com.jaba.vgl.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "Game")
@Table(name = "game_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game {

    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Name must not be empty.")
    private String name;

    @Column(
            name = "steam_id",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Name must not be empty.")
    private Long steamId;
    @Column(
            name = "release_date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Name must not be empty.")
    private Date releaseDate;

    @Column(
            name = "stduio_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Name must not be empty.")
    private String studioName;

    @ManyToMany(mappedBy = "games")
    Set<User> users;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Achievement> achievements;

    @ManyToMany
    @JoinTable(
            name = "wish_game_table", // This table will contain the association between wishes and games
            joinColumns = @JoinColumn(name = "wish_id"), // Column for wish
            inverseJoinColumns = @JoinColumn(name = "game_id")) // Column for game
    private Set<Wish> wishes;
}
