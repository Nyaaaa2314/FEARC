import java.io.IOException;
import java.util.*;
public class Ylisse {
	public static boolean debug; //Public boolean that I can check from any class to run debug-specific code
	public static boolean unix; //Public boolean if the user is on a unix OS or windows for path formatting
	
	public static void main(String[] args) throws IOException {
		debug = true;
		unix = !System.getProperty("os.name").contains("Windows"); //Linux and Mac both use the same formatting so the edge case is then Windows devices
		FileSys f = FileSys.getInstance();
		Data.ensureInstance();
		f.setupDir();
		if(debug) {
			CoreData.runSkills();
			for(Class c : Data.classes) {
				System.out.println(c.toString());
			}
			CoreData.close();
			
//			CoreData.runGrowths();
//			CoreData.runModifiers();
//			CoreData.close();
//			
//			ArrayList<String> logs = new ArrayList<String>();
//			for(LogEntry l : Data.log) {
//				logs.add(l.toString());
//			}
//			f.writeFile("/output/log.txt", logs);
		}
		
		
		
		
		
	}
	
	
	

}
