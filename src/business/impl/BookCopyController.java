package business.impl;

import business.usecase.AddBookCopyUseCase;
import business.usecase.CheckBookCopyAvailableUseCase;
import domain.Book;

public class BookCopyController implements AddBookCopyUseCase, CheckBookCopyAvailableUseCase{

	@Override
	public void addBookCopy(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkBookAvailableCopy(String bookId) {
		// TODO Auto-generated method stub
		return false;
	}

}
