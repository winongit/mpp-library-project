package business.usecase;

import java.util.List;

import domain.Book;

public interface SearchBookUseCase {
	public Book searchBook(String isbn);
	
	public List<Book> getBookCollection();
}
