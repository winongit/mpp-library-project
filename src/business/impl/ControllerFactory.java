package business.impl;

import business.usecase.AddBookCopyUseCase;
import business.usecase.AddBookUseCase;
import business.usecase.AddLibraryMemberUseCase;
import business.usecase.CheckBookCopyAvailableUseCase;
import business.usecase.CheckMemberUseCase;
import business.usecase.CheckOutBookUseCase;
import business.usecase.LogInUseCase;
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
	
	public static CheckOutBookUseCase createCheckOutBookUseCase() {
		CheckOutBookUseCase useCase = new CheckOutBookController();
		return useCase;
	}
	
	public static CheckMemberUseCase createCheckMemberUseCase() {
		CheckMemberUseCase useCase = new CheckMemberController();
		return useCase;
	}
	
	public static LogInUseCase createLogInUseCase() {
		LogInUseCase useCase = new LogInController();
		return useCase;
		
	}
	
	public static AddLibraryMemberUseCase createAddLibraryMemberUseCase() {
		AddLibraryMemberUseCase useCase = new LibraryMemberController();
		return useCase;
	}
	
}
