package com.hcl.assignment.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hcl.assignment.model.Books;

@SpringBootTest
class BooksJdbcRepositoryTest {

	@InjectMocks
	private BooksJdbcRepository jdbcRepository;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test_findbyidAndName() {
		
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(jdbcTemplate.queryForObject(anyString(), anyObject(), any(RowMapper.class))).thenReturn(book1);
		
		Books result = jdbcRepository.findbyidAndName(1, "Programming with Java");
		Assert.assertEquals(book1.toString(),  result.toString());
		
		Mockito.verify(jdbcTemplate, times(1)).queryForObject(anyString(), anyObject(), any(RowMapper.class));
	}

	@Test
	public void test_findbyNameAndPrice() {
		
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(jdbcTemplate.queryForObject(anyString(), anyObject(), any(RowMapper.class))).thenReturn(book1);
		
		Books result = jdbcRepository.findbyNameAndPrice("Programming with Java", 350);
		Assert.assertEquals(book1.toString(),  result.toString());
		
		Mockito.verify(jdbcTemplate, times(1)).queryForObject(anyString(), anyObject(), any(RowMapper.class));
	}

}
