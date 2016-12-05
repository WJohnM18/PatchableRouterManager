package src;

/***
 * Purpose:
 *     Used to display information to the user and receive input from them
 *      
 * @author williammacdonald
 *
 */
public interface UserInterface {

	/***
	 * @effects - If alertMessage != null, then the alertMessage will be displayed to the
	 *            user as an error message
	 *          - Otherwise if alertMessage == null, then no message will be displayed
	 */
	public void alertError(String alertMessage);
	
	
	/***
	 * @effects - If message != null, then message will be displayed to the screen
	 *          - Otherwise if message == null, then no message will be displayed
	 */
	public void display(String message);
}
