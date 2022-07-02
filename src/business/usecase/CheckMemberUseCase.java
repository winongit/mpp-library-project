package business.usecase;

import domain.LibraryMember;

public interface CheckMemberUseCase {
	public boolean checkMember(String memberId);
	
	public LibraryMember getMember(String memberId);
}
