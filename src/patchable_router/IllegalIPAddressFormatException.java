package patchable_router;

public class IllegalIPAddressFormatException extends Exception {

	/**
	 * Mandatory ID generated since Exceptions are serialisable 
	 */
	private static final long serialVersionUID = -2208344675544239174L;
	
	public IllegalIPAddressFormatException(String message){
		super(message);
	}

}
