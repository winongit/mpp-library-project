package business.impl;

import java.util.HashMap;

import business.usecase.AddBookCopyUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;
import domain.exception.BookNotFoundException;

public class BookCopyController implements AddBookCopyUseCase{

	@Override
	public Book addBookCopy(Book book, int noOfCopies) throws BookNotFoundException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> hmBooks = da.readBooksMap();
		
		if (hmBooks.containsKey(book.getIsbn())) {
			Book bookFromDB = hmBooks.get(book.getIsbn());
			
			for(int i=0; i<noOfCopies; i++) {
				bookFromDB.addCopy();
			}
			
			hmBooks.put(bookFromDB.getIsbn(), bookFromDB);
			da.updateBookHM(hmBooks);
			return bookFromDB;
		} else {
			throw new BookNotFoundException(book.getIsbn());
		}
		
	}

	
}
