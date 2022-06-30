package business.usecase;

import domain.LibraryMember;
import domain.exception.InvalidMemberException;

public interface AddLibraryMemberUseCase {
	public void addLibraryMember(LibraryMember member) throws InvalidMemberException;
}
