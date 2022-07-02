package domain.exception;

import java.io.Serializable;

public class BooCopyNotAvailableException extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public BooCopyNotAvailableException(String bookId) {
		super("BookId " + bookId + " copy is not available");
	}

}