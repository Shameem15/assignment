package com.hcl.assignment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hcl.assignment.model.Books;
import com.hcl.assignment.service.BooksService;

@RestController
@RequestMapping("/api")
public class BooksController {

	@Autowired
	BooksService booksService;

	@GetMapping("/book")
	public List<Books> getAllBooks() {
		return booksService.getAllBooks();
	}

	@GetMapping("/book/{bookid}")
	public Books getBooksByID(@PathVariable("bookid") int bookid) {
		return booksService.getBooksById(bookid);
	}

	@GetMapping("/book/findByIdName")
	public Books getBooksByIdAndName(@RequestParam(required = false, name = "bookid") Integer bookid,
			@RequestParam(required = false, name = "bookname") String bookname) {
		return booksService.getBooksByIdAndName(bookid, bookname);
	}

	@GetMapping("/book/findByNamePrice")
	public Books getBooksByNameAndPrice(@RequestParam(required = false, name = "bookname") String bookname,
			@RequestParam(required = false, name = "price") Integer price) {
		return booksService.getBooksByNameAndPrice(bookname, price);
	}

//	@GetMapping("/book/{searchKey}")
//	public Books getBooks(@PathVariable("searchKey") String searchKey) {
//
//		List<Books> listOfBooks = booksService.getAllBooks();
//
//		List<Books> filterStringBook = listOfBooks.stream()
//				.filter(x -> x.getBookname().equalsIgnoreCase(searchKey) || x.getAuthor().equalsIgnoreCase(searchKey))
//				.collect(Collectors.toList());
//		if (!filterStringBook.isEmpty()) {
//			return filterStringBook.stream().findFirst().get();
//		}
//
//		List<Books> filterIntBooks = null;
//		try {
//			filterIntBooks = listOfBooks.stream().filter(
//					x -> x.getPrice() == Integer.valueOf(searchKey) || x.getBookid() == Integer.valueOf(searchKey))
//					.collect(Collectors.toList());
//
//			if (!filterIntBooks.isEmpty()) {
//				return filterIntBooks.stream().findFirst().get();
//			}
//		} catch (Exception e) {
//			return null;
//		}
//
//		return null;
//	}

	@DeleteMapping("/book/{bookid}")
	public void deleteBook(@PathVariable("bookid") int bookid) {
		booksService.delete(bookid);
	}

	@PostMapping("/books")
	public int saveBook(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books.getBookid();
	}

	@PostMapping("/listbooks")
	public List<Books> saveAllBook(@RequestBody List<Books> listbooks) {
		booksService.saveListOfBooks(listbooks);
		return listbooks;
	}

	@PutMapping("/books")
	public Books update(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books;
	}
}
