package users;

public class LiarUser extends UserClass {

	private static final String LIAR = "liar";

	public LiarUser(String kind, String userID) {
		super(kind, userID);
	}

	@Override
	public String getKind() {
		return LIAR;
	}

	@Override
	public boolean contradictsStance(String hashtagName) {
		return false;
	}

}
