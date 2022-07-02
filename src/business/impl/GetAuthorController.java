package business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.usecase.GetAuthorUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Author;

public class GetAuthorController implements GetAuthorUseCase {
	GetAuthorController() {
	}

	@Override
	public List<Author> getAllAuthors() {
		
		DataAccess da = new DataAccessFacade();
		HashMap<String,Author> authors = da.readAuthorMap();
		if (authors==null) return null;
		return new ArrayList<>(authors.values());
	}
}
