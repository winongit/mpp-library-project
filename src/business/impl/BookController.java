package business.impl;

import business.usecase.AddBookCopyUseCase;
import business.usecase.CheckBookCopyAvailableUseCase;
import business.usecase.SearchBookUseCase;
import domain.Book;
import domain.exception.BookNotFoundException;

public class BookController implements AddBookCopyUseCase, CheckBookCopyAvailableUseCase, SearchBookUseCase{

	@Override
	public void addBookCopy(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book search(String bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkBookAvailableCopy(String bookId) {
		// TODO Auto-generated method stub
		return false;
	}

}
