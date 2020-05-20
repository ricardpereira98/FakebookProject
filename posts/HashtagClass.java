package posts;

public class HashtagClass implements Hashtag {

	private String hashtag;

	public HashtagClass(String hashtag) {
		this.hashtag = hashtag;
	}
	
	@Override
	public String getHashtagName() {
		return hashtag;
	}
}
