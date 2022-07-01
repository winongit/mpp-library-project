package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

final public class CheckOutRecordEntry implements Serializable{
	private static final long serialVersionUID = 4663355964847056876L;
	
	private LocalDate checkOutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;
	
	
	public CheckOutRecordEntry(LocalDate checkOutDate, LocalDate dueDate, BookCopy bookCopy) {
		super();
		this.checkOutDate = checkOutDate;
		this.dueDate = dueDate;
		this.bookCopy = bookCopy;
	}
	
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public BookCopy getBookCopy() {
		return bookCopy;
	}
	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookCopy, checkOutDate, dueDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckOutRecordEntry other = (CheckOutRecordEntry) obj;
		return Objects.equals(bookCopy, other.bookCopy) && Objects.equals(checkOutDate, other.checkOutDate)
				&& Objects.equals(dueDate, other.dueDate);
	}

	@Override
	public String toString() {
		return "CheckOutRecordEntry [checkOutDate=" + checkOutDate + ", dueDate=" + dueDate + ", bookCopy=" + bookCopy
				+ "]";
	}
	
	
	
	
	
}
