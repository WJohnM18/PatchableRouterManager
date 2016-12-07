package test;

import static org.junit.Assert.*;

import java.nio.file.InvalidPathException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import patchable_router.IRouter;
import patchable_router.IRouterManager;
import patchable_router.Router;
import patchable_router.RouterManager;

/***
 * Purpose:
 *     Used to test the RouterManager class and ensure that it's functionality works 
 *     correctly
 * @author williammacdonald
 *
 */
public class RouterManagerTests {
    
	@Test
	public void testRouterManagerConstruction(){
		IRouterManager routerManager = new RouterManager();
	}
	
	@Test(expected=InvalidPathException.class)
	public void testReadDataNonExistantFilePath() throws InvalidPathException{
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("rubbish path");
	}
	
	@Test(expected=InvalidPathException.class)
	public void testReadDataNonCSVFilePath() throws InvalidPathException{ 
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("RouterTests.java");
	}
	
	@Test
	public void testReadDataCSVFile(){
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("invalid_format.csv");
	}
	
	/***
	 * This test is assuming that getRouters() works correctly
	 *
	 * This is testing to see if the correct data is read when some of the rows
	 * aren't valid e.g. if a row doesn't have the correct number of columns, 
	 * that rows data isn't read
	 */
	@Test
	public void testCorrectRoutersReadInvalidFormat(){
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("invalid_format.csv");
		
	    Set<IRouter> testRouters = new HashSet<IRouter>();
	    testRouters.add(new Router("A.example.COM", "1.1.1.1", false, 11, "Faulty fans"));
	    
	    Set<IRouter> routers = routerManager.getRouters();
	    
	    assertTrue(routers.equals(testRouters));
	}
	
	/***
	 * This test is assuming that getRouters() works correctly
	 * 
	 * This is testing to see if the correct data is read when some of the rows 
	 * contain invalid data e.g. the IP address is not the correct format
	 */
	@Test
	public void testCorrectRoutersReadInvalidData(){
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("invalid_data.csv");
		
		Set<IRouter> testRouters = new HashSet<IRouter>();
		testRouters.add(new Router("c.example.com", "1.1.1.5", false, 12, "Case a bit loose"));
		testRouters.add(new Router("e.example.com", "1.1.1.6", false, 12.3, ""));
		testRouters.add(new Router("f.example.com", "1.1.1.7", false, 12.200, ""));
		testRouters.add(new Router("g.example.com", "1.1.1.6", false, 15.0, "Guarded by sharks with lasers on their heads"));
		
		Set<IRouter> routers = routerManager.getRouters();
		
		assertTrue(routers.equals(testRouters));
	}
	
	/***
	 * This test is assuming that getRouters() works correctly
	 * 
	 * This is testing to see if the correct data is read when all of the data
	 * is valid
	 */
	@Test
	public void testCorrectRoutersReadValidData(){
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("valid_data.csv");
		
		Set<IRouter> testRouters = new HashSet<IRouter>();
		testRouters.add(new Router("A.example.COM", "1.1.1.1", false, 11, "Faulty fans"));
		testRouters.add(new Router("b.example.com", "1.1.1.2", false, 13, "Behind the other routers so no one sees it"));
		testRouters.add(new Router("C.EXAMPLE.COM", "1.1.1.3", false, 12.1, ""));
		testRouters.add(new Router("d.example.com", "1.1.1.4", true, 14, ""));
		testRouters.add(new Router("c.example.com", "1.1.1.5", false, 12, "Case a bit loose"));
		testRouters.add(new Router("e.example.com", "1.1.1.6", false, 12.3, ""));
		testRouters.add(new Router("f.example.com", "1.1.1.7", false, 12.200, ""));
		testRouters.add(new Router("g.example.com", "1.1.1.6", false, 15.0, "Guarded by sharks with lasers on their heads"));
		
		Set<IRouter> routers = routerManager.getRouters();
		
		assertTrue(routers.equals(testRouters));
	}
	
	/***
	 * This is testing that the routers that are returned are all of the patchable
	 * routers
	 */
	@Test
	public void testCorrectPatchableRouters(){
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("valid_data.csv");
		
		Set<IRouter> testRouters = new HashSet<IRouter>();
		testRouters.add(new Router("b.example.com", "1.1.1.2", false, 13, "Behind the other routers so no one sees it"));
		testRouters.add(new Router("f.example.com", "1.1.1.7", false, 12.200, ""));
		
		Set<IRouter> routers = routerManager.getPatchableRouters();
		
		assertTrue(routers.equals(testRouters));
	}
}
