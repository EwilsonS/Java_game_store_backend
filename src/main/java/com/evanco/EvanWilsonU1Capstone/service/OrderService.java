package com.evanco.EvanWilsonU1Capstone.service;

import com.evanco.EvanWilsonU1Capstone.dao.*;
import com.evanco.EvanWilsonU1Capstone.model.*;
import com.evanco.EvanWilsonU1Capstone.viewmodel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class OrderService {

    // Field injection
    InvoiceDao invoiceDao;
    SalesTaxDao salesTaxDao;
    ProcessingFeeDao processingFeeDao;
    GameDao gameDao;
    ConsoleDao consoleDao;
    TshirtDao tshirtDao;

    // Constructor injection
    @Autowired
    public OrderService(InvoiceDao invoiceDao, SalesTaxDao salesTaxDao, ProcessingFeeDao processingFeeDao,
                        GameDao gameDao, ConsoleDao consoleDao, TshirtDao tshirtDao) {
        this.invoiceDao = invoiceDao;
        this.salesTaxDao = salesTaxDao;
        this.processingFeeDao = processingFeeDao;
        this.gameDao = gameDao;
        this.consoleDao = consoleDao;
        this.tshirtDao = tshirtDao;
    }

    // -------------------------------------------------------
    // Service methods
    // -------------------------------------------------------
    @Transactional
    public Invoice saveOrder(OrderViewModel ovm) {
        /*
         This is where we convert the OrderViewModel input to an Invoice object and
         return the invoice to the user
         */
        Invoice invoice = new Invoice(); // Instantiate new Invoice

        invoice.setName(ovm.getName()); // Set name

        invoice.setStreet(ovm.getStreet()); // Set street

        invoice.setCity(ovm.getCity()); // Set city

        setAndVerifyState(invoice, ovm); // Set and validate state

        invoice.setZipcode(ovm.getZipcode()); // Set zipcode

        invoice.setItem_type(ovm.getItem_type().toLowerCase()); // Set item_type

        invoice.setItem_id(ovm.getItem_id()); // Set item_id

        setPurchaseQty(invoice, ovm); // Set purchase quantity

        handleSwitchItemTypes(ovm, invoice); // Set unit_price, subtotal, and update existing quantities

        setTax(invoice, ovm); // Set tax

        setProcessingFee(invoice); // Set processing fee

        setTotal(invoice); // Set total

        invoice = invoiceDao.addInvoice(invoice); // Add invoice to db

        return invoice;
    }

    // -------------------------------------------------------
    // Helper methods
    // -------------------------------------------------------
    /**
     * Switch case to handle variable item_types
     * @param ovm type OrderViewModel
     * @param invoice type Invoice
     */
    private void handleSwitchItemTypes(OrderViewModel ovm, Invoice invoice) {
        BigDecimal price = new BigDecimal("0.0");
        int availableQty = 0;
        int newQty = 0;
        // Using switch case here for variable item_types
        switch (ovm.getItem_type().toLowerCase()) {
            case "game": {
                setGameUnitPriceSubtotalUpdateQty(ovm, invoice, price, availableQty, newQty);
                break;
            }
            case "console": {
                setConsoleUnitPriceSubtotalUpdateQty(ovm, invoice, price, availableQty, newQty);
                break;
            }
            case "t-shirt": {
                setTshirtUnitPriceSubtotalUpdateQty(ovm, invoice, price, availableQty, newQty);
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value [ " + ovm.getItem_type() +
                        " ] : Check your spelling. Acceptable Entries: Game, Console, T-shirt (not case sensitive)");
        }
    }

    /**
     * Set unit_price, subtotal, and update existing quantities for case "game"
     * @param ovm type OrderViewModel
     * @param invoice type Invoice
     * @param price type Big Decimal
     * @param availableQty Type int
     * @param newQty type int
     */
    private void setGameUnitPriceSubtotalUpdateQty(OrderViewModel ovm, Invoice invoice, BigDecimal price, int availableQty, int newQty) {
        if (gameDao.getGameById(ovm.getItem_id()) == null) {
            throw new NullPointerException("ID " + ovm.getItem_id() + " does not exist in " + ovm.getItem_type() + " table");
        }
        price = gameDao.getGameById(ovm.getItem_id()).getPrice();
        invoice.setItem_type("Game"); // sanitize data to match db
        invoice.setUnit_price(price);
        invoice.setSubtotal(price.multiply(new BigDecimal(invoice.getQuantity())).setScale(2, RoundingMode.HALF_UP));

        // Compare available qty and requested qty
        availableQty = gameDao.getGameById(ovm.getItem_id()).getQuantity();
        if (ovm.getQuantity() > availableQty) {
            throw new IllegalArgumentException("Uh oh, looks like we have  " + availableQty + " in stock!");
        } else {
            // Adjust dao as necessary to reflect recent purchase
            newQty = availableQty - ovm.getQuantity();
            Game updatedGame = gameDao.getGameById(ovm.getItem_id());
            updatedGame.setQuantity(newQty);
            gameDao.updateGame(updatedGame);
        }

    }

    /**
     * Set unit_price, subtotal, and update existing quantities for case "console"
     * @param ovm type OrderViewModel
     * @param invoice type Invoice
     * @param price type Big Decimal
     * @param availableQty Type int
     * @param newQty type int
     */
    private void setConsoleUnitPriceSubtotalUpdateQty(OrderViewModel ovm, Invoice invoice, BigDecimal price, int availableQty, int newQty) {
        if (consoleDao.getConsoleById(ovm.getItem_id()) == null) {
            throw new NullPointerException("ID " + ovm.getItem_id() + " does not exist in " + ovm.getItem_type() + " table");
        }
        price = consoleDao.getConsoleById(ovm.getItem_id()).getPrice();
        invoice.setItem_type("Console"); // sanitize data to match db
        invoice.setUnit_price(price);
        invoice.setSubtotal(price.multiply(new BigDecimal(invoice.getQuantity())).setScale(2, RoundingMode.HALF_UP));

        // Compare available qty and requested qty
        availableQty = consoleDao.getConsoleById(ovm.getItem_id()).getQuantity();
        if (ovm.getQuantity() > availableQty) {
            throw new IllegalArgumentException("Uh oh, looks like we have  " + availableQty + " in stock!");
        } else {
            // Adjust dao as necessary to reflect recent purchase
            newQty = availableQty - ovm.getQuantity();
            Console updatedConsole = consoleDao.getConsoleById(ovm.getItem_id());
            updatedConsole.setQuantity(newQty);
            consoleDao.updateConsole(updatedConsole);
        }

    }

    /**
     * Set unit_price, subtotal, and update existing quantities for case "t-shirt"
     * @param ovm type OrderViewModel
     * @param invoice type Invoice
     * @param price type Big Decimal
     * @param availableQty Type int
     * @param newQty type int
     */
    private void setTshirtUnitPriceSubtotalUpdateQty(OrderViewModel ovm, Invoice invoice, BigDecimal price, int availableQty, int newQty) {
        if (tshirtDao.getTshirtById(ovm.getItem_id()) == null) {
            throw new NullPointerException("ID " + ovm.getItem_id() + " does not exist in " + ovm.getItem_type() + " table");
        }
        price = tshirtDao.getTshirtById(ovm.getItem_id()).getPrice();
        invoice.setItem_type("T-Shirt"); // sanitize data to match db
        invoice.setUnit_price(price);
        invoice.setSubtotal(price.multiply(new BigDecimal(invoice.getQuantity())).setScale(2, RoundingMode.HALF_UP));

        // Compare available qty and requested qty
        availableQty = tshirtDao.getTshirtById(ovm.getItem_id()).getQuantity();
        if (ovm.getQuantity() > availableQty) {
            throw new IllegalArgumentException("Uh oh, looks like we have  " + availableQty + " in stock!");
        } else {
            // Adjust dao as necessary to reflect recent purchase
            newQty = availableQty - ovm.getQuantity();
            Tshirt updatedTshirt = tshirtDao.getTshirtById(ovm.getItem_id());
            updatedTshirt.setQuantity(newQty);
            tshirtDao.updateTshirt(updatedTshirt);
        }
    }

    /**
     * Set processing fee and handle fee for orders over 10 items
     * @param invoice type Invoice
     */
    private void setProcessingFee(Invoice invoice){
        if (invoice.getQuantity() <= 10) {
            invoice.setProcessing_fee(
                    processingFeeDao.processingFee(invoice.getItem_type())
            );
        } else {
            invoice.setProcessing_fee(
                    processingFeeDao
                            .processingFee(invoice.getItem_type())
                            .add(new BigDecimal("15.49").setScale(2, RoundingMode.HALF_UP))
            );
            System.out.println("** $15.49 processing fee added **");
        }
    }

    /**
     * Set total
     * @param invoice type Invoice
     */
    private void setTotal(Invoice invoice){
        BigDecimal total = invoice.getSubtotal().add(invoice.getTax()).add(invoice.getProcessing_fee());
        if (total.compareTo(new BigDecimal("999.99")) < 0) {
            invoice.setTotal(total.setScale(2, RoundingMode.HALF_UP));
        } else {
            throw new IllegalArgumentException("Your order total of $" + total + " has exceeded the maximum purchase amount of $999.99");
        }
    }

    /**
     * Set tax
     * @param invoice type Invoice
     * @param ovm type OrderViewModel
     */
    private void setTax(Invoice invoice, OrderViewModel ovm){
        if (salesTaxDao.calculateTax(invoice.getState()) != null) {
            invoice.setTax(
                    salesTaxDao.calculateTax(invoice.getState())
                            .multiply(invoice.getSubtotal())
                            .setScale(2, RoundingMode.HALF_UP)
            );
        } else {
            throw new IllegalArgumentException("Enter a valid state code: " + ovm.getState().toUpperCase() + " does not exist");
        }
    }

    /**
     * Loop through salestaxdao and match users state code to db
     * @param invoice type Invoice
     * @param ovm type OrderViewModel
     */
    private void setAndVerifyState(Invoice invoice, OrderViewModel ovm){
        List<SalesTaxRate> states = salesTaxDao.getAllStates();
        for (int i = 0; i < states.size(); i++) {
            if (!ovm.getState().toUpperCase().equals(states.get(i).getState())) {
                continue;
            }
            if (ovm.getState().toUpperCase().equals(states.get(i).getState())) {
                invoice.setState(states.get(i).getState().toUpperCase());
                break;
            }
        }
    }

    /**
     * Verify that purchase qty is not 0 or negative
     * @param invoice type Invoice
     * @param ovm type OrderViewModel
     */
    private void setPurchaseQty(Invoice invoice, OrderViewModel ovm){
        if (ovm.getQuantity() > 0) {
            invoice.setQuantity(ovm.getQuantity());
        } else {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }

    /**
     * Get all invcices
     * @return List of invoices
     */
    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoices();
    }

    // -------------------------------------------------------
    // BuildViewModel helper methods
    // -------------------------------------------------------
    public OrderViewModel buildOrderViewModel(Invoice invoice) {
        OrderViewModel ovm = new OrderViewModel();
        ovm.setItem_id(invoice.getItem_id());
        ovm.setName(invoice.getName());
        ovm.setStreet(invoice.getStreet());
        ovm.setState(invoice.getState());
        ovm.setZipcode(invoice.getZipcode());
        ovm.setItem_type(invoice.getItem_type());
        ovm.setItem_id(invoice.getItem_id());
        ovm.setQuantity(invoice.getQuantity());
        return ovm;
    }
}
