package users;

import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;
import posts.*;

public abstract class UserClass implements User {

	private String kind;
	private String userID;
	private Array<User> friends;
	private Array<Post> posts;
	private Array<Post> friendPosts;
	private Array<Comments> comments;

	public UserClass(String kind, String userID) {
		this.kind = kind;
		this.userID = userID;
		friends = new ArrayClass<User>();
		posts = new ArrayClass<Post>();
		comments = new ArrayClass<Comments>();
	}

	@Override
	public String getUserID() {
		return userID;
	}

	@Override
	public void addFile(User friend) {
		friends.insertLast(friend);
	}

	@Override
	public int getNumFriends() {
		return friends.size();
	}

	@Override
	public int getNumPosts() {
		return posts.size();
	}
	
	@Override
	public Post getPost(int pos) {
		return posts.get(pos);
	}
	
	@Override
	public boolean hasPost(int pos) {
		return getPost(pos)!= null;
	}

	@Override
	public int getNumComments() {
		return comments.size();
	}

	@Override
	public boolean areFriends(User user) {
		return friends.searchIndexOf(user) >= 0;
	}
	
	@Override
	public void addFriend(User user) {
		friends.insertLast(user);
	}
	
	@Override
	public void addPost(String truthfulness, String message, Array hashtags, User user) {
		Post post;
		post = new PostClass(truthfulness, message, hashtags, posts.size()+1, user);
		posts.insertLast(post);
		
		//adicionar os posts aos amigos
		
		Iterator<User> it = friends.iterator();
		while(it.hasNext()) {
			User friend = it.next();
			friend.addFriendPost(post);
		}
	}
	
	@Override
	public void addFriendPost(Post post) {
		friendPosts.insertLast(post);
	}
	
	@Override
	public boolean hasAccess(Post post) {
		return friendPosts.searchIndexOf(post) >= 0;
	}

	@Override	
	public int getPostNumber() {
		return posts.size();
	}
	
	@Override
	public Iterator<User> listFriends(){
		return friends.iterator();
	}
		
	@Override
	public Iterator<Post> listPosts(){
		return posts.iterator();
	}
	
	// abstract methods
	
	
	@Override
	public abstract String getKind();
	
	public abstract boolean contradictsStance(String hashtagName);
}
