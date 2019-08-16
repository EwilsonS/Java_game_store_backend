package com.evanco.EvanWilsonU1Capstone.viewmodel;

import org.apache.tomcat.jni.BIOCallback;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {
    //--------------------------------------------------------------------
    // Annotate each property to be not null and label them
    // private to adhere to data hiding practices - Public
    // interface, private implementation
    //--------------------------------------------------------------------
    private int game_id;
    @NotEmpty(message = "Title cannot be left empty")
    private String title;
    @NotEmpty(message = "Esrb rating cannot be left empty")
    private String esrb_rating;
    @NotEmpty(message = "Description cannot be left empty")
    private String description;
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "999.99", inclusive = true)
    private BigDecimal price;
    @NotEmpty(message = "Studio cannot be left empty")
    private String studio;
    @Min(value = 0)
    private int quantity;

    //---------------------------------------------------------------------
    // Getters and Setters
    //---------------------------------------------------------------------
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

    //---------------------------------------------------------------------
    // equals() and hasCode()
    //---------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameViewModel that = (GameViewModel) o;
        return getGame_id() == that.getGame_id() &&
                getTitle().equals(that.getTitle()) &&
                getEsrb_rating().equals(that.getEsrb_rating()) &&
                getDescription().equals(that.getDescription()) &&
                getPrice().equals(that.getPrice()) &&
                getStudio().equals(that.getStudio()) &&
                getQuantity() == that.getQuantity();

    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame_id(), getTitle(), getEsrb_rating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }
}
