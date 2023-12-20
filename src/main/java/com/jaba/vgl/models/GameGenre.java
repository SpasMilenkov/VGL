package com.jaba.vgl.models;

import java.util.Optional;

public enum GameGenre { //TODO: add more...
    RPG(1),
    HORROR(2),
    MMO(3),
    RACING(4);

    public final int id;

    GameGenre(int id) {
        this.id = id;
    }

    public static Optional<GameGenre> fromId(Long id) {
        for (GameGenre genre: values()) {
            if (genre.id == id) {
                return Optional.of(genre);
            }
        }
        return Optional.empty();
    }
}
