package business.usecase;

import java.util.List;

import domain.Book;

public interface GetBookUseCase {
	public List<Book> getBookCollection();
}
