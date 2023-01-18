import java.io.*;
import java.util.ArrayList;




public class FileSys {
	
	private static FileSys f;
	private BufferedReader buf;
	
	
	private FileSys() {
		
	}
	
	
	public static FileSys getInstance() {
		if(f == null) {
			f = new FileSys();
		}
		return f;
	}
	
	
	
	
	
	
	
	//Generic method to read any of the various text files into a list of Strings
	public ArrayList<String> readFile(String path) throws IOException {
		buf = new BufferedReader(new FileReader(path));
		ArrayList<String> ret = new ArrayList<String>();
		
		String line = buf.readLine();
	    while (line != null) {
	      ret.add(line);
	      line = buf.readLine();
	    }
		return ret;
	}
	
	
	
}
