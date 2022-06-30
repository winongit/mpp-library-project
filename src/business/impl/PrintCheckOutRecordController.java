package business.impl;

import java.util.HashMap;

import business.usecase.CheckMemberUseCase;
import business.usecase.PrintCheckOutRecordUseCase;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;
import domain.CheckOutRecord;

public class PrintCheckOutRecordController implements PrintCheckOutRecordUseCase {
		
	private CheckMemberUseCase checkMemberUseCase;
	@Override
	public CheckOutRecord printCheckOutRecord(String memberId) {
		if(!checkMemberUseCase.checkMember(memberId))
			//throw new MemberNotFoundException;
			return null; // remove this line after implementing exception
		
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map = da.readBooksMap();	
		//return map.get(isbn);
		
		//CheckOutRecord checkOutRecord
		return null;
	}
}
