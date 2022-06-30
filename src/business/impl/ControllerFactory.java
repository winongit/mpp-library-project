package business.impl;

import business.usecase.AddBookCopyUseCase;
import business.usecase.AddBookUseCase;
import business.usecase.CheckBookCopyAvailableUseCase;
import business.usecase.SearchBookUseCase;

public class ControllerFactory {
	private ControllerFactory() {}
	
	public static SearchBookUseCase createSearchBookUseCase() {
		SearchBookUseCase useCase = new BookController();
		return useCase;
	}
	
	public static AddBookUseCase createAddBookUseCase() {
		AddBookUseCase useCase = new BookController();
		return useCase;
	}
	
	public static CheckBookCopyAvailableUseCase createCheckBookCopyAvailableUseCase() {
		CheckBookCopyAvailableUseCase useCase = new BookCopyController();
		return useCase;
	}
	
	public static AddBookCopyUseCase createAddBookCopyUseCase() {
		AddBookCopyUseCase useCase = new BookCopyController();
		return useCase;
	}
}
