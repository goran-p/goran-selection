package com.infinum.selection.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.infinum.selection.exception.UnexpectedNumberOfDBOperationsException;
import com.infinum.selection.model.City;

/**
 * Class contains implementation of {@link ICityService} interface. <br>
 * Service class containing logic for manipulating with cities data into the system.
 * It allows adding new cities to the system by authorized users, adding and removing
 * cities to users favorite list and fetching cities sorted in various ways.
 */
@Service
public class CityService implements ICityService {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public void insertNewCity(final String name, final String description, final Long population) {
		
		final StringBuilder insertSql = new StringBuilder();
		insertSql.append("INSERT INTO city(name, description, population, recording_time) ");
		insertSql.append("VALUES(?, ?, ?, current_timestamp)");
		
		final int inserted = jdbc.update(Objects.toString(insertSql), name, description, population);
		if(inserted!=1) {
			final String message = "Expected to insert 1 record into the CITY table, but instead inserted "+inserted+" records";
			throw new UnexpectedNumberOfDBOperationsException(message);
		}
	}

	@Override
	public void addToFavorites(final Long token, final String cityName) {
		
		final StringBuilder insertSql = new StringBuilder();
		insertSql.append("INSERT INTO user_favorite_city(token, city_id) ");
		insertSql.append("VALUES(?, SELECT id FROM city WHERE name = ?) ");
		
		final int inserted = jdbc.update(Objects.toString(insertSql), token, cityName);
		if(inserted!=1) {
			final String message = "Expected to insert 1 record into the USER_FAVORITE_CITY table, but instead inserted "+inserted+" records";
			throw new UnexpectedNumberOfDBOperationsException(message);
		}
	}

	@Override
	public void removeFromFavorites(final Long token, final String cityName) {
		
		final StringBuilder removeSql = new StringBuilder();
		removeSql.append("DELETE FROM user_favorite_city ");
		removeSql.append("WHERE token = ? ");
		removeSql.append("AND city_id = (");
			removeSql.append("SELECT id FROM city WHERE name = ?");
		removeSql.append(")");
		
		final int deleted = jdbc.update(Objects.toString(removeSql), token, cityName);
		if(deleted!=1) {
			final String message = "Expected to remove 1 record from the USER_FAVORITE_CITY table, but instead deleted "+deleted+" records";
			throw new UnexpectedNumberOfDBOperationsException(message);
		}
	}

	@Override
	public List<City> getAllCities() {
		
		final String selectSql = "SELECT * FROM city ORDER BY name";
		return getQueryResult(selectSql);
	}

	@Override
	public List<City> getCreationSortedCities() {
		
		final String selectSql = "SELECT * FROM city ORDER BY recording_time";
		return getQueryResult(selectSql);
	}

	@Override
	public List<City> getMostLikedCities() {
		
		final StringBuilder selectSql = new StringBuilder();
		selectSql.append("SELECT c.* ");
		selectSql.append("FROM city c ");
		selectSql.append("LEFT OUTER JOIN (");
			selectSql.append("SELECT distinct(city_id), count(*) as favorites ");
			selectSql.append("FROM user_favorite_city ");
			selectSql.append("group by city_id ");
		selectSql.append(") AS ufc ON ufc.city_id = c.id ");
		selectSql.append("ORDER by ufc.favorites desc");
		
		return getQueryResult(Objects.toString(selectSql));
	}
	
	private List<City> getQueryResult(final String selectSql) {
		return jdbc.query(selectSql, new BeanPropertyRowMapper<>(City.class));
	}
}
