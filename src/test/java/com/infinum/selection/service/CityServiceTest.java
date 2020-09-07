package com.infinum.selection.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.infinum.selection.exception.UnexpectedNumberOfDBOperationsException;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {
	
	@Mock
	JdbcTemplate jdbc;
	
	@InjectMocks
	CityService cityService;
	
	@Test
	public void testCorrectInsertionOfNewCity() {
		
		when(jdbc.update(anyString(), anyString(), anyString(), anyLong())).thenReturn(1);
		cityService.insertNewCity("name", "desc", 1L);
		verify(jdbc, times(1)).update(anyString(), anyString(), anyString(), anyLong());
	}
	
	@Test(expected=UnexpectedNumberOfDBOperationsException.class)
	public void testIncorrectInsertionOfNewCity() {
		
		when(jdbc.update(anyString(), anyString(), anyString(), anyLong())).thenReturn(2);
		cityService.insertNewCity("name", "desc", 1L);
		verify(jdbc, times(1)).update(anyString(), anyString(), anyString());
	}
	
	@Test
	public void testCorrectAddingToFavorites() {
		
		when(jdbc.update(anyString(), anyLong(), anyString())).thenReturn(1);
		
		cityService.addToFavorites(1L, "dummy");
		verify(jdbc, times(1)).update(anyString(), anyLong(), anyString());
	}
	
	@Test(expected=UnexpectedNumberOfDBOperationsException.class)
	public void testIncorrectAddingToFavorites() {
		
		when(jdbc.update(anyString(), anyLong(), anyString())).thenReturn(2);
		
		cityService.addToFavorites(1L, "dummy");
		verify(jdbc, times(1)).update(anyString(), anyLong(), anyString());
	}
	
	@Test
	public void testCorrectRemovalFromFavorites() {
		
		when(jdbc.update(anyString(), anyLong(), anyString())).thenReturn(1);
		cityService.removeFromFavorites(1L, "dummy");
		verify(jdbc, times(1)).update(anyString(), anyLong(), anyString());
	}
	
	@Test(expected=UnexpectedNumberOfDBOperationsException.class)
	public void testIncorrectRemovalFromFavorites() {
		
		when(jdbc.update(anyString(), anyLong(), anyString())).thenReturn(2);
		cityService.removeFromFavorites(1L, "dummy");
		verify(jdbc, times(1)).update(anyString(), anyLong(), anyString());
	}
}
