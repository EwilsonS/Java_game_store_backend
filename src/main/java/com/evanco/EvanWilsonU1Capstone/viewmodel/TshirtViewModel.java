package com.evanco.EvanWilsonU1Capstone.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class TshirtViewModel {
    //--------------------------------------------------------------------
    // Annotate each property to be not null and label them
    // private to adhere to data hiding practices - Public
    // interface, private implementation
    //--------------------------------------------------------------------
    private int t_shirt_id;
    @NotEmpty(message = "Please enter a valid size")
    @Size(min=1, max = 2, message = "Please enter XS, S, M, L, XL, XXL")
    private String size;
    @NotEmpty(message = "Enter a color por favor")
    private String color;
    @NotEmpty(message = "Enter a color por favor")
    private String description;
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "999.99", inclusive = true)
    private BigDecimal price;
    @Min(value = 0)
    private int quantity;

    //---------------------------------------------------------------------
    // Getters and Setters
    //---------------------------------------------------------------------

    public int getT_shirt_id() {
        return t_shirt_id;
    }

    public void setT_shirt_id(int t_shirt_id) {
        this.t_shirt_id = t_shirt_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        TshirtViewModel that = (TshirtViewModel) o;
        return getT_shirt_id() == that.getT_shirt_id() &&
                getQuantity() == that.getQuantity() &&
                getSize().equals(that.getSize()) &&
                getColor().equals(that.getColor()) &&
                getDescription().equals(that.getDescription()) &&
                getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getT_shirt_id(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
