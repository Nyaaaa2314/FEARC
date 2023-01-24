
public class Util<T> {
	public static <T>int indexOf(T[] arr, T input) {
		
		for(int i = 0; i < arr.length; i++) {
			if(input.equals(arr[i])) {
				return i;
			}
		}
		return -1;
		
	}
}
