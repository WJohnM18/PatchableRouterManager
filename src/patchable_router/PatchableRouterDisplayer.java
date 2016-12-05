package patchable_router;

import java.nio.file.InvalidPathException;
import java.util.Set;

import src.UserInterface;

/***
 * Purpose:
 *     Used to read router data from a CSV file and then display the information about
 *     which routers can and cannot be patched to the user
 * @author williammacdonald
 *
 */
public class PatchableRouterDisplayer {
	
	private IRouterManager routerManager;
	private UserInterface UI;
	private Set<IRouter> patchableRouters;
	
	public PatchableRouterDisplayer(UserInterface UI, String csvSourcePath){
		
		this.routerManager = new RouterManager();
		this.UI = UI;
		
		if(this.updatePatchableRouterData(csvSourcePath)){
			this.displayPatchableRouters();
		}
		else{
			UI.alertError(csvSourcePath + " is not a valid .csv file path");
		}
		
	}
	
	
	/***
	 * @effects: Displays the information of any routers that can be patched.
	 *           Otherwise displays a message informing the user that there are no
	 *           patchable routers
	 *           
	 */
	public void displayPatchableRouters(){
			
		if(patchableRouters.isEmpty()){
			UI.display("None of the routers can be patched");
        }
		else{
		    String routerInfo = "";
		    for(IRouter router: patchableRouters){
			
			    routerInfo = router.getHostname() + "   (" + router.getIPAddress() + ")   " 
					         + "OS version " + router.getOSVersion();
			    
			    if(!router.getNote().isEmpty()){
			    	routerInfo += "[" + router.getNote() + "]";
			    }
			    
			    UI.display(routerInfo);
					   
		    }
		}
	}
	
	/***
	 * @modifies: this
	 * @effects: - If csvSource == valid path of a CSV file, then the patchable routers 
	 *             held by this are update with the contents of the file && true is returned
	 *           - Otherwise if the file is not a valid CSV file, then false is returned
	 */
	private boolean updatePatchableRouterData(String csvSourcePath){
		try{
			this.routerManager.readData(csvSourcePath);
		    this.patchableRouters = this.routerManager.getPatchableRouters();
		    return true;
		}
		catch(InvalidPathException e){
			return false;
		}
	}

}
