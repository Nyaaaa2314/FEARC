import java.util.ArrayList;
import java.util.Collections;

public class Util<T> {
	public static <T>int indexOf(T[] arr, T input) {
		
		for(int i = 0; i < arr.length; i++) {
			if(input.equals(arr[i])) {
				return i;
			}
		}
		return -1;
		
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
