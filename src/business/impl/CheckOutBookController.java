package business.impl;

import business.usecase.CheckMemberUseCase;
import business.usecase.CheckOutBookUseCase;
import business.usecase.SearchBookUseCase;
import domain.Book;
import domain.exception.BookNotFoundException;
import domain.exception.MemberNotFoundException;

public class CheckOutBookController implements CheckOutBookUseCase {

	private SearchBookUseCase searchBookUseCase = ControllerFactory.createSearchBookUseCase();
	private CheckMemberUseCase checkMember = ControllerFactory.createCheckMemberUseCase();

	@Override
	public void checkOutBook(String memberId, String bookId) throws BookNotFoundException, MemberNotFoundException {
		// TODO Auto-generated method stub

		Book book = searchBookUseCase.searchBook(bookId);

		if (book == null) {
			System.out.println(bookId);
			throw new BookNotFoundException("Book not found");
		}

		if (!checkMember.checkMember(memberId)) {
			throw new MemberNotFoundException("Member not found");
		}

	}
}
