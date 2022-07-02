package business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.usecase.AddBookUseCase;
import business.usecase.GetBookUseCase;
import business.usecase.SearchBookUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;

public class BookController implements SearchBookUseCase,AddBookUseCase,GetBookUseCase{

	public BookController(){
	}
	
	@Override
	public Book searchBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map = da.readBooksMap();	
		return map.get(isbn);
	}

	@Override
	public void addBook(Book book) { 
		if(searchBook(book.getIsbn()) == null) {
			DataAccess da = new DataAccessFacade();
			da.saveNewBook(book);	
		}
	}

	@Override
	public List<Book> getBookCollection() {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map= da.readBooksMap();
	
		List<Book> books = new ArrayList<>(map.values());
		return books;
	}
}
