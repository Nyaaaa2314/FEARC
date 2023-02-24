import java.io.IOException;
import java.util.*;

public class CoreData {

	final static String[] LT = { "59", "89", "D2", "D1", "DE", "C6", "47", "21", "BA", "DB", "C5", "EC", "35", "BD", "9F", "9B",
			"2D", "7B", "B2", "09", "F7", "53", "99", "8F", "C4", "90", "FA", "34", "F8", "19", "94", "02", "ED",
			"56", "40", "6C", "F4", "88", "4F", "2B", "B4", "BB", "EB", "74", "B7", "0D", "C2", "A4", "EE", "93",
			"CF", "42", "F1", "17", "BF", "F0", "A5", "BC", "0F", "6E", "1B", "73", "8D", "A6", "3B", "50", "33",
			"E0", "AF", "9D", "DD", "FF", "FE", "AA", "CE", "12", "62", "E2", "FB", "C1", "23", "49", "D6", "CD",
			"04", "2F", "41", "15", "1A", "32", "03", "8A", "14", "58", "0A", "A3", "D0", "71", "7D", "D3", "A0",
			"52", "BE", "D7", "8B", "48", "37", "13", "A8", "44", "08", "3C", "E3", "63", "F6", "DF", "16", "7C",
			"46", "F3", "07", "CC", "79", "C3", "6B", "3F", "81", "00", "20", "28", "AE", "EF", "6D", "8E", "0E",
			"1D", "4B", "95", "A1", "B6", "D4", "C7", "3E", "E5", "D8", "5A", "43", "26", "7A", "E4", "4E", "9C",
			"30", "4C", "C8", "97", "FD", "54", "68", "C0", "FC", "36", "1C", "75", "01", "96", "E9", "1F", "45",
			"06", "70", "2C", "29", "67", "2E", "F5", "9E", "92", "60", "3D", "E8", "E7", "66", "2A", "91", "EA",
			"57", "A9", "1E", "5F", "27", "51", "C9", "65", "18", "AB", "83", "D5", "85", "61", "0C", "77", "7E",
			"F9", "7F", "5E", "DC", "84", "5C", "6A", "39", "4D", "87", "5B", "DA", "69", "E6", "5D", "11", "82",
			"10", "55", "D9", "CB", "8C", "72", "86", "6F", "64", "80", "CA", "A2", "05", "AC", "4A", "B1", "0B",
			"38", "E1", "AD", "31", "B3", "98", "78", "B8", "22", "76", "9A", "24", "A7", "25", "B5", "F2", "B9",
			"B0", "3A" };
	private static ArrayList<String> LOOKUP_TABLE;
	private static FileSys f;
	public static ArrayList<String> Static;
	public static ArrayList<String> GameData;
	public static void ensureInitialization() throws IOException {
		if(f == null) {
			f = FileSys.getInstance();
			Data.ensureInstance();
			Static = f.readFile("/data/bins/static.txt");
			GameData = f.readFile("/data/bins/GameData.txt");
			LOOKUP_TABLE = new ArrayList<String>(Arrays.asList(LT));
		}
	}
	
	public static ArrayList<String> getGameData(){
		return GameData;
		
	}
	
	
	public static void runGrowths() throws IOException {
		ensureInitialization();
		Random rnd = new Random();
		boolean zero = false; //TODO: actually check for zero growths
		
		int[][] GrowthValues = new int[52][8];
		// int[] Growths = new int[8];
		String[] HexG = new String[8];
		// int[] GRlog = new int[8];
		String[] GRLog = new String[8 * 52];
		String[][] Growths = new String[52][8];
		Random rng = new Random(); 
		for (int i = 0; i < 52; i++) {
			int ID = i;
			int GR = 0;
			String GRHex = "";
			int index = 0;
			
			for (int j = 0; j < 8; j++) {
				
				switch (j) {
				case 0:
					GR = 35 + rng.nextInt(16);
					break;
				case 3:
					GR = 30 + rng.nextInt(21);
					break;
				case 4:
					GR = 30 + rng.nextInt(21);
					break;
				case 5:
					GR = 30 + rng.nextInt(41);
					break;
				default:
					GR = 10 + rng.nextInt(41);
					break;
				}
				
				GrowthValues[i][j] = GR;
				GRHex = Integer.toHexString(GR).toUpperCase();
				if (GRHex.length() == 1) {
					GRHex = "0" + GRHex;
				}
				if(zero) {
					GRHex = "00";
				}
				index = LOOKUP_TABLE.indexOf(GRHex);
				
				
				//Cringe af equation to encipher growths
				Growths[i][j] = Integer.toHexString(((index + (99 * ((ID ^ 167) - (33 * j)) ^ 217)) & 0xFF)).toUpperCase();
				if(Growths[i][j].length() == 1) {
					Growths[i][j] = "0" + Growths[i][j];
				}
				//String deciphered = debugGrowths(ID, j, true, Integer.parseInt(Growths[i][j], 16));
				//System.out.println();

			}

		}
		
		for(int i = 0, k = 12; i < 52; i++) {
			if(i == 2) {
				continue;
			}
			//int lindex = i < 2 ? i : i - 1;
			Data.units.get(i < 2 ? i : i - 1).growths = GrowthValues[i];
			Static.set(k + (143 * i), "0x" + Growths[i][0] + Growths[i][1] + Growths[i][2] + Growths[i][3]);
			Static.set(k + (143 * i) + 1, "0x" + Growths[i][4] + Growths[i][5] + Growths[i][6] + Growths[i][7]);
		}
	}
	
