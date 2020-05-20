package posts;

import dataStructures.*;
import users.*;

public class PostClass implements Post {

	private String truthfulness;
	private String message;
	private int postID;
	private User manager;

	private Array<Comments> comments;
	private Array<Hashtag> hashtags;
	
	public PostClass() {
		comments = new ArrayClass<Comments>();
	}

	public PostClass(String truthfulness, String message, Array hashtags, int postID, User manager) {
		this.truthfulness = truthfulness;
		this.message = message;
		this.hashtags = hashtags;
		this.postID = postID;
		this.manager = manager;
	}

	@Override
	public int getNumComments() {
		return comments.size();
	}

	@Override
	public String getDescription() {
		return message;
	}
	
	@Override
	public String getTruthfulness() {
		return truthfulness;
	}
	
	@Override
	public int getPostID() {
		return postID;
	}
	
}
