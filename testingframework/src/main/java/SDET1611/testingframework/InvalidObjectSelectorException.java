package SDET1611.testingframework;

public class InvalidObjectSelectorException extends Exception {

	public InvalidObjectSelectorException(String invalidPropValue){
		super(invalidPropValue + " is not a valid object selector.");
	}
	
}
