package patchable_router;

import java.nio.file.InvalidPathException;
import java.util.Set;

public interface IRouterManager {

	/***
	 * @effects: Returns all of the routers that can be patched
	 */
	public Set<IRouter> getPatchableRouters();
	
	
	/***
	 * @modifies: this
	 * @effects: - If csvSourcePath == null || csvSourcePath isn't a valid path of a 
	 *             CSV file, then InvalidPathException is thrown 
	 *           - Otherwise data is read from the CSV file, replacing the current data on routers
	 *             held by this with the data read from the file                                               
	 */
	public void readData(String csvSourcePath) throws InvalidPathException;
	
	/***
	 * @effects: Returns all of the routers stored by this
	 */
	public Set<IRouter> getRouters();
}
