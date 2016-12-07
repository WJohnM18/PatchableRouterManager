package test;

import static org.junit.Assert.*;

import org.junit.Test;

import patchable_router.IRouter;
import patchable_router.Router;


/***
 *
 *              NOTE:  These Tests were written using JUnit 4
 * 
 * Purpose:
 *     Used to test that the functionality of the Router class
 *     is correct
 *     
 * @author williammacdonald
 *
 */
public class RouterTests {

	@Test
	public void testRouterConstructor(){
		new Router("a.example.com", "1.1.1.1", false, 12.5, "testnote");
	}
	
	@Test
	public void testGetHostname(){
		String hostname = "a.example.com";
		IRouter router = new Router(hostname, "1.1.1.1", false, 12.5, "testnote");
		assertEquals(router.getHostname(), hostname);
	}
	
	@Test
	public void testGetIPAddress(){
		String ipAddress = "1.1.1.1";
		IRouter router = new Router("a.example.com", ipAddress, false, 12.5, "testnote");
	    assertEquals(router.getIPAddress(), ipAddress);
	}
	
	@Test
	public void testIsPatched(){
		boolean patched = false;
		IRouter router = new Router("a.example.com", "1.1.1.1", patched, 12.5, "testnote");
		assertEquals(router.isPatched(), patched);
	}
	
	@Test
	public void testGetOSVersion(){
		double OSVersion = 12.200;
		IRouter router = new Router("a.example.com", "1.1.1.1", false, OSVersion, "testnote");
		assertTrue(router.getOSVersion() == OSVersion);
	}
	
	@Test
	public void testGetNote(){
		String note = "testnote";
		IRouter router = new Router("a.example.com", "1.1.1.1", false, 12.5, note);
		assertEquals(router.getNote(), note);
	}
	
	@Test
	public void testAddNote(){
		String note = "testnote";
		IRouter router = new Router("a.example.com", "1.1.1.1", false, 12.5, note);
		
		String noteAddOn = "add this to note";
		
		router.addNote(noteAddOn);
		
		assertEquals(router.getNote(), (note + noteAddOn));
	}
	
	@Test
	public void testSetHostname(){
		String hostname = "a.example.com";
		IRouter router = new Router(hostname, "1.1.1.1", false, 12.5, "testnote");
		
		String newHostname = "some new hostname";
		router.setHostname(newHostname);
		
		assertEquals(newHostname, router.getHostname());
	}
	
	@Test
	public void testSetIPAddress(){
		String ipAddress = "1.1.1.1";
		IRouter router = new Router("a.example.com", ipAddress, false, 12.5, "testnote");
		
		String newIPAddress = "127.0.0.1";
		router.setIPAddress(newIPAddress);
		
	    assertEquals(router.getIPAddress(), newIPAddress);
	}
	
	@Test
	public void testSetPatched(){
		boolean patched = false;
		IRouter router = new Router("a.example.com", "1.1.1.1", patched, 12.5, "testnote");
		
		boolean newPatched = true;
		router.setPatched(newPatched);
		
	    assertEquals(router.isPatched(), newPatched);
	}
	
	@Test
	public void testSetOSVersion(){
		double osVersion = 12.000;
		IRouter router = new Router("a.example.com", "1.1.1.1", false, osVersion, "testnote");
		
		double newOSVersion = 45.678;
		router.setOSVersion(newOSVersion);
		
	    assertTrue(router.getOSVersion() == newOSVersion);
	}
	
	@Test
	public void testSetNote(){
		String note = "original note";
		IRouter router = new Router("a.example.com", "1.1.1.1", false, 12.5, note);
		
		String newNote = "this is my new note";
		router.setNote(newNote);
		
	    assertEquals(router.getNote(), newNote);
	}
	
	@Test
	public void testClearNote(){
		String note = "original note";
		IRouter router = new Router("a.example.com", "1.1.1.1", false, 12.5, note);
		
		router.clearNote();
		
	    assertEquals(router.getNote(), "");
	}
	
	@Test
	public void testAddNoteAfterClearNote(){
		String note = "original note";
		IRouter router = new Router("a.example.com", "1.1.1.1", false, 12.5, note);
		
		String noteToAdd = "note to add";
		router.clearNote();
		router.addNote(noteToAdd);
		
	    assertEquals(router.getNote(), noteToAdd);
	}
	
	@Test
	public void testInequalRouters(){
		IRouter routerOne = new Router("a.example.com", "1.1.1.1", false, 12.5, "");
		IRouter routerTwo = new Router("b.example.com", "1.1.1.4", true, 7, "");
		
		assertFalse(routerOne.equals(routerTwo));
	}
	
	
}
