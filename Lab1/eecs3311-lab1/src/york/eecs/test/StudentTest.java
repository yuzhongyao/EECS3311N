package york.eecs.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import york.eecs.source.UndirectedGraph;
import york.eecs.source.UndirectedGraphAlgorithms;
import york.eecs.source.VertexExistsException;

public class StudentTest {

	 /**
	  * TODO: Please write at least 5 test cases for testing @UndirectedGraph.
	  * TODO: Please write at least 5 test cases for testing @UndirectedGraphAlgorithms.
	  */

	UndirectedGraphAlgorithms<String> uga;
	@Before
	public void setUp() throws Exception {
		uga = new UndirectedGraphAlgorithms<>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testIsEmpty() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		boolean b = ug.isEmpty();
		boolean ugExpected = true;
		assertEquals(ugExpected, b);
	}
	
	@Test
	public void testEmptySize() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		int ugExpected = 0;
		assertEquals(ugExpected, ug.getSize());
	}
	
	@Test
	public void testAddingSelfEdge() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addEdge("A", "A");
		}
		catch (VertexExistsException e) {
			e.printStackTrace();
		}
		List<String> s = ug.getAdjacent("A");
		String expected = "A";
		String actual = s.get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSizeAfterAdding() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
		}
		catch (VertexExistsException e) {
			e.printStackTrace();
		}
		int ugExpected = 1;
		assertEquals(ugExpected, ug.getSize());
	}
	
	@Test
	public void testIsEmptyAfterAdding() throws VertexExistsException {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
		ug.addVertex("A");
		}
		catch (VertexExistsException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		boolean b = ug.isEmpty();
		boolean ugExpected = false;
		assertEquals(ugExpected, b);
	}
	
	@Test
	public void testUnorderedToString() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("A");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String toSt = ug.toString();
		String expected = "Graph:\nVertex: A & Adjacent Vertices: [B, C]\nVertex: B & Adjacent Vertices: [A]\nVertex: C & Adjacent Vertices: [A]\n";
	    assertEquals(expected, toSt);
	}
	
	@Test
	public void testLargeUnorderedToString() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("A");
			ug.addVertex("D");
			ug.addVertex("G");
			ug.addVertex("E");
			ug.addVertex("J");
			ug.addVertex("I");
			ug.addVertex("F");
			ug.addVertex("K");
			ug.addVertex("H");
			ug.addEdge("A", "E");
			ug.addEdge("A", "D");
			ug.addEdge("I", "D");
			ug.addEdge("A", "B");
			ug.addEdge("J", "E");
			ug.addEdge("F", "C");
			ug.addEdge("I", "K");
			ug.addEdge("E", "H");
			ug.addEdge("E", "A");
			ug.addEdge("E", "G");
			ug.addEdge("B", "C");
			ug.addEdge("J", "G");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String toSt = ug.toString();
		String expected = "Graph:\nVertex: A & Adjacent Vertices: [B, D, E]\nVertex: B & Adjacent Vertices: [A, C]\nVertex: C & Adjacent Vertices: [B, F]\n"
				+ "Vertex: D & Adjacent Vertices: [A, I]\nVertex: E & Adjacent Vertices: [A, G, H, J]\nVertex: F & Adjacent Vertices: [C]\nVertex: G & Adjacent Vertices: [E, J]\n"
				+ "Vertex: H & Adjacent Vertices: [E]\nVertex: I & Adjacent Vertices: [D, K]\nVertex: J & Adjacent Vertices: [E, G]\nVertex: K & Adjacent Vertices: [I]\n";
	    assertEquals(expected, toSt);
	}
	
	@Test
	public void testNoAdjacentsToString() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("A");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String toSt = ug.toString();
		String expected = "Graph:\nVertex: A & Adjacent Vertices: []\nVertex: B & Adjacent Vertices: []\nVertex: C & Adjacent Vertices: []\n";
	    assertEquals(expected, toSt);
	}
	
	@Test
	public void testBFS() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("G");
			ug.addVertex("F");
			ug.addVertex("E");
			ug.addVertex("D");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
			ug.addEdge("A", "G");
			ug.addEdge("B", "E");
			ug.addEdge("F", "C");
			ug.addEdge("F", "D");
			ug.addEdge("G", "F");
			ug.addEdge("G", "E");
			ug.addEdge("B", "F");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "B", "G");
		List<String> expected = new ArrayList<>();
		expected.add("B");
		expected.add("F");
		expected.add("G");
		assertEquals(expected, result);
	}
	
	@Test
	public void testBFS2() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("G");
			ug.addVertex("F");
			ug.addVertex("E");
			ug.addVertex("D");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
			ug.addEdge("A", "G");
			ug.addEdge("B", "E");
			ug.addEdge("F", "C");
			ug.addEdge("F", "D");
			ug.addEdge("G", "F");
			ug.addEdge("G", "E");
			ug.addEdge("B", "F");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "D", "E");
		List<String> expected = new ArrayList<>();
		expected.add("D");
		expected.add("F");
		expected.add("G");
		expected.add("E");
		assertEquals(expected, result);
	}
	
	@Test
	public void testBFSNoPathBetween() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("G");
			ug.addVertex("F");
			ug.addVertex("E");
			ug.addVertex("D");
			ug.addVertex("Z");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
			ug.addEdge("A", "G");
			ug.addEdge("B", "E");
			ug.addEdge("F", "C");
			ug.addEdge("F", "D");
			ug.addEdge("G", "F");
			ug.addEdge("G", "E");
			ug.addEdge("B", "F");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "A", "Z");
		List<String> expected = new ArrayList<>();
		assertEquals(expected, result);
	}
	
	
	
	
	
}
