import java.util.ArrayList;

public class Data {
	private static Data d;
	public final static String[] CharacterNames = { "Chrom", "Lissa", "Frederick", "Virion", "Sully", "Vaike", "Stahl", "Miriel", "Kellam", "Sumia", "Lon'qu", "Ricken", "Maribelle", "Panne", "Gaius", "Cordelia", "Gregor", "Nowi", "Libra", "Tharja", "Olivia", "Cherche", "Henry", "Lucina", "Say'ri", "Basilio", "Flavia", "Donnel", "Anna", "Owain", "Inigo", "Brady", "Kjelle", "Cynthia", "Severa", "Gerome", "M!Morgan", "F!Morgan", "Yarne", "Laurent", "Noire", "Nah", "Tiki", "Gangrel", "Walhart", "Emmeryn", "Yen'fay", "Aversa", "Priam" };
	public static ArrayList<LogEntry> log;
		
	private Data() {
		log = new ArrayList<LogEntry>();
		log.add(new LogEntry("M!Robin"));
		log.add(new LogEntry("F!Robin"));
		for(String s : CharacterNames) {
			log.add(new LogEntry(s));
		}
	}
	public static void ensureInstance() {
		if(d == null) {
			d = new Data();
		}
	}
	public static Data getInstance() {
		ensureInstance();
		return d;
	}
	
	
}
