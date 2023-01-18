import java.io.IOException;
import java.util.*;

public class Control {
	public static boolean debug; //Public boolean that I can check from any class to run debug-specific code
	
	
	public static void main(String[] args) throws IOException {
		debug = true;
		if(debug) {
			
			FileSys f = FileSys.getInstance();
			ArrayList<String> a = f.readFile("rom\\static.txt");
			System.out.println(a.size() + "\n" + a.get(0));
		}
		
		
		
		
		
	}
	
	
	

}
