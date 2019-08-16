package com.evanco.EvanWilsonU1Capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Evan Wilson
 * @version 0.0.1
 *
 * This project involves creating a simple database backed REST
 * inventory management web service for a Video Game Store using
 * Agile development techniques in a solo setting. I was responsible
 * for designing and documenting the REST API and implementing the
 * controller, service layer, DAO, Java data objects, and unit tests for
 * the application based on an existing database structure.
 *
 * ## Business Rules
 *
 * 1. Sales tax applies only to the cost of the items.
 * 2. Sales tax does not apply to any processing fees for an invoice.
 * 3. The processing fee is applied only once per order regardless of the number of items in the order unless the number of items on the order is greater than 10 in which case an *additional* processing fee of $15.49 is applied to the order.
 * 4. The order process logic must properly update the quantity on hand for the item in the order.
 * 5. Order quantity must be greater than zero.
 * 6. Order quantity must be less than or equal to the number of items on hand in inventory.
 * 7. Order must contain a valid state code.
 * 8. The REST API must properly handle and report all violations of business rules.
 *
 * Endpoints:
 * /games: ....................... POST new game, GET all games
 * /games/{id}: .................. GET, PUT, Delete by id
 * /games/title?{title}: ......... GET list of games by title name
 * /games/studio?{studio}: ....... GET list of games by studio name
 * /games/esrb_rating?{rating}: .. GET list of games by rating
 * /consoles: .................... POST new console, GET all consoles
 * /consoles/{id}: ............... GET, PUT, Delete console by id
 * /consoles/manufacturer?{mfr}: . GET list of consoles by manufacturer id
 * /tshirts: ..................... POST new t-shirt, GET all t-shirts
 * /tshirts/{id}: ................ GET, Put, Delete t-shirt by id
 * /tshirts/color?{color}: ....... GET list of t-shirts by color
 * /tshirts/size?{size}: ......... GET list of t-shirts by size
 * /order: ....................... POST new order Returns new Invoice
 *
 * Postman order testing template
 *  	   "name": "",
 *         "street": "",
 *         "city": "",
 *         "state": "",
 *         "zipcode": "",
 *         "item_type": "",
 *         "item_id": ,
 *         "quantity":
 */
@SpringBootApplication
public class EvanWilsonU1CapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvanWilsonU1CapstoneApplication.class, args);
	}

}
