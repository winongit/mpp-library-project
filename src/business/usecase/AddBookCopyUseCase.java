package business.usecase;

import domain.Book;
import domain.exception.BookNotFoundException;

public interface AddBookCopyUseCase {
	public Book addBookCopy(Book book, int noOfCopies) throws BookNotFoundException;
}
