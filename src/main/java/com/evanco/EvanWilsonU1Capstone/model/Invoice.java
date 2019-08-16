package com.evanco.EvanWilsonU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Invoice {

    private int invoice_id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String item_type;
    private int item_id;
    private BigDecimal unit_price;
    private int quantity;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processing_fee;
    private BigDecimal total;

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

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

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(BigDecimal processing_fee) {
        this.processing_fee = processing_fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoice_id() == invoice.getInvoice_id() &&
                getItem_id() == invoice.getItem_id() &&
                getQuantity() == invoice.getQuantity() &&
                getName().equals(invoice.getName()) &&
                getStreet().equals(invoice.getStreet()) &&
                getCity().equals(invoice.getCity()) &&
                getState().equals(invoice.getState()) &&
                getZipcode().equals(invoice.getZipcode()) &&
                getItem_type().equals(invoice.getItem_type()) &&
                getUnit_price().equals(invoice.getUnit_price()) &&
                getSubtotal().equals(invoice.getSubtotal()) &&
                getTax().equals(invoice.getTax()) &&
                getProcessing_fee().equals(invoice.getProcessing_fee()) &&
                getTotal().equals(invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoice_id(), getName(), getStreet(), getCity(), getState(), getZipcode(), getItem_type(), getItem_id(), getUnit_price(), getQuantity(), getSubtotal(), getTax(), getProcessing_fee(), getTotal());
    }
}
