import java.io.IOException;
import java.util.*;

public class Ylisse {
	public static boolean debug; //Public boolean that I can check from any class to run debug-specific code
	public static boolean mac;
	
	public static void main(String[] args) throws IOException {
		debug = true;
		mac = false;
		
		
		if(debug) {
			
			FileSys f = FileSys.getInstance();
			ArrayList<String> a = f.readFile("/rom/static.txt");
			a.set(0, "omg it worked");
			System.out.println(a.size() + "\n" + a.get(0));
			System.out.println(f.ensureFormat("/rom/static.txt"));
			
		}
		
		
		
		
		
	}
	
	
	

}
