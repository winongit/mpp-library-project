package business.impl;

import business.usecase.AddBookCopyUseCase;
import business.usecase.CheckBookCopyAvailableUseCase;
import business.usecase.LogInUseCase;
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
	
	public static LogInUseCase createLogInUseCase() {
		LogInUseCase useCase = new LogInController();
		return useCase;
		
	}
}
