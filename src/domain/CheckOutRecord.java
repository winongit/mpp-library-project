package domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

final public class CheckOutRecord implements Serializable{
	private static final long serialVersionUID = -1726803768458660267L;
	private LibraryMember member;
	private List<CheckOutRecordEntry> checkOutRecordEntries;
	
	
	public CheckOutRecord(LibraryMember member, List<CheckOutRecordEntry> checkOutRecordEntries) {
		super();
		this.member = member;
		this.checkOutRecordEntries = checkOutRecordEntries;
	}
	
	public LibraryMember getMember() {
		return member;
	}
	public void setMember(LibraryMember member) {
		this.member = member;
	}
	public List<CheckOutRecordEntry> getCheckOutRecordEntries() {
		return checkOutRecordEntries;
	}
	public void setCheckOutRecordEntries(List<CheckOutRecordEntry> checkOutRecordEntries) {
		this.checkOutRecordEntries = checkOutRecordEntries;
	}

	@Override
	public int hashCode() {
		return Objects.hash(checkOutRecordEntries, member);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckOutRecord other = (CheckOutRecord) obj;
		return Objects.equals(checkOutRecordEntries, other.checkOutRecordEntries)
				&& Objects.equals(member, other.member);
	}

	@Override
	public String toString() {
		return "CheckOutRecord [member=" + member + ", checkOutRecordEntries=" + checkOutRecordEntries + "]";
	}
	
	
	
	
	
	
}
