package com.evanco.EvanWilsonU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Game {
    private int game_id;
    private String title;
    private String esrb_rating;
    private String description;
    private BigDecimal price;
    private String studio;
    private int quantity;


    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrb_rating() {
        return esrb_rating;
    }

    public void setEsrb_rating(String esrb_rating) {
        this.esrb_rating = esrb_rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getGame_id() == game.getGame_id() &&
                getQuantity() == game.getQuantity() &&
                getTitle().equals(game.getTitle()) &&
                getEsrb_rating().equals(game.getEsrb_rating()) &&
                getDescription().equals(game.getDescription()) &&
                getPrice().equals(game.getPrice()) &&
                getStudio().equals(game.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame_id(), getTitle(), getEsrb_rating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }
}
