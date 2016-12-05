package patchable_router;

/***
 * Purpose:
 *     Used to hold information about a router, allowing this data to be 
 *     modified and retrieved
 * @author williammacdonald
 *
 */
public interface IRouter {

	public void setHostname(String hostname);

	public void setIPAddress(String ipAddress);
	
	public void setPatched(boolean patched);
	
	public void setOSVersion(double osVersion);
	
	/***
	 * @modifies: this
	 * @effects:  Adds the note passed as an argument to the current note held  
	 *            by this router
	 * 
	 */
	public void addNote(String note);
	
	/***
	 * @modifies: this
	 * @effects:  Clears the note held by this router, so that the note held by this router is empty
	 */
	public void clearNote();
	
	public String getHostname();
	
	public String getIPAddress();
	
	public boolean isPatched();
	
	public double getOSVersion();
	
	public String getNote();
	
}
