import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;




public class FileSys {
	
	private static FileSys f;
	private BufferedReader buf;
	private String userdir;
	
	private FileSys() {
		userdir = System.getProperty("user.dir");
	}
	
	
	public static FileSys getInstance() {
		if(f == null) {
			f = new FileSys();
		}
		return f;
	}
	
	
	
	//Method to convert between windows and mac folder formatting
	public String ensureFormat(String s) {
		return Ylisse.unix ? s : s.replaceAll("/", "\\\\\\\\");
	}
	
	
	//Generic method to read any of the various text files into a list of Strings
	public ArrayList<String> readFile(String path) throws IOException {
		buf = new BufferedReader(new FileReader(userdir + path));
		ArrayList<String> ret = new ArrayList<String>();
		
		String line = buf.readLine();
	    while (line != null) {
	      ret.add(line);
	      line = buf.readLine();
	    }
		return ret;
	}
	
	public void writeFile(String path, ArrayList<String> content) throws IOException {
		File temp = new File(ensureFormat(userdir + path));
		if(!temp.exists()) {
			temp.createNewFile();
		}
		Files.write(temp.toPath(), content, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	public void setupDir() {
		new File(ensureFormat(userdir + "/output" )).mkdirs();
		new File(ensureFormat(userdir + "/output/romfs" )).mkdirs();
		new File(ensureFormat(userdir + "/output/romfs/data" )).mkdirs();
		new File(ensureFormat(userdir + "/output/romfs/data/person" )).mkdirs();
		new File(ensureFormat(userdir + "/output/romfs/data/dispos" )).mkdirs();
		new File(ensureFormat(userdir + "/output/romfs/scripts" )).mkdirs();
	}
}
