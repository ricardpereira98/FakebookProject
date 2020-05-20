import java.util.Scanner;
import Fakebook.*;
import users.*;
import dataStructures.*;
import posts.*;

import Exceptions.*;

/**
 * 
 * @author nuno costa &&
 *
 */

public class Main {

	// command strings
	private static final String EXIT = "EXIT";
	private static final String HELP = "HELP";
	private static final String REGISTER = "REGISTER";
	private static final String USERS = "USERS";
	private static final String ADD_FRIEND = "ADDFRIEND";
	private static final String FRIENDS = "FRIENDS";
	private static final String POST = "POST";
	private static final String USER_POSTS = "USERPOSTS";
	private static final String COMMENT = "COMMENT";
	private static final String READ_POST = "READPOST";
	private static final String COMMENTS_BY_USER = "COMMENTSBYUSER";
	private static final String TOPIC_FANATICS = "TOPICFANATICS";
	private static final String TOPIC_POSTS = "TOPICPOSTS";
	private static final String POPULAR_POST = "POPULARPOST";
	private static final String TOP_POSTER = "TOPPOSTER";
	private static final String RESPONSIVE = "RESPONSIVE";
	private static final String SHAMELESS = "SHAMELESS";

	// message strings
	private static final String EXIT_MSG = "Exiting...";

	private static final String NO_USERS = "There are no users!";
	private static final String USER_NOT_EXISTS = "%s does not exist!\n";
	private static final String SAME_USER = "%s cannot be the same as %s.\n";
	private static final String IS_FRIEND = "%s is friend of %s.\n";
	private static final String INVALID_HASHTAG = "Invalid hashtags list!";
	private static final String POST_SENT = "%s sent a %s post to %d friends. Post id = %d\n";
	private static final String INADEQUATE_STANCE = "Inadequate stance!";
	private static final String NO_FRIENDS = "%s has no friends!";
	private static final String NO_ACCESS = "%s has no access to post %sd by %s!\n";
	private static final String NO_POST = "%s has no post %d\n";
	private static final String CANT_COMMENT = "%s cannot comment on this post!\n";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Fakebook fakebook = new FakebookClass();
		String comm = getCommand(in);

