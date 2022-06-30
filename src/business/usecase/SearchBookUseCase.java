package business.usecase;

import domain.Book;
import domain.exception.BookNotFoundException;

public interface SearchBookUseCase {
	public Book search(String bookId) throws BookNotFoundException;
}
