package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Invoice;
import java.util.List;

public interface InvoiceDao {
    /**
     * Create an Invoice Object to be returned to the end user. Invoice contains fields that
     * require information generated in the service layer's business logic. This DAO along with
     * Tax and Processing will be implemented in InvoiceJdbcTemplateImpl to build the Invoice object.
     * @param invoice type Invoice
     * @return Invoice object
     */
    Invoice addInvoice(Invoice invoice);

    /**
     * Read all Invoice objects in db. This method is not necessary, its use will be for ease of
     * testing in PostMan and integration testing.
     * @return List<Invoice> invoiceList
     */
    List<Invoice> getAllInvoices();

    void deleteInvoice(int id);
}
