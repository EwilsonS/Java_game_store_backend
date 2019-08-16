package com.evanco.EvanWilsonU1Capstone.controller;

import com.evanco.EvanWilsonU1Capstone.exception.NotFoundException;
import com.evanco.EvanWilsonU1Capstone.model.Console;
import com.evanco.EvanWilsonU1Capstone.service.ManageInventoryService;
import com.evanco.EvanWilsonU1Capstone.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consoles")
public class ConsoleController {

    @Autowired
    ManageInventoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel console){
        return service.saveConsole(console);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles() {
        return service.findAllConsoles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int id) {
        if (service.findConsoleById(id) == null){
            throw new NotFoundException("Console could not be retrieved for id " + id);
        }
        return service.findConsoleById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@PathVariable("id") int id, @RequestBody ConsoleViewModel cvm){
        if(cvm.getConsole_id() == 0){
            cvm.setConsole_id(id);
        }
        if(id != cvm.getConsole_id()){
            throw new IllegalArgumentException("Id on path must match cvm id");
        }
        service.updateConsole(cvm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable("id") int id){
        service.deleteConsole(id);
    }

    @GetMapping("/manufacturer?{mfr}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsolesByMfr(@PathVariable("mfr") String mfr){
        if (service.findConsolesByMfr(mfr).size() == 0) {
            throw new NotFoundException("There are no consoles made by " + mfr);
        }
        return service.findConsolesByMfr(mfr);
    }
}
