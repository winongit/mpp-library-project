package librarysystem.business.usecase;

import librarysystem.domain.CheckOutRecord;
import librarysystem.domain.exception.BooCopyNotAvailableException;
import librarysystem.domain.exception.BookNotFoundException;
import librarysystem.domain.exception.MemberNotFoundException;

public interface CheckOutBookUseCase {
	public void checkOutBook(String memberId, String bookId) throws BookNotFoundException, MemberNotFoundException, BooCopyNotAvailableException;
	public CheckOutRecord getCheckOutRecord(String memberId);
}
