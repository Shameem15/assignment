package com.hcl.assignment.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.assignment.model.Books;

@Repository
public class BooksJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

//	class BooksRowMapper implements RowMapper<Books> {
//
//		@Override
//		public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Books books = new Books();
//			books.setBookid(rs.getInt("bookid"));
//			books.setBookname(rs.getString("bookname"));
//			books.setAuthor(rs.getString("author"));
//			books.setPrice(rs.getInt("price"));
//			return books;
//		}
//	}
//
//	public List<Books> findAll() {
//		return jdbcTemplate.query("select * from books", new BooksRowMapper());
//	}

	@SuppressWarnings("deprecation")
	public Books findbyidAndName(int bookid, String name) {
		Books result;
		try {
			result = jdbcTemplate.queryForObject("select * from books where bookid = ? and bookname = ?",
					new Object[] { bookid, name }, new BeanPropertyRowMapper<Books>(Books.class));
		} catch (DataAccessException e) {
			result = null;
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public Books findbyNameAndPrice(String name, int price) {
		Books result;
		try {
			result = jdbcTemplate.queryForObject("select * from books where bookname = ? and price = ?",
					new Object[] { name, price }, new BeanPropertyRowMapper<Books>(Books.class));
		} catch (DataAccessException e) {
			result = null;
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

//	@SuppressWarnings("deprecation")
//	public Books findbyid(int bookid) {
//		return jdbcTemplate.queryForObject("select * from books where bookid=?", new Object[] { bookid },
//				new BeanPropertyRowMapper<Books>(Books.class));
//	}
//
//	public int deletebyid(int bookid) {
//		return jdbcTemplate.update("delete from books where bookid=?", new Object[] { bookid });
//	}
//
//	public int insert(Books books) {
//		return jdbcTemplate.update("insert into books (bookid, bookname, author, price) " + "values(?, ?, ?, ?)",
//				new Object[] { books.getBookid(), books.getBookname(), books.getAuthor(), books.getPrice() });
//	}
//
//	public int update(Books books) {
//		return jdbcTemplate.update("update books " + " set bookname = ?, author = ? " + " where bookid = ?",
//				new Object[] { books.getBookname(), books.getAuthor(), books.getBookid() });
//	}

}
