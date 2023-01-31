import java.io.IOException;
import java.util.*;
public class Ylisse {
	public static boolean debug; //Public boolean that I can check from any class to run debug-specific code
	public static boolean unix; //Public boolean if the user is on a unix OS or windows for path formatting
	public static boolean CR;
	public static boolean CS;
	
	public static void main(String[] args) throws IOException {
		debug = true;
		CS = false;
		CR = false;
		unix = !System.getProperty("os.name").contains("Windows"); //Linux and Mac both use the same formatting so the edge case is then Windows devices
		FileSys f = FileSys.getInstance();
		Data.ensureInstance();
		f.setupDir();
		//CoreData.ensureInitialization();
		if(debug) {
			//CoreData.runShufflePromotes();
			for(Class c : Data.classes) {
				System.out.println(c.name);
				System.out.println(c.pointers == null ? "Promoted" : c.pointers[0] + " " );
				System.out.println();
			}
			return;
		}
		Scanner in = new Scanner(System.in);
		boolean option;
		System.out.println("Respond to each question with either y for yes or n for no.");
		System.out.println("Would you like to randomize Stat Cap Modifiers?");
		option = getResponse(in);
		if(option) {
			CR = true;
			CoreData.runModifiers();
		}
		System.out.println("Would you like to randomize Character Growths?");
		option = getResponse(in);
		if(option) {
			CR = true;
			CoreData.runGrowths();
		}
		System.out.println("Would you like to randomize Class Skills?");
		option = getResponse(in);
		if(option) {
			CS = true;
			CoreData.runSkills();
		}
		System.out.println("Would you like to randomize Class Growths?");
		option = getResponse(in);
		if(option) {
			CS = true;
			CoreData.runClassGrowths();
		}
		
		//logging
		ArrayList<String> log = new ArrayList<String>();
		if(CR) {
			log.add(" ---Characters--- ");
			for(LogEntry l : Data.log) {
				log.add(l.toString());
			}
			if(CS) {
				log.add("\n ---Classes--- \n");
				for(Class c : Data.classes) {
					log.add(c.toLog());
				}
			}
		}
		else if(CS) {
			log.add(" ---Classes--- ");
			for(Class c : Data.classes) {
				log.add(c.toLog());
			}
		}
		CoreData.close();
		f.writeFile("/output/log.txt", log);
		System.out.println("Everything has been randomized. See log.txt in the output folder for all the randomized data.");
	}
	public static boolean getResponse(Scanner in) {
		String temp = in.next();
		while(!valid(temp)) {
			System.out.println("Please enter a valid option. (y or n)");
			temp = in.next();
		}
		return temp.equals("y") ? true : false;
	}
	
	public static boolean valid(String in) {
		in = in.toLowerCase().trim();
		return in.equals("y") || in.equals("n");
	}
	
	
	

}
