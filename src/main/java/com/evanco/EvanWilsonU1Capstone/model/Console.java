package com.evanco.EvanWilsonU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Console {

    private int console_id;
    private String model;
    private String manufacturer;
    private String memory_amount;
    private String processor;
    private BigDecimal price;
    private int quantity;

    public int getConsole_id() {
        return console_id;
    }

    public void setConsole_id(int console_id) {
        this.console_id = console_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemory_amount() {
        return memory_amount;
    }

    public void setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return getConsole_id() == console.getConsole_id() &&
                getQuantity() == console.getQuantity() &&
                getModel().equals(console.getModel()) &&
                getManufacturer().equals(console.getManufacturer()) &&
                getMemory_amount().equals(console.getMemory_amount()) &&
                getProcessor().equals(console.getProcessor()) &&
                getPrice().equals(console.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsole_id(), getModel(), getManufacturer(), getMemory_amount(), getProcessor(), getPrice(), getQuantity());
    }
}
