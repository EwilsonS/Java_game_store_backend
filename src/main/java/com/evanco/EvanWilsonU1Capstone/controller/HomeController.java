package com.evanco.EvanWilsonU1Capstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> routes() {
        Map<String, String> routes = new LinkedHashMap<>();
        routes.put("/games", "........................ POST new game, GET all games");
        routes.put("/games/{id}", "................... GET, PUT, Delete by id");
        routes.put("/games/title?{title}", ".......... GET list of games by title name");
        routes.put("/games/studio?{studio}", "........ GET list of games by studio name");
        routes.put("/games/esrb_rating?{rating}", "... GET list of games by rating");
        routes.put("/consoles", "..................... POST new console, GET all consoles");
        routes.put("/consoles/{id}", "................ GET, PUT, Delete console by id");
        routes.put("/consoles/manufacturer?{mfr}", ".. GET list of consoles by manufacturer id");
        routes.put("/tshirts", "...................... POST new t-shirt, GET all t-shirts");
        routes.put("/tshirts/{id}", "................. GET, Put, Delete t-shirt by id");
        routes.put("/tshirts/color?{color}", "........ GET list of t-shirts by color");
        routes.put("/tshirts/size?{size}", ".......... GET list of t-shirts by size");
        routes.put("/order", "........................ POST new order Returns new Invoice");

        return routes;
    }
}
