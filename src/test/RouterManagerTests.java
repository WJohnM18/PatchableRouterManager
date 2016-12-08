package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
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
	
	@Test(expected=FileNotFoundException.class)
	public void testReadDataNonExistantFilePath() throws FileNotFoundException{
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("rubbish path");
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testReadDataNonCSVFilePath() throws FileNotFoundException{ 
		IRouterManager routerManager = new RouterManager();
		routerManager.readData("RouterTests.java");
	}
	
	@Test
	public void testReadDataCSVFile() {
		IRouterManager routerManager = new RouterManager();
		try {
			routerManager.readData("invalid_format.csv");
			assertTrue(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			assertFalse(true);
		}
	}

}
