package business.usecase;

import domain.exception.LoginException;

public interface LogInUseCase {
	public void login(String id, String password) throws LoginException;
}
