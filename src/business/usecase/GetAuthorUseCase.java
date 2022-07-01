package business.usecase;

import java.util.List;

import domain.Author;

public interface GetAuthorUseCase {
	
	public List<Author> getAllAuthors();
}
