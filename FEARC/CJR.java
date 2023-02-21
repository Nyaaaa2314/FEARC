import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CJR {
	public static FileSys f;
	public static Scanner in;
	public static ArrayList<String> cf; //current file that exec is working with
	//public static String cf
	public static void runCJO() throws IOException {
		f = FileSys.getInstance();
		Data.ensureInstance();
		ArrayList<String> cmds = f.readFile("/data/records/commands.txt");
		Data.initCJR();
		for(String s : cmds) {
			if(s.startsWith("#") || s.trim().length() == 0) {
				continue;
			}
			exec(s);
		}
		
		
		
	}
	
	public static void exec(String s) throws IOException {
		in = new Scanner(s);
		String cmd = in.next();
		String a;
		String b;
		int c;
		switch(cmd) {
			case "load":
				cf = f.readFile("/data/" + in.next());
				break;
			case "replace":
				a = in.next();
				b = in.next();
				c = in.nextInt();
				in.nextLine();
				
				String d = cf.get(c - 1);
				d.replace(a, b);
				cf.set(c - 1, d);
				break;
			case "breplace": //bulk replace
				a = in.next();
				b = in.next();
				c = 0;
				for(String line : cf) {
					cf.set(c++, line.replace(a,b));
				}
				break;
			case "creplace": //character replace
				
				break;
			case "lreplace":
				c = in.nextInt();
				a = parseDataAccess(in.nextLine());
				cf.set(--c, a);
				break;
			case "append":
				
				break;
			case "save":
				f.writeFile("/output" + in.nextLine().trim(), cf);
				break;
		}
		
		
	}
	
	public static String parseDataAccess(String in) throws IllegalStateException{
		Scanner access;
		if(in.contains("CHARACTER_ACCESS")) {
			access = new Scanner(in);
			access.next(); //throw out the access token
			String name = access.next(); //grab the name of the og character
			String field = access.next(); //what are we accessing
			Unit temp = Util.search(name);
			access.close();
			if(temp == null) {
				throw new IllegalStateException("Name of character is invalid in command: " + in);
			}
			switch(field) {
				case "CID":
					return temp.CID;
			
			
			
			}
		}
		if(in.contains("WEAPON_SWAP")) {
			access = new Scanner(in);
			access.next(); //throw out the access token
			//String type = access.next(); //grab new weapon type
			int tier = access.nextInt();
			int btier = access.nextInt();
			String name = access.next();
			boolean promoted = access.next().trim().equals("T");
			int W = access.nextInt();
			access.close();
			Unit temp = Util.search(name);
			if(promoted) {
				//TODO: add reverse search for promoted class
			}
			else {
				Class c = Util.cSearch(temp.classset.get(0));
				String w = W == 1 ? c.wa : (W == 2 ? (c.wb.equals("none") ? c.wa : c.wb) : (c.wc.equals("none") ? (c.wb.equals("none") ? c.wa : c.wb) : c.wc));
				in = Data.weaponSwap(w, tier, btier, c.dark);
			}
		}
		//class access
		
		
		
		
		
		return in;
	}
	
	
	
	
	
	
	
	
	
}
