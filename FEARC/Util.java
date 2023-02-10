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
	public static void shufflePromotes(ArrayList<String> all){
		//ArrayList<Class> classes = Data.classes;
		Stack<String> A = new Stack<String>();
		ArrayList<String> B = new ArrayList<String>();
		Stack<String> C = new Stack<String>();
		for(Class c : Data.classes) {
			c.pa = 0;
			for(String s : all) {
				if(c.promotions != null) {
					if(!c.promotions.contains(s)) {
						c.pa++;
					}
				}
			}
			
		}
		ArrayList<Class> cl = Data.classes;
		ArrayList<Class> copy = new ArrayList<Class>();
		for(Class c : cl) {
			copy.add(c);
		}
//		classes.sort((Class a, Class b) -> a.pa - b.pa);
//		for(Class c : classes) {
//			System.out.println(c.pa);
//		}
		Collections.shuffle(all);
		for(String s : all) {
			C.push(s);
		}
		int in = 0;
		for(Class c : copy) {
			if(c.promotions ==  null) {
				continue;
			}
			ArrayList<String> pr = new ArrayList<String>();
			//cl.get(in).promotions = new ArrayList<String>();
			int add = 0;
			while(add < 1) {
				if(!c.promotions.contains(C.peek())) {
					pr.add(C.pop());
					add++;
				}
				else {
					A.push(C.pop());
				}
			}
			Class temp = cl.get(in);
			temp.promotions = pr;
			cl.set(in, temp);
			in++;
		}
		while(!C.isEmpty()) {
			A.push(C.pop());
		}
		while(!A.isEmpty()) {
			String s = A.pop();
			if(A.contains(s)) {
				B.add(s);
			}
			else {
				C.push(s);
			}
		}
		while(!C.isEmpty()) {
			A.push(C.pop());
		}
		in = 0;
		for(Class c : copy) {
			if(c.promotions == null) {
				in++;
				continue;
			}
			String s = cl.get(in).promotions.get(0);
			Boolean add = false;
			while(!add) {
				if(A.isEmpty()) {
					A.addAll(B);
				}
				if(A.peek().equals(s) || c.promotions.contains(A.peek())) {
					C.push(A.pop());
				}
				else {
					cl.get(in).promotions.add(A.pop());
					in++;
					while(!C.isEmpty()) {
						A.push(C.pop());
					}
					add = true;
					if(B.contains(s)) {
						B.remove(s);
						A.push(s);
					}
				}
			}	
		}
		System.out.println(validSort(copy, cl));
		System.exit(0);
		
		//return all;
	}
	public static boolean validSort(ArrayList<Class> a, ArrayList<Class> b) throws IllegalStateException{
		int dupes = 0;
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i) == null && b.get(i) == null) {
				continue;
			}
			else if(a.get(i) != null && b.get(i) != null) {
				ArrayList<String> as = a.get(i).promotions;
				ArrayList<String> bs = b.get(i).promotions;
				if(bs.size() != 2) {
					System.out.println("Wrong # of promotions");
					return false;
				}
				if(bs.get(0).equals(bs.get(1))) {
					//dupes++;
					//return false;
					System.out.println("Duplicate entry in resulting pair ");
					return false;
				}
				if(as.contains(bs.get(0)) || as.contains(bs.get(1))) {
					dupes++;
					//System.out.println("Not distinct from original pair");
					//return false;
				}
			}
			else {
				System.out.println("Data at wrong indices");
				return false;
			}
		}
		if(dupes > 0) {
			System.out.println("Not distinct from original pair " + dupes);
			return false;
		}
		
		return true;
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
