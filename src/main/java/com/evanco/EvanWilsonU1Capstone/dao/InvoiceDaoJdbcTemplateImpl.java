package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Invoice;
import com.evanco.EvanWilsonU1Capstone.model.ProcessingFee;
import com.evanco.EvanWilsonU1Capstone.model.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao, ProcessingFeeDao, SalesTaxDao {

    // Init Jdbc
    private JdbcTemplate jdbcTemplate;

    // Prepared statements
    private static final String INSERT_INVOICE_SQL =
            "insert into invoice " +
                    "(name, street, city, state, zipcode, item_type, item_id, unit_price," +
                    "quantity, subtotal, tax, processing_fee, total) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    private static final String SELECT_PROCESSING_FEE_BY_TYPE_SQL =
            "select *from processing_fee where product_type=?";

    private static final String SELECT_TAX_BY_STATE_SQL =
            "select * from sales_tax_rate where state=?";

    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id=?";

    private static final String SELECT_ALL_STATES_SQL =
            "select * from sales_tax_rate";

    // Constructor injection
    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Interface implementation

    /**
     * Create an Invoice Object to be returned to the end user. Invoice contains fields that
     * require information generated in the service layer's business logic. This DAO along with
     * Tax and Processing will be implemented in InvoiceJdbcTemplateImpl to build the Invoice object.
     *
     * @param invoice type Invoice
     * @return Invoice object
     */
    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(
                INSERT_INVOICE_SQL,
                invoice.getName(),
                invoice.getStreet(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipcode(),
                invoice.getItem_type(),
                invoice.getItem_id(),
                invoice.getUnit_price(),
                invoice.getQuantity(),
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getProcessing_fee(),
                invoice.getTotal()
        );

        invoice.setInvoice_id(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return invoice;
    }

    /**
     * Read all Invoice objects in db. This method is not necessary, its use will be for ease of
     * testing in PostMan and integration testing.
     *
     * @return List<Invoice> invoiceList
     */
    @Override
    public List<Invoice> getAllInvoices() {
        try {
            return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieve processing fee and fill invoice field. This is not to be included with taxes.
     * Consoles: 14.99
     * T-Shirts: 1.98
     * Games: 1.49
     * If order quantity of any item is > 10, include additional $15.49
     *
     * @param productType string populated from Order view Model.
     * @return cost type BigDecimal based on type
     */
    @Override
    public BigDecimal processingFee(String productType) {
        try {
            return
                    jdbcTemplate.queryForObject(
                            SELECT_PROCESSING_FEE_BY_TYPE_SQL,
                            this::mapRowToProcessingFe,
                            productType).getFee();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * calculateTax will be implemented in the InvoiceDaoJdbcTemplateImpl. It takes in a state
     * passed in from the Order View Model
     *
     * @param state type string validate 2 characters only
     * @return tax decimal from db based on the state. (data already provided and never manipulated)
     */
    @Override
    public BigDecimal calculateTax(String state) {
        try {
            return
                    jdbcTemplate.queryForObject(
                            SELECT_TAX_BY_STATE_SQL,
                            this::mapRowToSalesTaxRate,
                            state).getRate();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteInvoice(int id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);
    }

    @Override
    public List<SalesTaxRate> getAllStates() {
        return jdbcTemplate.query(SELECT_ALL_STATES_SQL, this::mapRowToSalesTaxRate);
    }

    // Helper methods to map properties to rows
    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));
        return salesTaxRate;
    }

    private ProcessingFee mapRowToProcessingFe(ResultSet rs, int rowNum) throws SQLException {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProduct_type(rs.getString("product_type"));
        processingFee.setFee(rs.getBigDecimal("fee"));
        return processingFee;
    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(rs.getInt("invoice_id"));
        invoice.setName(rs.getString("name"));
        invoice.setStreet(rs.getString("street"));
        invoice.setCity(rs.getString("city"));
        invoice.setState(rs.getString("state"));
        invoice.setZipcode(rs.getString("zipcode"));
        invoice.setItem_type(rs.getString("item_type"));
        invoice.setItem_id(rs.getInt("item_id"));
        invoice.setUnit_price(rs.getBigDecimal("unit_price"));
        invoice.setQuantity(rs.getInt("quantity"));
        invoice.setSubtotal(rs.getBigDecimal("subtotal"));
        invoice.setTax(rs.getBigDecimal("tax"));
        invoice.setProcessing_fee(rs.getBigDecimal("processing_fee"));
        invoice.setTotal(rs.getBigDecimal("total"));
        return invoice;
    }
}
