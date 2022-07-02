package business.impl;

import java.util.HashMap;

import business.usecase.AddLibraryMemberUseCase;
import business.usecase.CheckMemberUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.LibraryMember;
import domain.exception.InvalidMemberException;

public class LibraryMemberController implements AddLibraryMemberUseCase, CheckMemberUseCase {
	LibraryMemberController() {
	}
	
	@Override
	public void addLibraryMember(LibraryMember member) throws InvalidMemberException{
		DataAccess da = new DataAccessFacade();
		
		if (member == null) {
			throw new InvalidMemberException("Member is null");
		}
		
		if (member.getMemberId().isEmpty()) {
			throw new InvalidMemberException("Invalid Member Id");
		}
	
		if (member.getFirstName().isEmpty()) {
			throw new InvalidMemberException("Invalid First Name");
		}
		
		if (member.getLastName().isEmpty()) {
			throw new InvalidMemberException("Invalid Last Name");
		}
		
		if (checkMember(member.getMemberId())) {
			throw new InvalidMemberException("Member ID already exist");
		}
		
		da.saveNewMember(member);
		
	}

	@Override
	public boolean checkMember(String memberId) {
		if (getMember(memberId) != null)
			return true;
		
		return false;
				
	}

	@Override
	public LibraryMember getMember(String memberId) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,LibraryMember> map = da.readMemberMap();
		return map.get(memberId);
	}

	
}
