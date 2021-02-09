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
	
	 // TESTS INVARIANTS

	/* Test qui doit se terminer sans levee d'exception */
	@Test
	public void currentWhenInit() throws ErreurSysteme{
			assertTrue(sys.isCurrent(null));
			sys.add(p1);
			assertEquals(sys.current(), p1);
	}

	// > si current est null, alors last est null et waiting est vide.
	@Test
	public void testCurrentNull() throws ErreurSysteme {
		if (sys.isCurrent(null)) {
			assertTrue(sys.getWaiting().isEmpty());
		}
	}

	// > current n'appartient jamais à waiting
	@Test
	public void testWaiting() throws ErreurSysteme {
		if(!sys.isCurrent(null)){
			assertFalse(sys.getWaiting().contains(sys.current()));
		}
		
	}

	// > Si last est non null, il fait partie de la collection waiting et est différent de current.
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
	
	// TESTS SWAP

	/* > Swap lève l'exception ErreurSystème (sous-classe de Exception) s’il existe n'existe
aucun processus en attente de la ressource et laisse l'état inchangé. */
	@Test(expected = ErreurSysteme.class)
	public void testNoProcessusWaiting() throws ErreurSysteme{
		sys.add(p1);
		sys.swap();
	}
	
	/* À la sortie de swap ce n’est plus le processus actuel qui dispose de la ressource, le
possesseur à l’entrée a rejoint la liste des demandeurs et on se souvient que c’est lui qui
détenait la ressource au moment de l’échange. Si on a le choix on attribue la ressource à un
processus autre que celui qui l’avait lors de l’échange précédent. Le système peut choisir
n’importe quel processus demandeur parmi ceux qui respectent les contraintes précédentes.*/
	@Test
	public void testCurrentAfterSwap() throws ErreurSysteme{
		sys.add(p1);
		sys.add(p2);
		sys.swap();
		assertTrue(sys.isCurrent(p2));
		assertTrue(sys.isLast(p1));
		assertTrue(sys.getWaiting().contains(p1));
		assertFalse(sys.getWaiting().contains(p2));
	}

	@Test(expected = ErreurSysteme.class)
	public void testAddSameProc() throws ErreurSysteme{
		sys.add(p1);
		sys.add(p1);
	}


	@Test
	public void testNumberWaiting() throws ErreurSysteme{
		sys.add(p1);
		sys.add(p2);
		
		assertEquals(sys.getWaiting().size(), 1);
		sys.swap();
		assertEquals(sys.getWaiting().size(), 1);
		sys.swap();
		assertEquals(sys.getWaiting().size(), 1);

		sys.add(p3);
		
		assertEquals(sys.getWaiting().size(), 2);
		sys.swap();
		assertEquals(sys.getWaiting().size(), 2);
		sys.swap();
		assertEquals(sys.getWaiting().size(), 2);
	}

	// TESTS SYSTEME

	/* renvoie le processus qui détient actuellement la ressource ou
lève l'exception ErreurSysteme s'il n'existe aucun tel processus. */
	@Test(expected = ErreurSysteme.class)
	public void testNoCurrent() throws ErreurSysteme{
		sys.current();
	}
	
	@Test
	public void testCurrent() throws ErreurSysteme{
		sys.add(p1);
		assertEquals(sys.current(), p1);
	}








	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestSysteme.class);
	}
}
