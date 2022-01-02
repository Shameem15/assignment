package com.hcl.assignment.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.assignment.model.Books;
import com.hcl.assignment.repository.BooksJdbcRepository;
import com.hcl.assignment.repository.BooksRepository;

@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private BooksJdbcRepository booksJdbcRepository;

	public List<Books> getAllBooks() {
		List<Books> books = new ArrayList<Books>();
		booksRepository.findAll().forEach(books1 -> books.add(books1));
		return books;
	}

	public Books getBooksById(int id) {
		return booksRepository.findById(id).get();
	}
	
	public Books getBooksByIdAndName(int id, String name) {
		return booksJdbcRepository.findbyidAndName(id, name);
	}
	
	public Books getBooksByNameAndPrice(String name, int price ) {
		return booksJdbcRepository.findbyNameAndPrice(name, price);
	}

	public void saveOrUpdate(Books books) {
		booksRepository.save(books);
	}

	public void saveListOfBooks(List<Books> listbooks) {
		booksRepository.saveAll(listbooks);
	}

	public void delete(int id) {
		booksRepository.deleteById(id);
	}
 
	public void update(Books books, int bookid) {
		booksRepository.save(books);
	}
}