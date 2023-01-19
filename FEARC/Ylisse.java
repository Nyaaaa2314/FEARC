import java.io.IOException;
import java.util.*;

public class Ylisse {
	public static boolean debug; //Public boolean that I can check from any class to run debug-specific code
	public static boolean unix; //Public boolean if the user is on a unix OS or windows for path formatting
	
	public static void main(String[] args) throws IOException {
		debug = true;
		unix = !System.getProperty("os.name").contains("Windows"); //Linux and Mac both use the same formatting so the edge case is then Windows devices
		
		
		if(debug) {
			System.out.println();
			FileSys f = FileSys.getInstance();
			ArrayList<String> a = f.readFile("/rom/static.txt");
			//a.set(0, "omg it worked");
			//System.out.println(a.size() + "\n" + a.get(0));
			f.setupDir();
			//f.writeFile("/output/romfs/data/person/static.txt", a);
			
		}
		
		
		
		
		
	}
	
	
	

}
