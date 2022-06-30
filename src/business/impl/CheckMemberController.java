package business.impl;

import java.util.HashMap;

import business.usecase.CheckMemberUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.LibraryMember;

public class CheckMemberController implements CheckMemberUseCase{

	@Override
	public boolean checkMember(String memberId) {
		
		DataAccess da = new DataAccessFacade();
		HashMap<String,LibraryMember> map = da.readMemberMap();
		return map.get(memberId) != null;		
	}

}
