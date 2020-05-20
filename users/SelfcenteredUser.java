package users;

public class SelfcenteredUser extends UserClass {

	private static final String SELFCENTERED = "selfcentered";

	public SelfcenteredUser(String kind, String userID) {
		super(kind, userID);
	}

	@Override
	public String getKind() {
		return SELFCENTERED;
	}

	@Override
	public boolean contradictsStance(String hashtagName) {
		// TODO Auto-generated method stub
		return false;
	}

}
