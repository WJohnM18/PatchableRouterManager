package patchable_router;

/***
 * Purpose:
 *     Used to hold information about a router and allow this
 *     information to be retrieved or modified
 * @author williammacdonald
 *
 */
public class Router implements IRouter{
	
	private String hostname;
	private String ipAddress;
	private boolean patched;
	private String osVersion;
	private String note;
	

	public Router(String hostname, String ipAddress, boolean patched, String osVersion, String note) {
	    
	    this.hostname = hostname;
	    this.ipAddress = ipAddress;
	    this.patched = patched;
	    this.osVersion = osVersion;
	    this.note = note;
	}
	
	@Override
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Override
	public void setIPAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public void setPatched(boolean patched) {
		this.patched = patched;
	}

	@Override
	public void setOSVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	
	@Override
	public void setNote(String note){
		this.note = note;
	}

	/***
	 * @modifies: this
	 * @effects:  Adds the note passed as an argument to the current note held  
	 *            by this router
	 * 
	 */
	@Override
	public void addNote(String note) {
		// TODO Auto-generated method stub
		this.note += note;
	}

	/***
	 * @modifies: this
	 * @effects:  Clears the note held by this router, so that the note held by this router is empty
	 */
	@Override
	public void clearNote() {
		this.note = "";
	}

	@Override
	public String getHostname() {
		
		return this.hostname;
	}

	@Override
	public String getIPAddress() {
		
		return this.ipAddress;
	}

	@Override
	public boolean isPatched() {
		
		return this.patched;
	}

	@Override
	public String getOSVersion() {
		
		return this.osVersion;
	}
	
	@Override
	public String getNote(){
		return this.note;
	}

}
