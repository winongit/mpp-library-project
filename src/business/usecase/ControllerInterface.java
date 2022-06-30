package business.usecase;

import java.util.List;

import domain.exception.LoginException;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
}
