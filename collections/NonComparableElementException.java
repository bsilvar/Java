public class NonComparableElementException extends RuntimeException {
	
	public NonComparableElementException(String element) {
		super("The " + element + " is not comparable.");
	}

}
