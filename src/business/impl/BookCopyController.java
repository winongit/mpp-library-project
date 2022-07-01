package business.impl;

import java.util.HashMap;

import business.usecase.AddBookCopyUseCase;
import business.usecase.CheckBookCopyAvailableUseCase;
import business.usecase.CheckMemberUseCase;
import business.usecase.SearchBookUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;
import domain.exception.BookNotFoundException;

public class BookCopyController implements AddBookCopyUseCase, CheckBookCopyAvailableUseCase{

	private SearchBookUseCase searchBookUseCase = ControllerFactory.createSearchBookUseCase();

	//private CheckMemberUseCase checkMember = ControllerFactory.createCheckMemberUseCase();
	@Override
	public void addBookCopy(Book book) throws BookNotFoundException {
		
		book.addCopy();
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> hmBooks = da.readBooksMap();
		
		if (hmBooks.containsKey(book.getIsbn())) {
			Book bookFromDB = hmBooks.get(book.getIsbn());
			bookFromDB.addCopy();
			
			hmBooks.put(bookFromDB.getIsbn(), bookFromDB);
			da.updateBookHM(hmBooks);
		} else {
			throw new BookNotFoundException(book.getIsbn());
		}
		
	}

	@Override
	public Book checkBookAvailableCopy(String bookId) {
		// TODO Auto-generated method stub
		Book book = searchBookUseCase.searchBook(bookId);
		return book;
	}

}
