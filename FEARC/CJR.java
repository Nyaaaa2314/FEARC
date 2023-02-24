import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
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
		CJRStatic();
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
				b = parseSpecialCommand(in.next());
				c = in.nextInt();
				in.nextLine();
				
				String d = cf.get(c - 1);
				d.replace(a, b);
				cf.set(c - 1, d);
				break;
			case "breplace": //bulk replace
				a = in.next();
				b = parseSpecialCommand(in.next());
				c = 0;
				for(String line : cf) {
					cf.set(c++, line.replace(a,b));
				}
				break;
			case "creplace": //character replace
				
				break;
			case "lreplace":
				c = in.nextInt();
				a = parseSpecialCommand(in.nextLine());
				cf.set(--c, a);
				break;
			case "append":
				
				break;
			case "special":
				String sp = in.next();
				if(sp.trim().equals("FALCHION_SWAP")) {
					int type = in.nextInt();
					String name = in.next();
					Unit temp = Util.search(name);
					Class cl = Util.cSearch(temp.classset.get(0));
					ArrayList<String> g = CoreData.GameData;
					g.set(69311, "0x" + Util.wepToHex(cl.wa) + "000000");
					switch(Util.wepToHex(cl.wa)) {
						case "03":
							g.set(69313, "0x05500002");
							//no break here on purpose
						case "04":
							g.set(69314, "0x02000000");
							break;
						case "05":
							g.set(69305, "0x00340040");
							g.set(69318, "リライブ");
							g.set(69311, "0x" + Util.wepToHex(cl.wa) + "000101");
						case "06":
							g.set(69305, "0x00740040");
							g.set(69314, "0x02000000");
							g.set(69315, "0x00080503");
							g.set(69316, "0x02000A07");
							g.set(69312, "0x00010000");
							break;
						case "07":
							g.set(69305, "0x00740040");
							g.set(69312, "0x00010000");
							g.set(69316, "0x05040100");
							g.set(69315, "0x00030005");
							break;
					}
					CoreData.GameData = g;
				}
				break;
			case "save":
				f.writeFile("/output" + in.nextLine().trim(), cf);
				break;
		}
		
		
	}
	
	public static String parseSpecialCommand(String in) throws IllegalStateException{
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
			Class c = Util.cSearch(temp.classset.get(0));
			if(promoted && !Util.contains(Data.sClasses, c.name)) {
				c = Util.cSearch(c.promotions.get(0));
			}
			String w = W == 1 ? c.wa : (W == 2 ? (c.wb.equals("none") ? c.wa : c.wb) : (c.wc.equals("none") ? (c.wb.equals("none") ? c.wa : c.wb) : c.wc));
			
			in = Data.weaponSwap(w, tier, btier, c.dark);
		}
		//class access
		
		
		
		
		
		return in;
	}
	
