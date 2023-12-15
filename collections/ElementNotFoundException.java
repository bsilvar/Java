public class ElementNotFoundException extends RuntimeException {
	
	public ElementNotFoundException(String list) {
		super("The " + list + " is empty.");
	}

}
