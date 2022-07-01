package business.usecase;

import domain.Book;

public interface CheckBookCopyAvailableUseCase {
	public Book checkBookAvailableCopy(String bookId); 
}
