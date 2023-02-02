import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Util<T> {
	public static <T>int indexOf(T[] arr, T input) {
		
		for(int i = 0; i < arr.length; i++) {
			if(input.equals(arr[i])) {
				return i;
			}
		}
		return -1;
		
	}
	
	public static int[] searchPointers(String name){
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
	
	
	
	public static <T>ArrayList<T> ensureShuffle(ArrayList<T> all){
		boolean shuffled = false;
		while(!shuffled) {
			Collections.shuffle(all);
			for(int i = 0; i < all.size(); i+=2) {
				if(all.get(i).equals(all.get(i + 1))) {
					continue;
				}
			}
			shuffled = true;
		}
		return all;
	}
}
