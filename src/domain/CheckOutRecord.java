package domain;

import java.io.Serializable;
import java.util.*;

final public class CheckOutRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	private LibraryMember member;
	private List<CheckOutRecordEntry> checkOutRecordEntries;
	
	
	public CheckOutRecord(LibraryMember member, List<CheckOutRecordEntry> checkOutRecordEntries) {
		this.member = member;
		this.checkOutRecordEntries = checkOutRecordEntries;
	}


	public LibraryMember getMember() {
		return member;
	}


	public List<CheckOutRecordEntry> getCheckOutRecordEntries() {
		return checkOutRecordEntries;
	}


	public void setCheckOutRecordEntries(List<CheckOutRecordEntry> checkOutRecordEntries) {
		this.checkOutRecordEntries = checkOutRecordEntries;
	}


	
}
