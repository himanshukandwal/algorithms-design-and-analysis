package challenges.assorted.graph;

import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

public class SpreadsheetCalculator extends AbstractCustomTestRunner {

	private static final String regex = ".*[A-Z]+.*";
	private static final String regexOperators = ".*[\\+\\*\\-\\s/].*";
	
	public static class CellNode {		
		
        public String cellName;
		public String cellvalue;
		public List<CellNode> dependents;
		public List<CellNode> requestors;
		public String expression;
		public boolean isResolved;
		public boolean seen;
		
		public CellNode(String cellName) {
			this.cellName = cellName;
			this.dependents = new ArrayList<>();
			this.requestors = new ArrayList<>();
		}
		
		public String getCellName() {
			return cellName;
		}
		
		public String getCellvalue() {
			return cellvalue;
		}
		
		public void setCellvalue(String cellvalue) {
			this.cellvalue = cellvalue;
		}
		
		public void setExpression(String expression) {
			this.expression = expression;
		}
		
		@Override
		public String toString() {
			return cellName;
		}
		
    }
	
    public static void main(String args[] ) throws Exception {
    	Scanner sc = new Scanner (new File(System.getProperty("user.dir") + "/src/test/resources/me/hxkandwal/daily/challanges/assorted/graph/spreadsheet-calculator-sample-4.txt"));
        int cols = sc.nextInt();
        int rows = sc.nextInt();
        sc.nextLine();
        
        Map<String, CellNode> metamap = new LinkedHashMap<>();
        
        // Metamap creation.
        for (int row = 'A'; row < ('A' + rows); row ++) {
            for (int col = 1; col <= cols; col ++) {
                String name = String.valueOf((char) row) + col;
                metamap.put(name, new CellNode(name));
            }   
        }
        
        // CellNode population.
        for (int row = 'A'; row < ('A' + rows); row ++) {
            for (int col = 1; col <= cols; col ++) {
            	String name = String.valueOf((char) row) + col;
            	String expression = sc.nextLine();
            	
            	CellNode cellNode = metamap.get(name);
            	if (expression.matches(regex)) {
					for (String component : expression.split(" "))  {
						component = component.trim();
						
						if (component.matches(regex)) {
							metamap.get(component).requestors.add(cellNode);
							cellNode.dependents.add(metamap.get(component));
						}
					}
					cellNode.setExpression(expression);
				} else {
					cellNode.isResolved = true;
					
					// evaluate
					cellNode.cellvalue = rpnResolver(expression);
				}
            }
        }
        
        sc.close();
        
        // check cyclicity.
        if (containCycles(metamap)) 
        	System.out.println("Error: Circular dependency!");
        else {
	        while (!isResolved (metamap)) {
	        	for (CellNode node : metamap.values()) 
	        		if (node.isResolved) {
	        			recursiveResolver(node);
	        		}
	        }
	        
	        for (CellNode node : metamap.values())
	        	System.out.println(node.cellvalue);
        }
    }
    
    
    private static void recursiveResolver (CellNode resolvedCellNode) {
    	
    	for (CellNode cellNode : resolvedCellNode.requestors) {
    		if (!cellNode.isResolved) {
				cellNode.expression = cellNode.expression.replace(resolvedCellNode.cellName, resolvedCellNode.cellvalue);
				if (!cellNode.expression.matches(regex)) {
					cellNode.cellvalue = (cellNode.expression.matches(regexOperators) ? rpnResolver(cellNode.expression) : cellNode.expression);
					cellNode.expression = null;
					cellNode.isResolved = true;
					
					recursiveResolver(cellNode);
				}	
    		}
		}
    }
    
    private static boolean containCycles(Map<String, CellNode> metamap) {
		for (CellNode node : metamap.values()) { 
			if (!node.seen && node.requestors.size() == 0) {
				Set<String> recordedCells = new HashSet<>();
				recordedCells.add(node.getCellName());
				
				if (performDFS(node, recordedCells))
					return true;
			}
		}
		return false;
	}
	
	private static boolean performDFS (CellNode node, Set<String> recordedCells) {
		if (node.dependents.size() == 0)
			node.seen = true;
		else {
			for (CellNode otherNode : node.dependents) {
				
				if (recordedCells.contains(otherNode.getCellName()))
					return true;
				
				if (!otherNode.seen)  {
					recordedCells.add(otherNode.getCellName());
					
					if (performDFS(otherNode, recordedCells))
						return true;
				}
			}
		}
		
		recordedCells.remove(node.getCellName());
		return false;
	}
	
	// fail-fast resolution checker.
    private static boolean isResolved (Map<String, CellNode> map) {
    	for (CellNode node : map.values()) 
    		if (!node.isResolved)
    			return false;
    	return true;
    }
    
    private static String rpnResolver(String expression) {
    	String[] str = expression.split(" ");
		Stack<String> stack = new Stack<>();

		MathContext mc = new MathContext(5, RoundingMode.HALF_UP);
		
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals("*") || str[i].equals("+") || str[i].equals("-")
					|| str[i].equals("/")) {
				
				BigDecimal operand2 =  new BigDecimal(stack.pop());
				BigDecimal operand1 =  new BigDecimal(stack.pop());
				String operator = str[i];
				
				BigDecimal result = null;
				switch (operator) {
				case "*":
					result =  operand1.multiply(operand2, mc);
					break;

				case "+":
					result = operand1.add(operand2, mc);
					break;

				case "-":
					result = operand1.subtract(operand2, mc);
					break;

				case "/":
					result = operand1.divide(operand2, mc);
					break;
				default:
					break;
				}
				stack.push(result.setScale(5, BigDecimal.ROUND_HALF_UP).toString());
			} else {
				stack.push(str[i]);
			}
		}

		return new BigDecimal(stack.pop()).setScale(5, BigDecimal.ROUND_HALF_UP).toString();
	}

}
