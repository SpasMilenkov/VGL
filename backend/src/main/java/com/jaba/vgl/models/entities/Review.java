package com.jaba.vgl.models.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
            name = "review_sequence",
            sequenceName = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "review_sequence"
    )
    @Column(name = "review_id")
    private Long id;

    @Column(name = "gamee_id")
    @NotNull(message = "Game id must not be empty.")
    private Long gameId;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "user_id")
    @NotNull(message = "User id must not be empty.")
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
            name = "steam_id",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Review text must not be empty.")
    private String steamId;
    @Column(
            name = "review_rating",
            nullable = false
    )
    @NotNull(message = "Review rating must not be empty.")
    @Min(value = 0 , message = "Value should be greater then then equal to 0")
    @Max(value = 5 , message = "Value should be less then then equal to 5")
    private Integer rating;

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
