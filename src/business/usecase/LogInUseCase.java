package business.usecase;

import dataaccess.Auth;
import domain.exception.LoginException;

public interface LogInUseCase {
	public Auth login(String id, String password) throws LoginException;
}
