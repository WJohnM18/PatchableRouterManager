package patchable_router;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * Purpose:
 *     Used to display information about the routers read from a CSV file
 * @author williammacdonald
 *
 */
public class RouterManager implements IRouterManager{
	
	//The collection that holds data on all of our routers
	private Set<IRouter> routerCollection;
	
	public RouterManager(){
		this.routerCollection = new HashSet<IRouter>();
	}
	
	/***
	 * @effects: Returns all of the routers that can be patched
	 */
	@Override
	public Set<IRouter> getPatchableRouters(){
		Set<IRouter> routersThatCanBePatched = new HashSet<IRouter>();
		
		for(IRouter currentRouter: this.routerCollection){
			if(canBePatched(currentRouter)){
				routersThatCanBePatched.add(currentRouter);
			}
		}
		
		return routersThatCanBePatched;
	}
	
	/***
	 * @effects: Returns all of the routers held by this
	 */
	@Override
	public Set<IRouter> getRouters(){
		return this.routerCollection;
	}
	
	/***
	 * @effects - Returns true if the given router can be patched
	 *            The condition(s) that determine if a router can be patched or not 
	 *            are as follows:
	 *                x The router has not already been patched
	 *                x The current OS Version for the router OS is 12 or above
	 *                x There are no other routers that share the same IP Address
	 *                x There are no other routers which share the same hostname
	 *                
	 *          - Otherwise returns false
	 */
	private boolean canBePatched(IRouter router){
		
		if(router.isPatched()){
			return false;
		}
		else if(Double.parseDouble(router.getOSVersion()) < 12){
			return false;
		}
	
		/*Check if there any routers that share a hostname or IP address with 
		 * the current router*/
		String ipAddress = router.getIPAddress();
		String hostname = router.getHostname();
		int ipAddressCount = 0;
		int hostnameCount = 0;
		
		for(IRouter currentRouter: this.routerCollection){
			if((currentRouter.getHostname().toLowerCase()).equals(hostname.toLowerCase())){
				hostnameCount++;
				if(hostnameCount > 1){
					return false;
				}
			}
			
			if(currentRouter.getIPAddress().equals(ipAddress)){
				ipAddressCount++;
				if(ipAddressCount > 1){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	/***
	 * Checks if the given string has the format of a valid OS Version
	 * (so does the string only contain numerical values with or without a decimal point)
	 * Returns true if the string represents a double
	 * Otherwise returns false
	 */
	private boolean validOSVersion(String osVersion){
		try{
		    Double.parseDouble(osVersion);
		    return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	
	/***
	 * Checks if the given string has the format of a valid Patched string 
	 * (so 'yes' or 'no' case insensitive)
	 * Returns true if the string matches 'yes' or 'no' case insensitive
	 * Otherwise returns false
	 */
	private boolean validPatched(String patched){
		
		if(patched.toLowerCase().equals("no") || patched.toLowerCase().equals("yes")){
			return true;
		}

		return false;
	}
	
	/***
	 * Checks if the given string has the format of a valid IP address
	 * 
	 * Returns true if the given string has the same format as a valid IPv4 address
	 * Otherwise returns false
	 */
	private boolean validIPAddress(String ipAddress){
		
		Pattern p = Pattern.compile("^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])"
				+ "\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])"
				+ "\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])"
				+ "\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]))");
		
		Matcher m = p.matcher(ipAddress);
		
		return m.matches();
	}
	
	/***
	 * Checks if the given string has the format of a hostname
	 * Returns true if the given string is the same format as a valid hostname
	 * Otherwise returns false
	 * @return
	 */
	private boolean validHostname(String hostname){
		Pattern p = Pattern.compile("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*"
				                     + "([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$");
		
		Matcher m = p.matcher(hostname);
		
		return m.matches();
	}
	
	/***
	 * Returns the number of commas in the given string.
	 * If null is given, then 0 is returned
	 */
	private int countCommas(String stringToCount){
		if(null == stringToCount){
			return 0;
		}
		else{
			int commaCount = 0;
			for(int i = 0; i < stringToCount.length(); i++){
				if(',' == stringToCount.charAt(i)){
					commaCount++;
				}
			}
			
			return commaCount;
		}
	}
	
	/***
	 * Checks if the data contained in the given string is valid
	 * router data
	 * Returns true if the string contains valid router data
	 * @return
	 */
	private boolean validData(String routerData){
	
		if(4 != countCommas(routerData)){
			return false;
		}
		
		String[] data = routerData.split(",");
		
		String hostname = data[0];
		String ipAddress = data[1];
		String patchedString = data[2];
		String osVersionString = data[3];
		
		if(validHostname(hostname)
           && validIPAddress(ipAddress)
           && validPatched(patchedString)
           && validOSVersion(osVersionString)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/***
	 * Creates an IRouter object from the given string of router data.
	 * If the router data is invalid, then a null object will be returned
	 * @param routerData
	 * @return
	 */
	private IRouter createRouter(String routerData){
		if(!validData(routerData)){
			return null;
		}
		else{
			String[] data = routerData.split(",");
			
			String hostname = data[0];
			String ipAddress = data[1];
			boolean patched = data[2].toLowerCase().equals("yes");
			String osVersion = data[3];
			
			String note = "";
			if(5 == data.length){
				note = data[4];
			}
		
			
			IRouter router = new Router(hostname, ipAddress, patched, osVersion, note);
			
			return router;
		}
	}
	
	/***
	 * @modifies: this
	 * @effects: - If csvSourcePath == null || csvSourcePath isn't a valid path of a 
	 *             CSV file, then FileNotFoundException is thrown
	 *           - Otherwise data is read from the CSV file, replacing the current data on routers
	 *             held by this with the data read from the file                                               
	 */
	@Override
	public void readData(String csvSourcePath) throws FileNotFoundException{
		
		if(null == csvSourcePath){
			throw new FileNotFoundException("Path must be the path of an existing CSV file");
		}
		
		//Check if it is a CSV file, if not throw an exception
		String extension = csvSourcePath.substring(csvSourcePath.lastIndexOf('.')+1);
		if(!extension.equals("csv")){
			throw new FileNotFoundException("Path must be the path of an existing CSV file");
		}
		
		//Clear the router collection
		this.routerCollection = new HashSet<IRouter>();
		
		BufferedReader in = new BufferedReader(new FileReader(csvSourcePath));
		String currentLine = "";
		try {
			while(null != (currentLine = in.readLine())){
			    if(validData(currentLine)){
			    	this.routerCollection.add(createRouter(currentLine));
			    }
            }
			
			in.close();
		} catch (IOException e) {
			throw new FileNotFoundException("Path must be the path of an existing CSV file");
		}
	}

}