	public static void runModifiers() throws IOException {
		ensureInitialization();
		Random rng = new Random();
		//int seed = rng.nextInt();
		FileSys f = FileSys.getInstance();
		ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < 52; i++) {
			ArrayList<String> m = new ArrayList<String>();
			for(int j = 0; j < 7; j++) {
				if(j == 0 || j == 1) {
					int r = -1 * rng.nextInt(4);
					switch(r) {
					case -3:
						m.add("FD");
						break;
					case -2:
						m.add("FE");
						break;
					case -1:
						m.add("FF");
						break;
					default:
						m.add("00");
						break;
					}
				}
				else {
					int r = rng.nextInt(4);
					switch(r) {
					case 3:
						m.add("03");
						break;
					case 2:
						m.add("02");
						break;
					case 1:
						m.add("01");
						break;
					default:
						m.add("00");
						break;
					}
				}
			}
			Collections.shuffle(m);
			a.add(m);
			if(i == 2) {
				continue;
			}
			//TODO: make this more efficient | reminder for later: move caps[j] up to the first switch
			int[] caps = new int[7];
			for(int j = 0; j < 7; j++) {
				switch(m.get(j)) {
				case "00":
					caps[j] = 0;
					break;
				case "01":
					caps[j] = 1;
					break;
				case "02":
					caps[j] = 2;
					break;
				case "03":
					caps[j] = 3;
					break;
				case "FD":
					caps[j] = -3;
					break;
				case "FE":
					caps[j] = -2;
					break;
				case "FF":
					caps[j] = -1;
					break;
				}
			}
			Data.units.get(i < 2 ? i : i - 1).caps = caps;
		}
		for(int i = 0, k = 16; i < 52; i++) {
			if(i == 2) {
				continue;
			}
			ArrayList<String> m = a.get(i);
			int index = k + 143 * i;
			Static.set(index, Static.get(index).substring(0, 4) + m.get(0) + m.get(1) + m.get(2));
			Static.set(index + 1, "0x" + m.get(3) + m.get(4) + m.get(5) + m.get(6));
		}
	}
	
	public static void runSkills() throws IOException {
		ensureInitialization();
		ArrayList<String> skillsR = new ArrayList<String>(Arrays.asList(Data.Skills));
		Collections.shuffle(skillsR);
		for(int i = 0, j = 0; i < Data.classes.size(); i++, j+=2) {
			//Data.classes.set
			Class temp = Data.classes.get(i);
			temp.skills = new ArrayList<String>();
			temp.skills.add(skillsR.get(j));
			temp.skills.add(skillsR.get(j + 1));
			Data.classes.set(i, temp);
		}
		for(Class c : Data.classes) {
			for(String line : c.slines) {
				GameData.set(Integer.parseInt(line), "0x" + c.skills.get(0) + "00" + c.skills.get(1) + "00");
			}
		}
		
	}
	
	public static void runClassGrowths() throws IOException{
		ensureInitialization();
		Random rnd = new Random(System.currentTimeMillis());
		boolean zero = false; //TODO: actually check for zero growths
		
		
		//growths are slines -8 and -7, different ids for male/female versions of classes
		//for enciphering
		
		
		//Class growths for luck are always 0
		for(int i = 0, ID = 0; i < Data.classes.size(); i++) {
			int[] growths = new int[8];
			Class c = Data.classes.get(i);
			
			if(c.promoted) {
				growths[0] = 35 + rnd.nextInt(16);
				growths[1] = rnd.nextInt(31);
				growths[2] = rnd.nextInt(21);		
				growths[3] = 5 + rnd.nextInt(21);
				growths[4] = 5 + rnd.nextInt(21);
				growths[5] = 0;
				growths[6] = 5 + rnd.nextInt(11);
				growths[7] = 5 + rnd.nextInt(11);
				
			}
			else {
				growths[0] = 35 + rnd.nextInt(16);
				growths[1] = 10 + rnd.nextInt(16);
				growths[2] = rnd.nextInt(16);		
				growths[3] = 15 + rnd.nextInt(16);
				growths[4] = 10 + rnd.nextInt(16);
				growths[5] = 0;
				growths[6] = 5 + rnd.nextInt(11);
				growths[7] = 5 + rnd.nextInt(6);
			}
			c.growths = growths;
			Data.classes.set(i, c);
			
			String[] hex = new String[8];
			String[] hex2 = new String[8];
			for(int j = 0; j < 8; j++) {
				String h = Integer.toHexString(growths[j]);
				if(h.length() == 1) {
					h = "0" + h;
				}
				hex[j] = h;
				if(zero) {
					hex[j] = "00";
				}
				hex2[j] = hex[j];
				
			}
			for(int j = 0; j < 8; j++) {
				if(c.name.equals("Villager")) {
					ID++;
				}
				int index = LOOKUP_TABLE.indexOf(hex[j].toUpperCase());
				hex[j] = Integer.toHexString(((index + (0x23 * ((ID ^ 0x46) - (0xF1 * j)) ^ 0x78)) & 0xFF)).toUpperCase();
				if(hex[j].length() == 1) {
					hex[j] = "0" + hex[j];
				}
				if(c.slines.size() == 2) {
					//index = LOOKUP_TABLE.indexOf(hex2[j]);
					hex2[j] = Integer.toHexString(((index + (0x23 * (((ID  + 1)^ 0x46) - (0xF1 * j)) ^ 0x78)) & 0xFF)).toUpperCase();
					if(hex2[j].length() == 1) {
						hex2[j] = "0" + hex2[j];
					}
				}
			}
			if(c.gender == 0) {
				GameData.set(Integer.parseInt(c.slines.get(0)) - 8, "0x" + hex[0] + hex[1] + hex[2] + hex[3]);
				GameData.set(Integer.parseInt(c.slines.get(0)) - 7, "0x" + hex[4] + hex[5] + hex[6] + hex[7]);
				GameData.set(Integer.parseInt(c.slines.get(1)) - 8, "0x" + hex2[0] + hex2[1] + hex2[2] + hex2[3]);
				GameData.set(Integer.parseInt(c.slines.get(1)) - 7, "0x" + hex2[4] + hex2[5] + hex2[6] + hex2[7]);
				ID+=2;
			}
			else {
				GameData.set(Integer.parseInt(c.slines.get(0)) - 8, "0x" + hex[0] + hex[1] + hex[2] + hex[3]);
				GameData.set(Integer.parseInt(c.slines.get(0)) - 7, "0x" + hex[4] + hex[5] + hex[6] + hex[7]);
				ID++;
			}
			
		}	
		
	}
	
	public static void runClassCaps() {
		for(int i = 0; i < Data.classes.size(); i++) {
			Class c = Data.classes.get(i);
			c.caps = new int[8];
			Random rng = new Random();
			//TODO: bounded promoted caps
			if(c.promoted) {
				c.caps[0] = 80;
				c.caps[1] = 30 + rng.nextInt(21);
				c.caps[2] = 25 + rng.nextInt(22);
				c.caps[3] = 34 + rng.nextInt(15);
				c.caps[4] = 35 + rng.nextInt(12);
				c.caps[5] = 45;
				c.caps[6] = 30 + rng.nextInt(21);
				c.caps[7] = 30 + rng.nextInt(16);
			}
			else {
				c.caps[0] = 60;
				c.caps[1] = 20 + rng.nextInt(11);
				c.caps[2] = 20 + rng.nextInt(9);
				c.caps[3] = 23 + rng.nextInt(8);
				c.caps[4] = 23 + rng.nextInt(6);
				c.caps[5] = 30;
				c.caps[6] = 20 + rng.nextInt(11);
				c.caps[7] = 20 + rng.nextInt(9);
			}
			String[] chex = new String[8];
			for(int j = 0; j < c.caps.length; j++) {
				chex[j] = Integer.toHexString(c.caps[j]);
				if(chex[j].length() == 1) {
					chex[j] = "0" + chex[j];
				}
			}
			GameData.set(Integer.parseInt(c.slines.get(0)) - 4, "0x" + chex[0] + chex[1] + chex[2] + chex[3]);
			GameData.set(Integer.parseInt(c.slines.get(0)) - 3, "0x" + chex[4] + chex[5] + chex[6] + chex[7]);
			if(c.slines.size() > 1) {
				GameData.set(Integer.parseInt(c.slines.get(1)) - 4, "0x" + chex[0] + chex[1] + chex[2] + chex[3]);
				GameData.set(Integer.parseInt(c.slines.get(1)) - 3, "0x" + chex[4] + chex[5] + chex[6] + chex[7]);
			}
			
		}
	}
	
	
	public static void runPairUps() {
		for(Class c : Data.classes) {
			Random rng = new Random();
			int[] indices = new int[4];
			c.pairups = new int[8];
			indices[0] = rng.nextInt(1,8);
			indices[1] = rng.nextInt(1,8);
			indices[2] = rng.nextInt(1,8);
			indices[3] = rng.nextInt(0,100) < 5 ? 0 : rng.nextInt(1,8);
			for(int i : indices) {
				if(i == 0) {
					c.pairups[i] = 1;
				}
				else {
					c.pairups[i] = rng.nextInt(1, c.promoted ? 6 : 4);
				}
			}
		}
		for(Class c : Data.classes) {
			GameData.set(Integer.parseInt(c.slines.get(0)) - 2, "0x" + "0" + c.pairups[0] + "0"  + c.pairups[1]+ "0" + c.pairups[2]+ "0" + c.pairups[3]);
			GameData.set(Integer.parseInt(c.slines.get(0)) - 1, "0x" + "0"+  c.pairups[4]+ "0" + c.pairups[5]+ "0" + c.pairups[6]+ "0" + c.pairups[7]);
			if(c.slines.size() > 1) {
				GameData.set(Integer.parseInt(c.slines.get(1)) - 2, "0x"+  "0" + c.pairups[0]+ "0" + c.pairups[1]+ "0" + c.pairups[2]+ "0" + c.pairups[3]);
				GameData.set(Integer.parseInt(c.slines.get(1)) - 1, "0x" + "0" + c.pairups[4]+ "0" + c.pairups[5]+ "0" + c.pairups[6]+ "0" + c.pairups[7]);
			}
		}
		
		
	}
	
	
	public static void runShufflePromotes() {
		
		ArrayList<String> all = new ArrayList<String>();
		
		
		for(Class c : Data.classes) {
			if(c.promotions != null) {
				all.addAll(c.promotions);
			}
		}
		//all = Util.ensureShuffle(all);
		//all = Util.shufflePromotes(all);
		Util.shufflePromotes(all);
		int index = 0;
		for(Class c : Data.classes) {
			if(c.promotions != null) {
				c.sp = true;
				c.promotions = new ArrayList<String>();
				c.promotions.add(all.get(index++));
				c.promotions.add(all.get(index++));
				
				//int[] ptr1 = Util.searchPointers(c.promotions.get(0));
				//int[] ptr2 = Util.searchPointers(c.promotions.get(1));
				int[][] pointers = {Util.searchPointers(c.promotions.get(0)), Util.searchPointers(c.promotions.get(1))};
				for(int i = 0; i < pointers.length; i++) {
					if(pointers[i].length == 1) {
						GameData.set(Integer.parseInt(c.slines.get(0)) + i + 7, "POINTER1: " + pointers[i][0]);
						if(c.slines.size() > 1) {
							GameData.set(Integer.parseInt(c.slines.get(1))+ (i) + 7, "POINTER1: " + pointers[i][0]);
						}
					}
					else {
						GameData.set(Integer.parseInt(c.slines.get(0))+ (i) + 7, "POINTER1: " + pointers[i][0]);
						if(c.slines.size() > 1) {
							GameData.set(Integer.parseInt(c.slines.get(1))+ (i) + 7, "POINTER1: " + pointers[i][1]);
						}
						
						
					}
				}
			}
		}
		
		
		
	}
	
	
	//debug method for double-checking growth conversions
	public static String debugGrowths(int ID, int N, boolean Char, int enc) {
		int index;
		if(Char) {
			index = (enc - (0x63 * ((ID ^ 0xA7) - 0x21 * N) ^ 0xD9)) & 0xFF;
		}
		else {
			index = (enc - (0x23 * ((ID ^ 0x46) - 0xF1 * N) ^ 0x78)) & 0xFF;
		}
		return LOOKUP_TABLE.get(index);
	}

	
	
	
	public static void close() throws IOException {
		f.writeFile("/output/romfs/data/person/static.txt", Static);
		f.writeFile("/output/romfs/data/GameData.txt", GameData);
	}
	
	
	
	
	
	
	
	
	
}
