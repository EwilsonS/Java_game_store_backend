package com.evanco.EvanWilsonU1Capstone.controller;

import com.evanco.EvanWilsonU1Capstone.exception.NotFoundException;
import com.evanco.EvanWilsonU1Capstone.model.Tshirt;
import com.evanco.EvanWilsonU1Capstone.service.ManageInventoryService;
import com.evanco.EvanWilsonU1Capstone.viewmodel.TshirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tshirts")
public class TshirtController {

    @Autowired
    ManageInventoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TshirtViewModel createTshirt(@RequestBody @Valid TshirtViewModel tshirt){
        return service.saveTshirt(tshirt);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TshirtViewModel> getAllTshirts() {
        return service.findAllTshirts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TshirtViewModel getTshirt(@PathVariable("id") int id) {
        if(service.findTshirtById(id) == null){
            throw new NotFoundException("T-shirt could not be found for ID: " + id);
        }
        return service.findTshirtById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@PathVariable("id") int id, @RequestBody TshirtViewModel tvm){
        if(tvm.getT_shirt_id() == 0){
            tvm.setT_shirt_id(id);
        }
        if(id != tvm.getT_shirt_id()){
            throw new IllegalArgumentException("Id on path must match tvm id");
        }
        service.updateTshirt(tvm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable("id") int id){
        service.deleteTshirt(id);
    }

    @GetMapping("/color?{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TshirtViewModel> getTshirtsByColor(@PathVariable("color") String color){
        if(service.findTshirtsByColor(color).size() == 0){
            throw new NotFoundException("There are no shirts with the color: " + color);
        }
        return service.findTshirtsByColor(color);
    }

    @GetMapping("/size?{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TshirtViewModel> getTshirtsBySize(@PathVariable("size") String size){
        if(service.findTshirtsBySize(size).size() == 0){
            throw new NotFoundException("There are no shirts of size: " + size);
        }
        return service.findTshirtsBySize(size);
    }
}
