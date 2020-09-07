package com.infinum.selection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinum.selection.model.City;
import com.infinum.selection.service.ICityService;

/**
 * Controller for API city routes.
 */
@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private ICityService cityService;
	
	/**
	 * Method contains logic for entering new city data into the system.
	 * @param name			city name
	 * @param description	city description
	 * @param population	city population
	 */
	@RequestMapping("new/{name}-{description}-{population}")
	public void insertNewCity(@PathVariable final String name, @PathVariable final String description,
			@PathVariable final Long population) {
		
		cityService.insertNewCity(name, description, population);
	}
	
	/**
	 * Method contains logic for adding new city to users favorite list.
	 * @param name	name of the city
	 * @param token	distinct id of the user adding new city to favorites
	 */
	@RequestMapping ("/favorites/add/{name}-{token}")
	public void addToFavorites(@PathVariable final String name, @PathVariable final Long token) {
		
		cityService.addToFavorites(token, name);
	}
	
	/**
	 * Method contains logic for removing city from users favorite list.
	 * @param name	name of the city
	 * @param token	distinct id of the user adding new city to favorites
	 */
	@RequestMapping("/favorites/remove/{name}-{token}")
	public void removeFromFavorites(@PathVariable final String name, @PathVariable final Long token) {
		
		cityService.removeFromFavorites(token, name);
	}
	
	/**
	 * Method contains logic for fetching all cities from system ordered in alphabetical order.
	 * @return	alphabetically ordered list of the cities in the system
	 */
	@RequestMapping("/all")
	public List<City> getAllCities() {
		
		return cityService.getAllCities();
	}
	
	/**
	 * Method contains logic for fetching all cities from system ordered by the timestamp of creation.
	 * @return	list of the cities in the system order by the timestamp of creation
	 */
	@RequestMapping("/creation-sorted")
	public List<City> getCreationSortedCities() {
		
		return cityService.getCreationSortedCities();
	}
	
	/**
	 * Method contains logic for fetching cities from the system with most likes from system users.
	 * @return	list of the cities ordered by the most likes from system user
	 */
	@RequestMapping("/favorites")
	public List<City> getMostLikedCities() {
		
		return cityService.getMostLikedCities();
	}
}
