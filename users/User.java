package users;

import dataStructures.Array;
import dataStructures.Iterator;
import posts.Post;

public interface User {

	String getUserID();

	void addFile(User friend);

	int getNumFriends();

	String getKind();

	int getNumPosts();

	int getNumComments();

	boolean areFriends(User user);

	void addFriend(User user);

	void addPost(String truthfulness, String message, Array hashtags, User user);

	int getPostNumber();

	boolean contradictsStance(String hashtagName);

	Iterator<User> listFriends();

	Iterator<Post> listPosts();

	Post getPost(int pos);

	boolean hasPost(int pos);

	void addFriendPost(Post post);

	boolean hasAccess(Post post);

}
