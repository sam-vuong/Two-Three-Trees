import static org.junit.Assert.*;

import org.junit.Test;

public class Over3DepthTest {

	@Test
	public void testDepthOverThree() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);
		
		t.insert(21);
		t.insert(22);
		t.insert(23);
		t.insert(24);
		
		String expected = "9 21";
		assertEquals(expected, t.search(9));
		assertEquals(expected, t.search(21));
		
		t.insert(25);
		t.insert(26);
		t.insert(27);
		t.insert(28);
		
		expected = "21";
		assertEquals(expected, t.search(21));
		expected = "25";
		assertEquals(expected, t.search(25));
		expected = "23";
		assertEquals(expected, t.search(23));
		expected = "27";
		assertEquals(expected, t.search(27));
		expected = "22";
		assertEquals(expected, t.search(22));
		expected = "24";
		assertEquals(expected, t.search(24));
		expected = "26";
		assertEquals(expected, t.search(26));
		expected = "28";
		assertEquals(expected, t.search(28));
	}

}
