/* Squelette de fichier de test pour JUNIT 4 */

	import org.junit.Test;
	import org.junit.Before;
	import org.junit.After;
	import static org.junit.Assert.fail;
/* Il y a d'autre sortes d'assert, si besoin */
	import static org.junit.Assert.assertTrue;
	import static org.junit.Assert.assertFalse;
	import static org.junit.Assert.assertEquals;
	import junit.framework.JUnit4TestAdapter;
	
	public class TestSysteme { 
	
	    /* Executee avant chaque methode de test. */
	    @Before public void setUp () {
	    }
	
	    /* Si besoin ajouter une methode avec l'annotation @After
	     * pour executer une methode apres chaque test
	     */
	
	    /* Test qui doit se terminer sans levee d'exception */
	    @Test public void test1() { 
	    }
	

	    /* Test dont l'excution doit lever l'exception indiquee. */
	    @Test(expected=ErreurSysteme.class)
	    public void test2() throws ErreurSysteme { }


	    public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestSysteme.class);
	    }
	}
