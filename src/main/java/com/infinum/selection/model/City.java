package com.infinum.selection.model;

import java.sql.Timestamp;

/**
 * Class representing database table CITY.
 * It contains system informations about world cities. 
 */
public class City {

	private Long id;
	private String name;
	private String description;
	private Long population;
	private Timestamp recordingTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	public Timestamp getRecordingTime() {
		return recordingTime;
	}
	public void setRecordingTime(Timestamp recordingTime) {
		this.recordingTime = recordingTime;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", description=" + description + ", population=" + population
				+ ", recordingTime=" + recordingTime + "]";
	}
	
}
