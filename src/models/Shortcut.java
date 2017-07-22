package models;

public class Shortcut {
	private int id;
	private String name;
	private String content;
	private int author_ID;
	private int stars;
	private String timestamp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAuthor_ID() {
		return author_ID;
	}
	public void setAuthor_ID(int author_ID) {
		this.author_ID = author_ID;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Shortcut(int id, String name, String content, int author_ID, int stars, String timestamp) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.author_ID = author_ID;
		this.stars = stars;
		this.timestamp = timestamp;
	}
	
}
