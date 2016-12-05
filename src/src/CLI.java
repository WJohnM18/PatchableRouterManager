package src;

/***
 * Purpose:
 *     A Command Line Interface used to display information to the user
 * @author williammacdonald
 *
 */
public class CLI implements UserInterface {

	/***
	 * @effects - If alertMessage != null, then prints out the alertMessage to the console
	 *          - Otherwise if alertMessage == null, no action is taken
	 */
	@Override
	public void alertError(String alertMessage){
		if(null != alertMessage){
		    System.out.println("ERROR: " + alertMessage);
		}
	}

	/***
	 * @effects - If message != null, then prints out the message to the console
	 *          - Otherwise if message == null, no action is taken
	 */
	@Override
	public void display(String message) {
		if(null != message){
			System.out.println(message);
		}
		
	}
	
	
	
	
}
