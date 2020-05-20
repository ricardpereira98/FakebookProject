package users;

public class NaiveUser extends UserClass{

	private static final String NAIVE = "naive";

	public NaiveUser(String kind, String userID) {
		super(kind, userID);
	}

	@Override
	public String getKind() {
		return NAIVE;
	}

	@Override
	public boolean contradictsStance(String hashtagName) {
		// TODO Auto-generated method stub
		return false;
	}

}
