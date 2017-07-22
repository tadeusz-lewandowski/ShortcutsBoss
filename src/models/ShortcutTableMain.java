package models;

public class ShortcutTableMain extends Shortcut{
	private String authorName;
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public ShortcutTableMain(int id, String name, String content, int author_ID, int stars, String timestamp, String authorName) {
		super(id, name, content, author_ID, stars, timestamp);
		this.authorName = authorName;
	}	
}
