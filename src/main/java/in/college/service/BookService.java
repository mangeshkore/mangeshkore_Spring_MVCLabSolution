package in.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.college.entities.Book;

@Service
public interface BookService {
	
	public List<Book> findAll();
	
	Book findById(int id);
	
	void save(Book book);
	
	void deleteById(int id);
	
	List<Book> searchBy(String name, String author);

}
