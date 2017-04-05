package me.hxkandwal.daily.challanges.assorted;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.StringTokenizer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * A simple program to convert json to xml.
 * 
 * @author Hxkandwal
 */
public class JSONtoXMLConverter extends AbstractCustomTestRunner {

	public static interface IConverter {
		public String convert (String json) throws IOException;
	}
	
	public static class ConverterImpl implements IConverter {
		
		// state variables
		private StringTokenizer tokenizer;					// used to access json tokens in a consistent fashion.
		private StringBuilder ans = new StringBuilder();	// final answer build-up.
		private String elementName;							// used to store the element-name that is being created during the marshaling process.
		private int arraysOpen = 0;							// used to store the count of the nested array opened during the marshaling process.
		private int objectsOpen = 0;						// used to store the count of the nested objects opened during the marshaling process.
		private int arrayInnerObjectsOpen = 0;				// used to store the count of the nested anonymous objects opened during the marshaling process.
		
		@Override
		public String convert(String json) throws IOException {
			readJson(json);
			parseData ();
			return writeXml (ans);
		}
		
		/**
		 * core marshaler. This breaks down/integrates the tokens intelligently to construct the xml document. 
		 */
		private void parseData () {
			if (!isValid()) return;
			
			String token = fetchToken();
	
			// detected start of object
			if (token.equals("{")) { 
				ans.append("<object" + makeName() + ">"); 				// manage variable states
				arrayInnerObjectsOpen += (arraysOpen > 0) ? 1 : 0;
				objectsOpen ++;
			} 
			// detected end of object
			else if (token.equals("}")) { 
				ans.append("</object>"); 
				arrayInnerObjectsOpen -= (arraysOpen > 0) ? 1 : 0;		// manage variable states
				objectsOpen --;
			}
			// detected start of array
			else if (token.equals("[")) { ans.append("<array" + makeName() + ">"); arraysOpen ++; }
			// detected end of array
			else if (token.equals("]")) { ans.append("</array>"); arraysOpen --; }
			// detected null type
			else if (token.equals("null"))
				ans.append("<null" + makeName() + "/>");
			// detected boolean type
			else if (token.equals("true") || token.equals("false")) 
				ans.append("<boolean" + makeName() + ">").append(token).append("</boolean>");
			// detected number type
			else if (token.matches("[-+.0-9]+"))
				ans.append("<number" + makeName() + ">").append(token).append("</number>");
			// detected string type
			else if (token.equals("\"")) {
				// construct complete string token
				StringBuilder completeToken = new StringBuilder();
				String nextToken = null;
				while (!(nextToken = fetchToken()).equals("\"")) completeToken.append(" " + nextToken);
				token = completeToken.toString().trim();
				
				if (!isArrayData() && elementName == null) elementName = token;
				else ans.append("<string" + makeName() + ">").append(token).append("</string>");
			}
			
			parseData();
		}
		
		private boolean isValid () { 
			return tokenizer.hasMoreElements(); 
		}
		
		private String fetchToken () {
			return (isValid()) ? tokenizer.nextToken() : null;
		}
		
		private String makeName () {
			StringBuilder name = new StringBuilder();
			if (isArrayData() || elementName == null) return name.toString();
			name.append(" name = \"").append(elementName).append("\" ");
			elementName = null;
			return name.toString();
		}
		
		private boolean isArrayData () {
			return (arraysOpen > 0 && arrayInnerObjectsOpen == 0) || objectsOpen == 0;
		}
		
		/**
		 * read and clean the data.
		 */
		private void readJson (String rowcontent) throws IOException {
			rowcontent = rowcontent.replaceAll(",", " ")
								   .replaceAll(":", " : ")
								   .replaceAll("\"", " \" ")
								   .replaceAll("\\{", " { ")
								   .replaceAll("\\}", " } ")
								   .replaceAll("\\[", " [ ")
								   .replaceAll("\\]", " ] ").trim();
			tokenizer = new StringTokenizer(rowcontent, " ");
		}
		
		/**
		 * write the answer.
		 * @throws  
		 */
		private String writeXml (StringBuilder ans) throws IOException {
			try {
				Source xmlInput = new StreamSource(new StringReader(ans.toString()));
		        StringWriter stringWriter = new StringWriter();
		        StreamResult xmlOutput = new StreamResult(stringWriter);
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        transformerFactory.setAttribute("indent-number", 2);
		        Transformer transformer = transformerFactory.newTransformer(); 
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        transformer.transform(xmlInput, xmlOutput);
		        return xmlOutput.getWriter().toString();
			} catch (TransformerException exception) {
				exception.printStackTrace();
			}
			return "";
		}

	}
	
	// driver method
	public static void main(String[] args) throws Exception {
		System.out.println(new ConverterImpl().convert("-5"));
		System.out.println(new ConverterImpl().convert("{ \"a\" : 5}"));
		System.out.println(new ConverterImpl().convert("{ \"a\" : [1,2,3]}"));
		System.out.println(new ConverterImpl().convert("null"));
		System.out.println(new ConverterImpl().convert("true"));
		System.out.println(new ConverterImpl().convert(
			"{ "
				+ "\"work place\" : {"
		        	+ "\"name\" : \"Himanshu\","
			        + "\"type\" : \"Person\","
			        + "\"age\" : 29," 
			    + "}, "
			    + "\"male\" : true,"
			    + "\"array_0\" : [\"red\", \"green\", \"blue\", \"black\"],"
			    + "\"array_1\" : [1, \"red\", [{ \"inner object\" : true}], { \"obj\" : false}]"
		   +"}"));
	}
	
}