package com.jaba.vgl.models.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Review")
@Table(name = "review_table")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Review {

    @Id
    @SequenceGenerator(
            name = "vgl_sequence",
            sequenceName = "vgl_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vgl_sequence"
    )
    @Column(name = "review_id")
    private Long id;

    @Column(name = "game_id")
    @NotNull(message = "Game id must not be empty.")
    private Long gameId;

    @Column(name = "user_id")
    @NotNull(message = "Game id must not be empty.")
    private Long userId;

    @Column(
            name = "review_title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Review title must not be empty.")
    private String title;

    @Column(
            name = "review_text",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Review text must not be empty.")
    private String text;

    @Column(
            name = "review_rating",
            nullable = false
    )
    @NotNull(message = "Review rating must not be empty.")
    private Float rating;

    @ManyToOne
    @JoinColumn(
            name = "game_id",
            referencedColumnName = "id",
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(
                    name = "game_id_fk"
            )
    )
    private GameDetails gameDetails;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(
                    name = "user_id_fk"
            )
    )
    private User user;
}
