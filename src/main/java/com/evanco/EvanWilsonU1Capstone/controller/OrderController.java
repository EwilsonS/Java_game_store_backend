package com.evanco.EvanWilsonU1Capstone.controller;

import com.evanco.EvanWilsonU1Capstone.model.Invoice;
import com.evanco.EvanWilsonU1Capstone.service.OrderService;
import com.evanco.EvanWilsonU1Capstone.viewmodel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice convertOrderToInvoice(@RequestBody @Valid OrderViewModel orderViewModel){
        return service.saveOrder(orderViewModel);
    }

    // For development only
    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices(){
        return service.getAllInvoices();
    }
}
