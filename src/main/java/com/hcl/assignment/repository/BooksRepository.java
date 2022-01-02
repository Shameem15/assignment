package com.hcl.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import com.hcl.assignment.model.Books;
  
public interface BooksRepository extends CrudRepository<Books, Integer> {
}