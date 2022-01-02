package com.hcl.assignment.controller;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.assignment.model.Books;
import com.hcl.assignment.service.BooksService;

@SpringBootTest
class BooksControllerTest {

	@InjectMocks
	private BooksController controller;
	
	@Mock
	private BooksService booksService;
	
	@Test
	public void test_getAllBooks() {
		
		List<Books> bookslist = getAllBooks();
		Mockito.when(booksService.getAllBooks()).thenReturn(bookslist);
		
		List<Books> result = controller.getAllBooks();
		Assert.assertEquals(bookslist.toString(),  result.toString());
		
		Mockito.verify(booksService, times(1)).getAllBooks();
		
	}
	
	@Test
	public void test_getBooksByID() {
		
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(booksService.getBooksById(1)).thenReturn(book1);
		
		Books result = controller.getBooksByID(1);
		Assert.assertEquals(book1.toString(),  result.toString());
		
		Mockito.verify(booksService, times(1)).getBooksById(1);
	}
	
	@Test
	public void test_getBooksByIdAndName() {
		
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(booksService.getBooksByIdAndName(1, "Programming with Java")).thenReturn(book1);
		
		Books result = controller.getBooksByIdAndName(1, "Programming with Java");
		Assert.assertEquals(book1.toString(),  result.toString());
		
		Mockito.verify(booksService, times(1)).getBooksByIdAndName(1, "Programming with Java");
	}
	
	@Test
	public void test_getBooksByNameAndPrice() {
		
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(booksService.getBooksByNameAndPrice("Programming with Java", 350)).thenReturn(book1);
		
		Books result = controller.getBooksByNameAndPrice("Programming with Java", 350);
		Assert.assertEquals(book1.toString(),  result.toString());
		
		Mockito.verify(booksService, times(1)).getBooksByNameAndPrice("Programming with Java", 350);
	}
	
	@Test
	public void test_deleteBook() {
		Mockito.doNothing().when(booksService).delete(1);
		controller.deleteBook(1);
		Mockito.verify(booksService).delete(1);
	}
	
	@Test
	public void test_saveBook() {
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.doNothing().when(booksService).saveOrUpdate(book1);
		int result = controller.saveBook(book1);
		Assert.assertEquals(1, result);
		Mockito.verify(booksService).saveOrUpdate(book1);
	}
	
	@Test
	public void test_saveAllBook() {
		List<Books> bookslist = getAllBooks();
		Mockito.doNothing().when(booksService).saveListOfBooks(bookslist);
		List<Books> result = controller.saveAllBook(bookslist);
		Assert.assertEquals(bookslist, result);
		Mockito.verify(booksService).saveListOfBooks(bookslist);
	}
	
	@Test
	public void test_update() {
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.doNothing().when(booksService).saveOrUpdate(book1);
		Books result = controller.update(book1);
		Assert.assertEquals(book1.toString(), result.toString());
		Mockito.verify(booksService).saveOrUpdate(book1);
	}

	private List<Books> getAllBooks() {
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		
		Books book2 = new Books();
		book2.setBookid(2);
		book2.setBookname("Data Structures and Algorithms in Java");
		book2.setAuthor("Robert Lafore");
		book2.setPrice(590);
		
		Books book3 = new Books();
		book3.setBookid(3);
		book3.setBookname("Effective Java");
		book3.setAuthor("Joshua Bloch");
		book3.setPrice(670);
		
		
		List<Books> bookslist = new ArrayList<Books>();
		bookslist.add(book1);
		bookslist.add(book2);
		bookslist.add(book3);
		
		return bookslist;
	}

}
