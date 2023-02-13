import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

public class Util<T> {
	public static <T>int indexOf(T[] arr, T input) {
		
		for(int i = 0; i < arr.length; i++) {
			if(input.equals(arr[i])) {
				return i;
			}
		}
		return -1;
		
	}
	
	public static int[] searchPointers(String name) {
		for(Class c : Data.classes) {
			if(c.name.equals(name)) {
				return c.pointers;
			}
		}
		return null;
	}
	public static int indexOfChar(String name) {
		int i = 0;
		for(Class c : Data.classes) {
			if(c.name.equals(name)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	
	
	public static <T>boolean contains(T[] arr, T input){
		for(T t : arr) {
			if(t.equals(input)) {
				return true;
			}
		}
		return false;
	}
	public static char[] manageInput(String s) {
		StringBuilder str = new StringBuilder(s);
		for(int i = 0; i < s.length(); i++) {
			String c = "" + str.charAt(0);
			str.deleteCharAt(0);
			if(str.indexOf(c) == -1) {
				str.append(c);
			}
		}
		char[] ret = str.toString().toCharArray();
		Arrays.sort(ret);
		
		return ret;
	}
	
	public static void shufflePromotes(ArrayList<String> all) {
		
		//Stack<String> A = new Stack<String>();
		//double start = System.currentTimeMillis();
		ArrayList<ArrayList<String>> shuffle = new ArrayList<ArrayList<String>>();
		//int runs = 0;
		while(!validShuffle(shuffle)) {
			Collections.shuffle(all);
			shuffle = new ArrayList<ArrayList<String>>();
			for(int i = 0; i < all.size(); i++) {
				ArrayList<String> temp = new ArrayList<String>(2);
				temp.add(all.get(i++));
				temp.add(all.get(i));
				shuffle.add(temp);
			}
			//runs++;
		}
		int i = 0;
		for(Class c : Data.classes) {
			if(c.promotions == null) {
				continue;
			}
			else {
				c.sp = true;
				c.promotions = shuffle.get(i++);
			}
		}
		//double end = System.currentTimeMillis();
		//System.out.println("Took " + (end - start) / 1000 + " seconds and took " + runs + " permutations");
		//System.exit(0);
	}
	
	public static boolean validShuffle(ArrayList<ArrayList<String>> shuffle) {
		if(shuffle.size() == 0) {
			return false;
		}
		int i = 0;
		for(Class c : Data.classes) {
			if(c.promotions == null) {
				continue;
			}
			ArrayList<String> ch = c.promotions;
			ArrayList<String> sh = shuffle.get(i++);
			if(sh.get(0).equals(sh.get(1))) {
				return false;
			}
			if(ch.contains(sh.get(0)) || ch.contains(sh.get(1))) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	public static ArrayList<Unit> shuffleGens(ArrayList<Unit> shuffle){
		Stack<Unit> gen1 = new Stack<Unit>();
		Stack<Unit> gen2 = new Stack<Unit>();
		for(Unit u : Data.units) {
			if(u.isRobin) {
				continue;
			}
			if(!contains(Data.gen2, u.name)) {
				gen1.push(u);
			}
			else {
				gen2.push(u);
			}
		}
		Collections.shuffle(gen2);
		Collections.shuffle(gen1);
		shuffle = new ArrayList<Unit>();
		
		for(Unit u : Data.units) {
			if(u.isRobin) {
				continue;
			}
			if(!contains(Data.gen2, u.name)) {
				shuffle.add(gen1.pop());
			}
			else {
				shuffle.add(gen2.pop());
			}
		}
		
		
		return shuffle;
	}
	
	
	public static <T>ArrayList<T> ensureShuffle(ArrayList<T> all){
		boolean shuffled = false;
		while(!shuffled) {
			shuffled = true;
			Collections.shuffle(all);
			for(int i = 0; i < all.size(); i+=2) {
				if(all.get(i).equals(all.get(i + 1))) {
					shuffled = false;
					break;
				}
			}
		}
		return all;
	}
}
