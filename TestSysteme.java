/* Squelette de fichier de test pour JUNIT 4 */

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.fail;
/* Il y a d'autre sortes d'assert, si besoin */
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;

public class TestSysteme {

	Systeme sys;
	Processus p1;
	Processus p2;
	Processus p3;
	Processus p4;
	Processus p5;
	Processus p6;

	/* Executee avant chaque methode de test. */
	@Before
	public void setUp() {
		sys = new Systeme();
		p1 = new Processus("proc1");
		p2 = new Processus("proc2");
		p3 = new Processus("proc3");
		p4 = new Processus("proc4");
		p5 = new Processus("proc5");

		sys.init();
	}

	/*
	 * Si besoin ajouter une methode avec l'annotation @After pour executer une
	 * methode apres chaque test
	 */

	/* Test qui doit se terminer sans levee d'exception */
	@Test
	public void currentWhenInit() throws ErreurSysteme{
			assertTrue(sys.isCurrent(null));
			sys.add(p1);
			assertEquals(sys.current(), p1);
	}

	@Test
	public void testCurrent() throws ErreurSysteme {
		if (sys.isCurrent(null)) {
			assertTrue(sys.getWaiting().isEmpty());
		}
	}

	@Test
	public void testWaiting() throws ErreurSysteme {
		if(!sys.isCurrent(null)){
			assertFalse(sys.getWaiting().contains(sys.current()));
		}
		
	}

	@Test
	public void testLast() throws ErreurSysteme {
		sys.add(p1);
		sys.add(p2);
		sys.swap();
		if (!sys.isLast(null)) {
			assertTrue(sys.getWaiting().contains(p1));
			assertNotEquals(p1,sys.current());
			assertEquals(p2,sys.current());
		}
	}

	/* Test dont l'excution doit lever l'exception indiquee. */
	// @Test(expected = ErreurSysteme.class)

	@Test
	public void testAddOneProc() throws ErreurSysteme {
		sys.add(p1);
		assertTrue(sys.isCurrent(p1));
	}
	

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestSysteme.class);
	}
}
