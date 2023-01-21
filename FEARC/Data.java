import java.io.IOException;
import java.util.ArrayList;

public class Data {
	private static Data d;
	public final static String[] CharacterNames = { "Chrom", "Lissa", "Frederick", "Virion", "Sully", "Vaike", "Stahl", "Miriel", "Kellam", "Sumia", "Lon'qu", "Ricken", "Maribelle", "Panne", "Gaius", "Cordelia", "Gregor", "Nowi", "Libra", "Tharja", "Olivia", "Cherche", "Henry", "Lucina", "Say'ri", "Basilio", "Flavia", "Donnel", "Anna", "Owain", "Inigo", "Brady", "Kjelle", "Cynthia", "Severa", "Gerome", "M!Morgan", "F!Morgan", "Yarne", "Laurent", "Noire", "Nah", "Tiki", "Gangrel", "Walhart", "Emmeryn", "Yen'fay", "Aversa", "Priam" };
	public static ArrayList<LogEntry> log;
	public final static String[] Skills = {"01", "02", "03", "04", "05", "06", "07", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57"};
	public static ArrayList<Class> classes;
	
	
	private Data() throws IOException {
		log = new ArrayList<LogEntry>();
		log.add(new LogEntry("M!Robin"));
		log.add(new LogEntry("F!Robin"));
		for(String s : CharacterNames) {
			log.add(new LogEntry(s));
		}
		classes = new ArrayList<Class>();
		FileSys f = FileSys.getInstance();
		ArrayList<String> ClassData = f.readFile("/data/records/classes.txt");
		for(int i = 0; i < ClassData.size(); i++) {
			if(ClassData.get(i).trim().length() == 0) {
				continue;
			}
			Class temp = new Class(ClassData.get(i++));
			temp.promoted = ClassData.get(i++).equals("promoted");
			temp.wa = ClassData.get(i++);
			temp.wb = ClassData.get(i++);
			temp.wc = ClassData.get(i++);
			temp.JID = ClassData.get(i++);
			temp.slines.add(ClassData.get(i));
			if(ClassData.get(i + 1).length() == 5) {
				temp.slines.add(ClassData.get(++i));
			}
			classes.add(temp);
			
		}
		
		
		
	}
	public static void ensureInstance() throws IOException {
		if(d == null) {
			d = new Data();
		}
	}
	public static Data getInstance() throws IOException {
		ensureInstance();
		return d;
	}
	
	
}
