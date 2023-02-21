import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CJR {
	public static FileSys f;
	public static Scanner in;
	public static ArrayList<String> cf; //current file that exec is working with
	public static void runCJO() throws IOException {
		f = FileSys.getInstance();
		Data.ensureInstance();
		ArrayList<String> cmds = f.readFile("/data/records/commands.txt");
		Data.initCJR();
		for(String s : cmds) {
			if(s.startsWith("#")) {
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
			case "append":
				
				break;
			case "save":
				
				break;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
