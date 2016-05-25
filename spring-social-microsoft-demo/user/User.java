package hello.user;

/**
 * Simple little User model. 
 * Just stores the user's id for simplicity.
 * @author Keith Donald
 */
public final class User {
	
	private final String id;
	
	public User(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
}