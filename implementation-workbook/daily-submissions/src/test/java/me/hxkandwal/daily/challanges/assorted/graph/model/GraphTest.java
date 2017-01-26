package me.hxkandwal.daily.challanges.assorted.graph.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testReadUndirectedGraph() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(System.getProperty("user.dir") + 
					"/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/graph-construction-sample.txt"));
		
		Graph graph = Graph.readGraph (sc, false);
		
		assertEquals(5, graph.getNumVertices());
		assertEquals(6, graph.getNumEdges());
		assertEquals(3, graph.getVertices()[0].getAdjacentEdges().size());
		assertEquals(2, graph.getVertices()[1].getAdjacentEdges().size());
		assertEquals(2, graph.getVertices()[2].getAdjacentEdges().size());
		assertEquals(3, graph.getVertices()[3].getAdjacentEdges().size());
		assertEquals(2, graph.getVertices()[4].getAdjacentEdges().size());
	}
	
	@Test
	public void testReadDirectedGraph() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(System.getProperty("user.dir") + 
					"/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/graph-construction-sample.txt"));
		
		Graph graph = Graph.readGraph (sc, true);
		
		assertEquals(5, graph.getNumVertices());
		assertEquals(6, graph.getNumEdges());
		assertEquals(3, graph.getVertices()[0].getAdjacentEdges().size());
		assertEquals(1, graph.getVertices()[1].getAdjacentEdges().size());
		assertEquals(1, graph.getVertices()[2].getAdjacentEdges().size());
		assertEquals(1, graph.getVertices()[3].getAdjacentEdges().size());
		assertEquals(0, graph.getVertices()[4].getAdjacentEdges().size());
	}	

}
