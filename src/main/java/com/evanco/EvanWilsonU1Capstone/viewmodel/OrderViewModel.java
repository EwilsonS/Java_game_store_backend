package com.evanco.EvanWilsonU1Capstone.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

public class OrderViewModel {

    //--------------------------------------------------------------------
    // Annotate each property to be not null and label them
    // private to adhere to data hiding practices - Public
    // interface, private implementation
    //--------------------------------------------------------------------
    @NotEmpty(message = "Please enter a name")
    private String name;
    @NotEmpty(message = "Please enter a street")
    private String street;
    @NotEmpty(message = "Please enter a city")
    private String city;
    @NotEmpty(message = "Please enter a state")
    @Size(min=2, max = 2, message = "State codes must be exactly 2 characters")
    private String state;
    @NotEmpty(message = "Please enter a zip code")
    private String zipcode;
    @NotEmpty(message = "Enter the Item type")
    private String item_type;
    @Min(value = 1)
    private int item_id;
    @Min(value = 1)
    private int quantity;

    //---------------------------------------------------------------------
    // Getters and Setters
    //---------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
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
        OrderViewModel that = (OrderViewModel) o;
        return getItem_id() == that.getItem_id() &&
                getQuantity() == that.getQuantity() &&
                getName().equals(that.getName()) &&
                getStreet().equals(that.getStreet()) &&
                getCity().equals(that.getCity()) &&
                getState().equals(that.getState()) &&
                getZipcode().equals(that.getZipcode()) &&
                getItem_type().equals(that.getItem_type());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZipcode(), getItem_type(), getItem_id(), getQuantity());
    }
}