/*	for every character
 *
 *	if u consider the first growth line to be 0 then
 *
 *	+9,10,11 are skills
 *
 *	+18,19,20,21,22,23 are reclasses
 *
 *	+6,7 are weapon exp, starting with the last 2 bytes on +6
 *
 *	0x 00 00 00 00
 *	01 23 45 67 89 10
 *
 */
	public static void CJRStatic() {
		int i = 3;
		int acc = 0;
		//16 + 143 * i
		ArrayList<String> Static = CoreData.Static;
		ArrayList<String> StaticS = (ArrayList<String>) Static.clone();
		//System.out.println(Data.units.size());
		for(Unit u : Data.rUnits) {
			int numSkills =  1;
			int k = 12 + 143 * i;
			int n = 12 + 143 * (Util.indexOf(Data.CharacterNames, u.name) + 3);
			if(i == 3) {
				StaticS.set(k - 9, "0x00000000");
				StaticS.set(n - 9, u.m ? "0x22000000" : "0x23000000");
			}
//			System.out.println(u.name);
//			System.out.println(u.replacementChar);
//			System.out.println("------------------");
			Class c = Util.cSearch(u.classset.get(0));
			Class pc = null;
			if(!Static.get(k + 9).substring(2, 4).equals("00")) {
				if(!Static.get(k + 9).substring(6, 8).equals("00")) {
					StaticS.set(n + 9, "0x" + c.skills.get(0) + "00" + c.skills.get(1) + "00");
					numSkills += 1;
					if(Util.contains(Data.sClasses, c.name)) {
						StaticS.set(n + 10, "0x00000000");
						StaticS.set(n + 11, "0x00000000");
					}
					else {
						if(!Static.get(k + 10).substring(2, 4).equals("00")) {
							pc = Util.cSearch(c.promotions.get(0));
							if(!Static.get(k + 10).substring(6, 8).equals("00")) {
								StaticS.set(n + 10, "0x" + pc.skills.get(0) + "00" + pc.skills.get(1) + "00");
								numSkills += 2;
							}
							else {
								StaticS.set(n + 10, "0x" + pc.skills.get(0) + "000000");
								numSkills += 1;
							}
						}
					}
				}
				else {
					StaticS.set(n + 9, "0x" + c.skills.get(0) + "000000");
					StaticS.set(n + 10, "0x00000000");
				}
			}
			StaticS.set(n + 11, "0x00000000");
			if(u.replacementChar.equals("Frederick") && c.promotions != null) {
				pc = Util.cSearch(c.promotions.get(0));
			}
			//StaticS.set(, null)
			StaticS.set(n - 5, pc != null ? pc.genJID(u.m) : c.genJID(u.m));
			
			String wep1 = Static.get(k + 6).substring(6,10);
			String wep2 = Static.get(k + 7).substring(2,10);
			String[] wep = (wep1 + wep2).split("(?<=\\G.{" + 2 + "})");
			Arrays.sort(wep, (String a, String b) -> Integer.parseInt(b, 16) - Integer.parseInt(a, 16));
			
			String[] xp = new String[wep.length];
			int w = 0;
			Class d = pc != null ? pc : c;
			for(String s : wep) {
				String we = w == 0 ? d.wa : (w ==  1 ? d.wb : (w == 2 ? d.wc : ""));
				switch(we) {
					case "Sword":
						xp[0] = s;
						break;
					case "Lance":
						xp[1] = s;
						break;
					case "Axe":
						xp[2] = s;
						break;
					case "Bow":
						xp[3] = s;
						break;
					case "Tome":
						xp[4] = s;
						break;
					case "Staff":
						xp[5] = s;
						break;
					default:
						break;
				}
				w++;
			}
			for(int j = 0; j < xp.length; j++) {
				if(xp[j] == null) {
					xp[j] = "00";
				}
			}
			StaticS.set(n + 6, Static.get(n+6).substring(0,6) +xp[0] +xp[1]);
			StaticS.set(n + 7, "0x" + xp[2] +xp[3] + xp[4] +xp[5]);
			
			String add1 = Static.get(k - 1).substring(2, 10);
			String add2 = Static.get(k - 2).substring(2, 10);
			String[] add = (add1 + add2).split("(?<=\\G.{" + 2 + "})");
			Random rng = new Random();
			Arrays.sort(add, (String a, String b) -> rng.nextInt(100) - rng.nextInt(100));
			StaticS.set(n - 1, "0x" + add[0] + add[1] + add[2] + add[3]);
			StaticS.set(n - 2, "0x" + add[4] + add[5] + add[6] + add[7]);
			String tempLv = Static.get(n + 8);
			tempLv = tempLv.substring(4,6);
			if(Integer.parseInt(tempLv, 16) >= 20 && Util.contains(Data.sClasses, c.name)) {
				tempLv = Integer.toHexString(Integer.parseInt(tempLv, 16) - 15);
				if(tempLv.length() == 1) {
					tempLv = "0" + tempLv;
				}
			}
			StaticS.set(n + 8, Static.get(n+8).substring(0, 4) + tempLv + Static.get(n+8).substring(6, 10));
			i++;
		}
		CoreData.Static = StaticS;
	}
	
	
	
	
	
}
