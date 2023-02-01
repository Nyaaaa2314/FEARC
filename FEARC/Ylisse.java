import java.io.IOException;
import java.util.*;
public class Ylisse {
	public static boolean debug; //Public boolean that I can check from any class to run debug-specific code
	public static boolean unix; //Public boolean if the user is on a unix OS or windows for path formatting
	public static boolean CR;
	public static boolean CS;
	
	public static void main(String[] args) throws IOException {
		debug = false;
		CS = false;
		CR = false;
		unix = !System.getProperty("os.name").contains("Windows"); //Linux and Mac both use the same formatting so the edge case is then Windows devices
		FileSys f = FileSys.getInstance();
		Data.ensureInstance();
		f.setupDir();
		//CoreData.ensureInitialization();
		if(debug) {
			//CoreData.runShufflePromotes();
//			for(Class c : Data.classes) {
//				System.out.println(c.name);
//				System.out.println(c.pointers == null ? "Promoted" : c.pointers[0] + " " + (c.pointers.length > 1 ? c.pointers[1] : ""));
//				System.out.println();
//			}
			
			String a = "asdhkaj";
			char[] as = a.toCharArray();
			Arrays.sort(as);
			for(int i = 0; i < as.length; i++) {
				System.out.println(as[i]);
			}
			return;
		}
		Scanner in = new Scanner(System.in);
		/*TODO: New UI system
			Have each option represented by a letter, ie Class Skills could be a, Class growths could be b, then
			the user inputs a string in any order of all the options they want and the program then runs those
			specific selections, much better than asking indiviually, it's essentially like a non-GUI version
			of checkboxes
		*/
		System.out.println("Please type out a word consisting of all the options you'd like to randomize from the below list.");
		System.out.print("For example, if you only want options a and b, then type the characters 'a' and 'b' then \nhit the enter key afterwards. ");
		System.out.println("It is not case, duplicate, order, or space-sensitive. \nValid inputs could include 'ab', 'aB', ' a b ', 'ababbba', 'ba ba', etc .\n");
		System.out.println("-----------------------------------------");
		System.out.println("|\tOption a: Stat Cap Modifiers\t|");
		System.out.println("|\tOption b: Character Growths \t|");
		System.out.println("|\tOption c: Class Skills      \t|");
		System.out.println("|\tOption d: Class Growths     \t|");
		System.out.println("|\tOption e: Class Stat Caps   \t|");
		System.out.println("|\tOption f: Class Promotions  \t|");
		System.out.println("-----------------------------------------");
		System.out.println("Enter here: ");
		//System.out.println("\tOption a: Stat Cap Modifiers | ");
		
		
		String s = in.nextLine().toLowerCase().trim();
		if(debug) {
			s = "abcdef";
		}
		char[] choice = Util.manageInput(s);
//		for(int i = 0; i < choice.length; i++) {
//			System.out.println(choice[i]);
//		}
		
		for(char c : choice) {
			switch(c) {
				case 'a':
					CR = true;
					CoreData.runModifiers();
					break;
				case 'b':
					CR = true;
					CoreData.runClassGrowths();
					break;
				case 'c':
					CS = true;
					CoreData.runSkills();
					break;
				case 'd':
					CS = true;
					CoreData.runGrowths();
					break;
				case 'e':
					CS = true;
					CoreData.runClassCaps();
					break;
				case 'f':
					CS = true;
					CoreData.runShufflePromotes();
					break;
				default:
					System.out.println(c + " is an illegal character. Ignoring.");
			
			}
		}
//		boolean option;
//		System.out.println("Respond to each question with either y for yes or n for no.");
//		System.out.println("Would you like to randomize Stat Cap Modifiers?");
//		option = getResponse(in);
//		if(option) {
//			CR = true;
//			CoreData.runModifiers();
//		}
//		System.out.println("Would you like to randomize Character Growths?");
//		option = getResponse(in);
//		if(option) {
//			CR = true;
//			CoreData.runGrowths();
//		}
//		System.out.println("Would you like to randomize Class Skills?");
//		option = getResponse(in);
//		if(option) {
//			CS = true;
//			CoreData.runSkills();
//		}
//		System.out.println("Would you like to randomize Class Growths?");
//		option = getResponse(in);
//		if(option) {
//			CS = true;
//			CoreData.runClassGrowths();
//		}
		
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
