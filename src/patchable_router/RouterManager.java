package patchable_router;

import java.nio.file.InvalidPathException;
import java.util.HashSet;
import java.util.Set;

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
		else if(router.getOSVersion() < 12){
			return false;
		}
	
		/*Check if there any routers that share a hostname or IP address with 
		 * the current router*/
		String ipAddress = router.getIPAddress();
		String hostname = router.getHostname();
		int ipAddressCount = 0;
		int hostnameCount = 0;
		
		for(IRouter currentRouter: this.routerCollection){
			if(currentRouter.getHostname().equals(hostname)){
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
	 * @modifies: this
	 * @effects: - If csvSourcePath == null || csvSourcePath isn't a valid path of a 
	 *             CSV file, then InvalidPathException is thrown
	 *           - Otherwise data is read from the CSV file, replacing the current data on routers
	 *             held by this with the data read from the file                                               
	 */
	@Override
	public void readData(String csvSourcePath) throws InvalidPathException{
		throw new InvalidPathException(csvSourcePath, "");
	}

}
