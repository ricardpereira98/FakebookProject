package users;

import Fakebook.*;
import dataStructures.*;
import posts.Hashtag;

public class FanaticUser extends UserClass {

	private static final String FANATIC = "fanatic";

	private Array<Fanaticism> fanaticisms;

	public FanaticUser(String kind, String userID, Array fanaticisms) {
		super(kind, userID);
		this.fanaticisms = fanaticisms;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getKind() {
		return FANATIC;
	}

	@Override
	public boolean contradictsStance(String hashtagName) {
		Iterator<Fanaticism> it = fanaticisms.iterator();
		
		while (it.hasNext()) {
			Fanaticism fan = it.next();
			if(fan.getFanaticismType().equalsIgnoreCase("hates")) {
				if(fan.getFanaticismName().equalsIgnoreCase(hashtagName)) {
					return true;
				}
			}
		}

		return false;
	}

}
