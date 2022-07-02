package business.impl;

import java.util.HashMap;

import business.usecase.LogInUseCase;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import domain.exception.LoginException;

public class LogInController implements LogInUseCase {
	LogInController() {
	}
	
	
	public static Auth currentAuth = null;

	@Override
	public Auth login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		
		if (!map.containsKey(id)) {	
			throw new LoginException("Invalid Credentials");
		}
		
		String userPass = map.get(id).getPassword();
		if (!userPass.equals(password)) {
			throw new LoginException("Invalid Credentials");
		}
		
 		currentAuth = map.get(id).getAuthorization();
 		return currentAuth;
 		
	}

}