		while (!comm.equals(EXIT)) {
			switch (comm) {

			case HELP:
				help();
				break;

			case REGISTER:
				register(in, fakebook);
				break;

			case USERS:
				users(fakebook);
				break;

			case ADD_FRIEND:
				addFriend(in, fakebook);
				break;

			case FRIENDS:
				friends(in, fakebook);
				break;

			case POST:
				post(in, fakebook);
				break;

			case USER_POSTS:
				userPosts(in, fakebook);
				break;

			case COMMENT:
				comment(in, fakebook);
				break;

			case READ_POST:
				readPost(in, fakebook);
				break;

			case COMMENTS_BY_USER:
				commentsByUser(in, fakebook);
				break;

			case TOPIC_FANATICS:
				topicFanatics(in, fakebook);
				break;

			case TOPIC_POSTS:
				topicPosts(in, fakebook);
				break;

			case POPULAR_POST:
				popularPost(fakebook);
				break;

			case TOP_POSTER:
				topPoster(fakebook);
				break;

			case RESPONSIVE:
				responsive(fakebook);
				break;

			case SHAMELESS:
				shameless(fakebook);
				break;

			}
			System.out.println();
			comm = getCommand(in);
		}
		System.out.println(EXIT_MSG);
		System.out.println();
		in.close();
	}

	private static String getCommand(Scanner in) {
		String input;

		input = in.next().toUpperCase().trim();
		return input;
	}

	private static void help() {
		System.out.println("register - registers a new user");
		System.out.println("users - lists all users");
		System.out.println("addfriend - adds a new friend");
		System.out.println("friends - lists the user friends");
		System.out.println("post - posts a new message");
		System.out.println("userposts - lists all posts by a user");
		System.out.println("comment - user comments on a post");
		System.out.println("readpost - prints detailed info on a post");
		System.out.println("commentsbyuser - shows all the comments by a user on a given post");
		System.out.println("topicfanatics - shows a list of fanatic users on a topic");
		System.out.println("topicposts - shows a list of posts on a given topic");
		System.out.println("popularpost - shows the most commented post");
		System.out.println("topposter - shows the user with more posts");
		System.out.println("responsive - shows the user with a higher percentage of commented posts");
		System.out.println("shameless - shows the top liars");
		System.out.println("help - shows the available commands");
		System.out.println("exit - terminates the execution of the program");
	}

	private static void register(Scanner in, Fakebook fakebook) {
		try {
			tryToRegister(in, fakebook);
		} catch (InvalidUserKindException | ExistentUserException | InvalidFanaticismException e) {
			e.getMessage();
		}
	}
	
	private static void tryToRegister(Scanner in, Fakebook fakebook)
			throws InvalidUserKindException, ExistentUserException, InvalidFanaticismException {
		String kind, userID;
		kind = in.next().trim();
		userID = in.nextLine().trim();
		int numFanaticisms = 0;

		if (fakebook.isFanatic(kind)) {
			numFanaticisms = in.nextInt();
			
			fakebook.restoreFanaticism();
			
			for (int i = 0; i < numFanaticisms; i++) {
				String type = in.next().trim();
				String fanaticism = in.next().trim();
				fakebook.addFanatism(type, fanaticism);
			}
		}
		fakebook.addUser();
		System.out.println(userID + " registered.");

	}

	private static void users(Fakebook fakebook) {
		if (fakebook.noUsers()) {
			System.out.println(NO_USERS);
		} else {
			Iterator<User> it = fakebook.listUsers();
			while (it.hasNext()) {
				User user = it.next();
				System.out.printf("%s [%s] %d %d %d\n", user.getUserID(), user.getKind(), user.getNumFriends(),
						user.getNumPosts(), user.getNumComments());
			}
		}
	}

	private static void addFriend(Scanner in, Fakebook fakebook) {
		String user1, user2;
		user1 = in.next().trim();
		user2 = in.nextLine().trim();

		// fazer com exceptions
		if (!fakebook.hasUser(user2)) {
			if (!fakebook.hasUser(user1)) {
				System.out.printf(USER_NOT_EXISTS, user1);
			} else {
				System.out.printf(USER_NOT_EXISTS, user2);
			}
		}

		else if (user1.equalsIgnoreCase(user2)) {
			System.out.printf(SAME_USER, user1, user2);
		}

		else if (fakebook.areFriends(user1, user2)) {
			System.out.printf("%s must really admire %s!\n", user1, user2);
		}

		else {
			fakebook.addFriend(user1, user2);
			System.out.printf(IS_FRIEND, user1, user2);
		}

	}

	private static void friends(Scanner in, Fakebook fakebook) {
		String userID;
		userID = in.nextLine().trim();

		if (!fakebook.hasUser(userID)) {
			System.out.printf(USER_NOT_EXISTS, userID);
		}

		else if (fakebook.noFriends(userID)) {
			System.out.printf(NO_FRIENDS, userID);
		}

		else {
			Iterator<User> it = fakebook.listFriends(userID);
			while (it.hasNext()) {
				User friend = it.next();
				System.out.print(friend.getUserID());
				if (it.hasNext()) {
					System.out.print(", ");
				} // else {
					// System.out.println();
					// }
			}

		}

	}

	private static void post(Scanner in, Fakebook fakebook) {
		String userID, hashtag, truthfulness, message;
		int numHashtags;

		userID = in.nextLine().trim();
		numHashtags = in.nextInt();

		fakebook.restoreHashtags(); // restora
		// verificar
		for (int i = 0; i < numHashtags; i++) {
			hashtag = in.next().trim();
			fakebook.addHashtag(hashtag);
		}

		truthfulness = in.next().trim();
		message = in.nextLine().trim();

		if (!fakebook.hasUser(userID)) {
			System.out.printf(USER_NOT_EXISTS, userID);
		}

		else if (!(numHashtags > 0)) {
			System.out.println(INVALID_HASHTAG);
		}

		else if (fakebook.contradictsStance(userID, truthfulness)) {
			System.out.println(INADEQUATE_STANCE);
		}

		else {
			fakebook.addPost(userID, truthfulness, message);
			User a = fakebook.getUser(userID);
			System.out.printf(POST_SENT, userID, truthfulness, a.getNumFriends(), a.getPostNumber());
		}
	}

	private static void userPosts(Scanner in, Fakebook fakebook) {
		String userID;
		userID = in.nextLine().trim();

		if (!fakebook.hasUser(userID)) {
			System.out.printf(USER_NOT_EXISTS, userID);
		}

		else if (fakebook.noPosts(userID)) {
			System.out.printf("%s has no posts!\n", userID);
		}

		else {
			int i = 1;
			Iterator<Post> it = fakebook.listPosts(userID);
			while (it.hasNext()) {
				Post post = it.next();
				System.out.printf("%d. [%s] %s [%d comments]\n", i, post.getTruthfulness(), post.getDescription(),
						post.getNumComments());
				i++;
			}

		}

	}

	private static void comment(Scanner in, Fakebook fakebook) {
		// TODO Auto-generated method stub

		String managerID, updaterID, stance, comment;
		int postID;

		updaterID = in.nextLine().trim();
		managerID = in.nextLine().trim();
		postID = in.nextInt();
		stance = in.next().trim();
		comment = in.nextLine().trim();

		if (!fakebook.hasUser(managerID)) {
			if (!fakebook.hasUser(updaterID)) {
				System.out.printf(USER_NOT_EXISTS, updaterID);
			} else {
				System.out.printf(USER_NOT_EXISTS, managerID);
			}
		}

		else if (!fakebook.hasPost(managerID, postID)) {
			System.out.printf(NO_POST, managerID, postID);
		}

		else if (!fakebook.hasAccess(updaterID, managerID, postID)) {
			System.out.printf(NO_ACCESS, updaterID, postID, managerID);
		}

		else if (fakebook.isSelfcentered(updaterID)) {
			System.out.printf(CANT_COMMENT, updaterID);
		}

		else if (fakebook.contradictsCommentStance(updaterID, stance)) {
			System.out.println(INADEQUATE_STANCE);
		}

	}

	private static void readPost(Scanner in, Fakebook fakebook) {
		// TODO Auto-generated method stub

	}

	private static void commentsByUser(Scanner in, Fakebook fakebook) {
		// TODO Auto-generated method stub

	}

	private static void topicFanatics(Scanner in, Fakebook fakebook) {
		// TODO Auto-generated method stub

	}

	private static void topicPosts(Scanner in, Fakebook fakebook) {
		// TODO Auto-generated method stub

	}

	private static void popularPost(Fakebook fakebook) {
		// TODO Auto-generated method stub

	}

	private static void topPoster(Fakebook fakebook) {
		// TODO Auto-generated method stub

	}

	private static void responsive(Fakebook fakebook) {
		// TODO Auto-generated method stub

	}

	private static void shameless(Fakebook fakebook) {
		// TODO Auto-generated method stub

	}

}
