package business.usecase;

import domain.Book;
import domain.exception.BookNotFoundException;

public interface AddBookCopyUseCase {
	public void addBookCopy(Book book) throws BookNotFoundException;
}
