package com.hcl.assignment.service;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.assignment.model.Books;
import com.hcl.assignment.repository.BooksJdbcRepository;
import com.hcl.assignment.repository.BooksRepository;

@SpringBootTest
class BooksServiceTest {

	@InjectMocks
	private BooksService service;
	
	@Mock
	private BooksRepository booksRepository;
	
	@Mock
	private BooksJdbcRepository booksJdbcRepository;
	
	@Test
	public void test_getAllBooks() {
		
		List<Books> bookslist = getAllBooks();
		Mockito.when(booksRepository.findAll()).thenReturn(bookslist);
		
		List<Books> result = service.getAllBooks();
		Assert.assertEquals(bookslist.toString(),  result.toString());
		
		Mockito.verify(booksRepository, times(1)).findAll();
		
	}
	
	@Test
	public void test_getBooksByID() {
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		
		Optional<Books> optional = Optional.of(book1);

		Mockito.when(booksRepository.findById(1)).thenReturn(optional);
		
		Books result = service.getBooksById(1);
		Assert.assertEquals(book1.toString(),  result.toString());
		
		Mockito.verify(booksRepository, times(1)).findById(1);
	}
	
	@Test
	public void test_getBooksByIdAndName() {
		
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(booksJdbcRepository.findbyidAndName(1, "Programming with Java")).thenReturn(book1);
		
		Books result = service.getBooksByIdAndName(1, "Programming with Java");
		Assert.assertEquals(book1.toString(),  result.toString());
		
		Mockito.verify(booksJdbcRepository, times(1)).findbyidAndName(1, "Programming with Java");
	}
	
	@Test
	public void test_getBooksByNameAndPrice() {
		
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(booksJdbcRepository.findbyNameAndPrice("Programming with Java", 350)).thenReturn(book1);
		
		Books result = service.getBooksByNameAndPrice("Programming with Java", 350);
		Assert.assertEquals(book1.toString(),  result.toString());
		
		Mockito.verify(booksJdbcRepository, times(1)).findbyNameAndPrice("Programming with Java", 350);
	}
	
	@Test
	public void test_saveOrUpdate() {
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(booksRepository.save(book1)).thenReturn(book1);
		service.saveOrUpdate(book1);
		Mockito.verify(booksRepository).save(book1);
	}
	
	@Test
	public void test_saveListOfBooks() {
		List<Books> bookslist = getAllBooks();
		Mockito.when(booksRepository.saveAll(bookslist)).thenReturn(bookslist);
		service.saveListOfBooks(bookslist);
		Mockito.verify(booksRepository).saveAll(bookslist);
	}
	
	@Test
	public void test_delete() {
		Mockito.doNothing().when(booksRepository).deleteById(1);
		service.delete(1);
		Mockito.verify(booksRepository).deleteById(1);
	}
	
	@Test
	public void test_update() {
		Books book1 = new Books();
		book1.setBookid(1);
		book1.setBookname("Programming with Java");
		book1.setAuthor("E. Balagurusamy");
		book1.setPrice(320);
		Mockito.when(booksRepository.save(book1)).thenReturn(book1);
		service.update(book1, book1.getBookid());
		Mockito.verify(booksRepository).save(book1);
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
