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
		for(String s : cmds) {
			exec(s);
		}
		
		
		
	}
	
	public static void exec(String s) throws IOException {
		in = new Scanner(s);
		String cmd = in.next();
		
		switch(cmd) {
			case "load":
				cf = f.readFile("/data/" + in.next());
				break;
			case "replace":
				
				break;
			case "breplace": //bulk replace
				
				break;
			case "save":
				
				break;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
