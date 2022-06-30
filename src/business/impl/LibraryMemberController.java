package business.impl;

import business.usecase.AddLibraryMemberUseCase;
import business.usecase.CheckMemberUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.LibraryMember;
import domain.exception.InvalidMemberException;

public class LibraryMemberController implements AddLibraryMemberUseCase, CheckMemberUseCase {

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
		// TODO Auto-generated method stub
		return false;
	}
	
}
