package business.usecase;

import domain.exception.BooCopyNotAvailableException;
import domain.exception.BookNotFoundException;
import domain.exception.MemberNotFoundException;

public interface CheckOutBookUseCase {
	public void checkOutBook(String memberId, String bookId) throws BookNotFoundException, MemberNotFoundException, BooCopyNotAvailableException;
}
