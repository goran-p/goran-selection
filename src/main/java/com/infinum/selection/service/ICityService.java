package com.infinum.selection.service;

import java.util.List;

import com.infinum.selection.model.City;

/**
 * Interface containing list of methods for manipulating city data.
 */
public interface ICityService {

	// authorized user methods
	/**
	 * Method contains logic for entering new city data into the system.
	 * @param name			name of the city
	 * @param description	description of the city
	 * @param population	population of the city
	 */
	void insertNewCity(final String name, final String description, final Long population);
	
	/**
	 * Method contains logic for adding new city to users favorite list.
	 * @param token		distinct id of the user adding new city to favorites
	 * @param cityName	name of the city
	 */
	void addToFavorites(final Long token, final String cityName);
	
	/**
	 * Method contains logic for removing city from users favorite list.
	 * @param token		distinct id of the user removing city from favorites
	 * @param cityName	name of the city
	 */
	void removeFromFavorites(final Long token, final String cityName);
	
	// non-authorized user methods
	/**
	 * Method contains logic for fetching all cities from system ordered in alphabetical order. 
	 * @return	alphabetically ordered list of the cities in the system
	 */
	List<City> getAllCities();
	
	/**
	 * Method contains logic for fetching all cities from system ordered by the timestamp of creation.
	 * @return	list of the cities in the system order by the timestamp of creation
	 */
	List<City> getCreationSortedCities();
	
	/**
	 * Method contains logic for fetching cities from the system with most likes from system users.
	 * @return	list of the cities ordered by the most likes from system user
	 */
	List<City> getMostLikedCities();
}
