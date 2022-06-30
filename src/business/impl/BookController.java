package business.impl;

import java.util.HashMap;

import business.usecase.AddBookUseCase;
import business.usecase.SearchBookUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;

public class BookController implements SearchBookUseCase,AddBookUseCase{

	BookController(){
	}
	
	@Override
	public Book searchBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map = da.readBooksMap();	
		return map.get(isbn);
	}

	@Override
	public void addBook(Book book) {
		if(searchBook(book.getIsbn()) != null) {
			DataAccess da = new DataAccessFacade();
			da.saveNewBook(book);	
		}
	}
}
