package multi.dimensional.search.structure.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import multi.dimensional.search.structure.MDS;
import multi.dimensional.search.structure.util.Timer;

/**
 * Sample driver code for Project LP4. Modify as needed. Do not change
 * input/output formats.
 * 
 * @author rbk
 */
public class LP4VerboseDriver {
	
	static long[] description;
	static final int DLENGTH = 100000;
	static int count = 0;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		if (args.length > 0) {
			in = new Scanner(new File(args[0]));
		} else {
			in = new Scanner(System.in);
		}
		
		String s;
		double rv = 0;
		description = new long[DLENGTH];

		Timer timer = new Timer();
		MDS mds = new MDS();

		while (in.hasNext()) {
			s = in.next();
			if (s.charAt(0) == '#') {
				s = in.nextLine();
				continue;
			}
			if (s.equals("Insert")) {
				long id = in.nextLong();
				double price = in.nextDouble();
				long des = in.nextLong();
				int index = 0;
				while (des != 0) {
					description[index++] = des;
					des = in.nextInt();
				}
				description[index] = 0;
				rv += printAndReturn (s, mds.insert(id, price, description, index));
			} else if (s.equals("Find")) {
				long id = in.nextLong();
				rv += printAndReturn (s, mds.find(id));
			} else if (s.equals("Delete")) {
				long id = in.nextLong();
				rv += printAndReturn (s, mds.delete(id));
			} else if (s.equals("FindMinPrice")) {
				long des = in.nextLong();
				rv += printAndReturn (s, mds.findMinPrice(des));
			} else if (s.equals("FindMaxPrice")) {
				long des = in.nextLong();
				rv += printAndReturn (s, mds.findMaxPrice(des));
			} else if (s.equals("FindPriceRange")) {
				long des = in.nextLong();
				double lowPrice = in.nextDouble();
				double highPrice = in.nextDouble();
				rv += printAndReturn (s, mds.findPriceRange(des, lowPrice, highPrice));
			} else if (s.equals("PriceHike")) {
				long minid = in.nextLong();
				long maxid = in.nextLong();
				double rate = in.nextDouble();
				rv += printAndReturn (s, mds.priceHike(minid, maxid, rate));
			} else if (s.equals("Range")) {
				double lowPrice = in.nextDouble();
				double highPrice = in.nextDouble();
				rv += printAndReturn (s, mds.range(lowPrice, highPrice));
			} else if (s.equals("SameSame")) {
				rv += mds.samesame();
			} else if (s.equals("End")) {
				break;
			} else {
				System.out.println("Houston, we have a problem.\nUnexpected line in input: " + s);
				System.exit(0);
			}
		}
		in.close();

		System.out.println(roundOff(rv));
		System.out.println(timer.end());
	}
	
	static double roundOff (double value) {
		return Math.floor((value + 0.000001d) * 100) / 100;
	}
	
	static double printAndReturn (String s, double value) {
		System.out.println((++count) + ": " + s + ": " + value);
		return value;
	}
	
	static double printAndReturn (String s, long value) {
		System.out.println((++count) + ": " + s + ": " + value);
		return value;
	}
	
	static double printAndReturn (String s, int value) {
		System.out.println((++count) + ": " + s + ": " + value);
		return value;
	}
}