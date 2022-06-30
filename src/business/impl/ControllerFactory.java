package business.impl;

import business.usecase.AddBookCopyUseCase;
import business.usecase.CheckBookCopyAvailableUseCase;
import business.usecase.CheckMemberUseCase;
import business.usecase.CheckOutBookUseCase;
import business.usecase.SearchBookUseCase;

public class ControllerFactory {
	private ControllerFactory() {}
	
	public static SearchBookUseCase createSearchBookUseCase() {
		SearchBookUseCase useCase = new BookController();
		return useCase;
	}
	
	public static CheckBookCopyAvailableUseCase createCheckBookCopyAvailableUseCase() {
		CheckBookCopyAvailableUseCase useCase = new BookController();
		return useCase;
	}
	
	public static AddBookCopyUseCase createAddBookCopyUseCase() {
		AddBookCopyUseCase useCase = new BookController();
		return useCase;
	}
	
	public static CheckOutBookUseCase createCheckOutBookUseCase() {
		CheckOutBookUseCase useCase = new CheckOutBookController();
		return useCase;
	}
	
	public static CheckMemberUseCase createCheckMemberUseCase() {
		return null;
	}
	
}
