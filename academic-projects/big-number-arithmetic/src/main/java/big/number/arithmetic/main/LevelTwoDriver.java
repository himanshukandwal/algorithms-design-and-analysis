package big.number.arithmetic.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import big.number.arithmetic.model.ComplexNumberNode;
import big.number.arithmetic.model.NumberNode;

/**
 * Driver Class for level 2.
 * 
 * @author G31
 *
 */
public class LevelTwoDriver {

	public static void main(String[] args) throws FileNotFoundException {
		Queue<String> commandQueue = new LinkedList<>();
		
		Scanner inputScanner;
		int linenum;
		String cmd;
		
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			inputScanner = new Scanner(inputFile);
		} else {
			inputScanner = new Scanner(System.in);
		}

		while (inputScanner.hasNext()) {
			linenum = inputScanner.nextInt();
			cmd = inputScanner.next();
			commandQueue.add(cmd);

			System.out.println("|" + linenum + "|" + cmd + "|");
		}

		inputScanner.close();

		Map<String, NumberNode> metaMap = new HashMap<>();
		while (!commandQueue.isEmpty()) {
			String command =  commandQueue.poll().trim();
			
			if (command.contains("=") && isVariableDigit(command.split("=")[1])) {
				metaMap.put(command.split("=")[0].trim(), new ComplexNumberNode(command.split("=")[1].trim()));
				System.out.println();
				continue;
			}	

			if (!command.contains("=")) {
				NumberNode numberNode = null;
				
				if (metaMap.containsKey(command)) {
					numberNode = metaMap.get(command);
					System.out.println(" displaying variable : " + command);
					System.out.println( numberNode + " (decimal representation) ");
					numberNode.printList();
				}
				System.out.println();
				continue;
			}

			String variable = command.split("=")[0];
			String expression = command.split("=")[1];
			
			if (expression.contains("+")) {
				System.out.println(" output for : " + command);
				
				String operandA = expression.split("\\+")[0];
				String operandB = expression.split("\\+")[1];
				
				NumberNode result = ComplexNumberNode.sum(
						(isVariableDigit(operandA) ? new NumberNode(Integer.valueOf(operandA)) : metaMap.get(operandA)), 
						(isVariableDigit(operandB) ? new NumberNode(Integer.valueOf(operandB)) : metaMap.get(operandB)));
				
				System.out.println( result + " (decimal representation) ");
				result.printList();
				
				metaMap.put(variable, result);
			} else if (expression.contains("-")) {
				System.out.println(" output for : " + command);
				
				String operandA = expression.split("\\-")[0];
				String operandB = expression.split("\\-")[1];
				
				NumberNode result = ComplexNumberNode.subtract(
						(isVariableDigit(operandA) ? new NumberNode(Integer.valueOf(operandA)) : metaMap.get(operandA)), 
						(isVariableDigit(operandB) ? new NumberNode(Integer.valueOf(operandB)) : metaMap.get(operandB)));
				
				System.out.println( result + " (decimal representation) ");
				result.printList();
				
				metaMap.put(variable, result);
			} else if (expression.contains("*")) {
				System.out.println(" output for : " + command);
				
				String operandA = expression.split("\\*")[0];
				String operandB = expression.split("\\*")[1];
				
				NumberNode result = ComplexNumberNode.product(
						(isVariableDigit(operandA) ? new NumberNode(Integer.valueOf(operandA)) : metaMap.get(operandA)), 
						(isVariableDigit(operandB) ? new NumberNode(Integer.valueOf(operandB)) : metaMap.get(operandB)));
				
				System.out.println( result + " (decimal representation) ");
				result.printList();
				
				metaMap.put(variable, result);
			} else if (expression.contains("/")) {
				System.out.println(" output for : " + command);
				
				String operandA = expression.split("/")[0];
				String operandB = expression.split("/")[1];
				
				NumberNode result = ComplexNumberNode.divide(
						(isVariableDigit(operandA) ? new NumberNode(Integer.valueOf(operandA)) : metaMap.get(operandA)), 
						(isVariableDigit(operandB) ? new NumberNode(Integer.valueOf(operandB)) : metaMap.get(operandB)));
				
				System.out.println( result + " (decimal representation) ");
				result.printList();
				
				metaMap.put(variable, result);
			} else if (expression.contains("^")) {
				System.out.println(" output for : " + command);
				
				String operandA = expression.split("\\^")[0];
				String operandB = expression.split("\\^")[1];
				
				NumberNode result = ComplexNumberNode.power(
						(isVariableDigit(operandA) ? new NumberNode(Integer.valueOf(operandA)) : metaMap.get(operandA)), 
						(isVariableDigit(operandB) ? new NumberNode(Integer.valueOf(operandB)) : metaMap.get(operandB)));
				
				System.out.println( result + " (decimal representation) ");
				result.printList();
				
				metaMap.put(variable, result);
			} else if (expression.contains("%")) {
				System.out.println(" output for : " + command);
				
				String operandA = expression.split("\\%")[0];
				String operandB = expression.split("\\%")[1];
				
				NumberNode result = ComplexNumberNode.mod(
						(isVariableDigit(operandA) ? new NumberNode(Integer.valueOf(operandA)) : metaMap.get(operandA)), 
						(isVariableDigit(operandB) ? new NumberNode(Integer.valueOf(operandB)) : metaMap.get(operandB)));
				
				System.out.println( result + " (decimal representation) ");
				result.printList();
				
				metaMap.put(variable, result);
			} else if (expression.contains("~")) {
				System.out.println(" output for : " + command);
				
				String operandA = expression.split("\\~")[0];
				
				NumberNode result = ComplexNumberNode.squareRoot(
						(isVariableDigit(operandA) ? new NumberNode(Integer.valueOf(operandA)) : metaMap.get(operandA)));
				
				System.out.println( result + " (decimal representation) ");
				result.printList();
				
				metaMap.put(variable, result);
			} else if (expression.contains("!")) {
				System.out.println(" output for : " + command);
				
				String operandA = expression.split("\\!")[0];
				
				NumberNode result = ComplexNumberNode.factorial(
						(isVariableDigit(operandA) ? new NumberNode(Integer.valueOf(operandA)) : metaMap.get(operandA)));
				
				System.out.println( result + " (decimal representation) ");
				result.printList();
				
				metaMap.put(variable, result);
			}		
			
			System.out.println();
		}
	}
	
	private static boolean isVariableDigit(String operand) {
		boolean isDigit = true;
		
		for (int index = 0; index < operand.length(); index++)
			isDigit = isDigit && Character.isDigit(operand.charAt(index));
		
		return isDigit;
	}

}
