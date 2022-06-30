package business.usecase;

import domain.Book;

public interface SearchBookUseCase {
	public Book searchBook(String isbn);
}
