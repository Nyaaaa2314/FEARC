import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Data {
	private static Data d;
	public final static String[] CharacterNames = { "Chrom", "Lissa", "Frederick", "Virion", "Sully", "Vaike", "Stahl", "Miriel", "Kellam", "Sumia", "Lon'qu", "Ricken", "Maribelle", "Panne", "Gaius", "Cordelia", "Gregor", "Nowi", "Libra", "Tharja", "Olivia", "Cherche", "Henry", "Lucina", "Say'ri", "Basilio", "Flavia", "Donnel", "Anna", "Owain", "Inigo", "Brady", "Kjelle", "Cynthia", "Severa", "Gerome", "M!Morgan", "F!Morgan", "Yarne", "Laurent", "Noire", "Nah", "Tiki", "Gangrel", "Walhart", "Emmeryn", "Yen'fay", "Aversa", "Priam" };
	public static ArrayList<Unit> units;
	public final static String[] Skills = {"01", "02", "03", "04", "05", "06", "07", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57","58","5A","63","64","65","66"};
	public static ArrayList<Class> classes;
	public final static String[] skillNames = {"HP +5", "Strength +2", "Magic +2", "Skill +2", "Speed +2", "Defense +2", "Resistance +2", "Hit Rate +20", "Avoid +10", "Movement +1", "Locktouch", "Veteran", "Aptitude", "Discipline", "Armsthrift", "Dual Support+", "Dual Strike+", "Dual Guard+", "Rightful King", "Odd Rhythm", "Even Rhythm", "Quick Burn", "Slow Burn", "Lucky Seven", "Gamble", "Outdoor Fighter", "Indoor Fighter", "Tantivy", "Focus", "Zeal", "Wrath", "Prescience", "Patience", "Underdog", "Charm", "Solidarity", "Demoiselle", "Hex", "Anathema", "Healtouch", "Relief", "Renewal", "Deliverer", "Defender", "Acrobat", "Pass", "Swordfaire", "Lancefaire", "Axefaire", "Bowfaire", "Tomefaire", "Luck +4", "Special Dance", "Rally Strength", "Rally Magic", "Rally Skill", "Rally Speed", "Rally Luck", "Rally Defense", "Rally Resistance", "Rally Movement", "Rally Spectrum", "Swordbreaker", "Lancebreaker", "Axebreaker", "Bowbreaker", "Tomebreaker", "Wyrmsbane", "Beastbane", "Lethality", "Aether", "Astra", "Sol", "Luna", "Ignis", "Vengeance", "Vantage", "Pavise", "Aegis", "Counter", "Miracle", "Despoil", "Galeforce", "Lifetaker", "Conquest", "Shadowgift","All Stats +2","Iote's Shield","Resistance +10","Aggressor","Rally Heart","Bond"};
	public final static String[] sClasses = {"Villager", "Dancer", "Taguel", "Manakete", "Lodestar", "Dread Fighter", "Bride", "Conqueror"};
	public final static String[] fliers = {"Dark Flier", "Pegasus Knight", "Falcon Knight", "Wyvern Knight", "Wyvern Lord", "Griffon Rider"};
	public static ArrayList<ArrayList<String>> classSets;
	public static ArrayList<Unit> rUnits;
//	public final static char m;
//	public final static char f;
	private Data() throws IOException {
		units = new ArrayList<Unit>();
		units.add(new Unit("M!Robin", true));
		units.add(new Unit("F!Robin", true));
		for(String s : CharacterNames) {
			units.add(new Unit(s));
		}
		
		
		//Class parsing from classes.txt
		classes = new ArrayList<Class>();
		FileSys f = FileSys.getInstance();
		ArrayList<String> ClassData = f.readFile("/data/records/classes.txt");
		for(int i = 0; i < ClassData.size(); i++) {
			if(ClassData.get(i).trim().length() == 0) {
				continue;
			}
			Class temp = new Class(ClassData.get(i++));
			if(!ClassData.get(i).equals("base") && !ClassData.get(i).equals("promoted")) {
				temp.promotions = new ArrayList<String>();
				temp.promotions.add(ClassData.get(i++));
				temp.promotions.add(ClassData.get(i++));
			}
			temp.promoted = ClassData.get(i++).equals("promoted");
			temp.wa = ClassData.get(i++);
			temp.wb = ClassData.get(i++);
			temp.wc = ClassData.get(i++);
			temp.JID = ClassData.get(i++);
			temp.setGender(Integer.parseInt(ClassData.get(i++)));
			temp.slines.add(ClassData.get(i));
			if(ClassData.get(i + 1).length() == 5) {
				temp.slines.add(ClassData.get(++i));
			}
			if(temp.promoted) {
				ArrayList<String> GameData = f.readFile("/data/bins/GameData.txt");
				if(GameData == null) {
					throw new NullPointerException("Error encountered with loading GameData from CoreData into Data");
				}
				for(int j = 0; j < temp.slines.size(); j++) {
					temp.pointers[j] = Integer.parseInt(GameData.get(Integer.parseInt(temp.slines.get(j)) - 18).substring(6));
				}
				
			}
			classes.add(temp);
			
		}
	}
	
	public static void initCJR() {
		ArrayList<String> all = new ArrayList<String>();
		for(Class c : classes) {
			if(!c.promoted || Util.contains(sClasses, c.name)) {
				all.add(c.name);
			}
		}
		classSets = new ArrayList<ArrayList<String>>();
		Random rng = new Random();
		for(int i = 0; i < CharacterNames.length; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			if(i == 15) {
				temp.add(rng.nextInt(0,2) == 0 ? "Pegasus Knight" : "Wyvern Rider");
			}
			else {
				temp.add(all.get(rng.nextInt(0, all.size())));
			}
			
			temp.add(all.get(rng.nextInt(0, all.size())));
			temp.add(all.get(rng.nextInt(0, all.size())));
			classSets.add(temp);
		}
		
		ArrayList<Unit> temp = (ArrayList<Unit>) units.subList(2, units.size());
		Collections.shuffle(temp);
		ArrayList<Unit> temp2 = (ArrayList<Unit>) units.subList(0,2);
		units = new ArrayList<Unit>();
		units.addAll(temp2);
		units.addAll(temp);
		int i = 0;
		for(Unit u : units) {
			if(u.isRobin) {
				continue;
			}
			u.replacementChar = CharacterNames[i];
			u.classset = classSets.get(i++);
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
