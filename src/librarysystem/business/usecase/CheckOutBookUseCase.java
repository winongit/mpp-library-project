package librarysystem.business.usecase;

import librarysystem.domain.exception.BooCopyNotAvailableException;
import librarysystem.domain.exception.BookNotFoundException;
import librarysystem.domain.exception.MemberNotFoundException;

public interface CheckOutBookUseCase extends PrintCheckOutRecordUseCase {
	public void checkOutBook(String memberId, String bookId) throws BookNotFoundException, MemberNotFoundException, BooCopyNotAvailableException;
}
