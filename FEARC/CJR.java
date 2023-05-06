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
		String d;
		switch(cmd) {
			case "load":
				cf = f.readFile("/data/" + in.next());
				break;
			case "replace":
				c = in.nextInt();
				a = in.next();
				b = parseSpecialCommand(in.nextLine());
				
				//in.nextLine();
				
				d = cf.get(c - 1);
				d = d.replace(a, b);
				cf.set(c - 1, d);
				break;
			case "breplace": //bulk replace
				a = in.next();
				b = parseSpecialCommand(in.nextLine());
				c = 0;
				for(String line : cf) {
					cf.set(c++, line.replace(a,b));
				}
				break;
			case "blreplace": //bulk-limit replace
				c = in.nextInt();
				a = in.next();
				b = parseSpecialCommand(in.nextLine());
				for(int i = c; i < cf.size(); i++) {
					d = cf.get(i - 1);
					d = d.replace(a, b);
					cf.set(i - 1, d);
				}
				break;
			case "freplace": //replace first - replaces first instance of substring a with substring b
				a = in.next();
				b = parseSpecialCommand(in.nextLine());
				for(int i = 0; i < cf.size(); i++) {
					d = cf.get(i);
					if(d.contains(a)) {
						d = d.replace(a, b);
						cf.set(i, d);
						break;
					}
				}
				break;
			case "lreplace":
//				if(!in.hasNextInt()) {
//					String nums = in.next();
//					nums = nums.replace(',', ' ').replace('[', ' ').replace(']', ' ');
//					ArrayList<Integer> lines = new ArrayList<Integer>();
//					Scanner sc = new Scanner(nums);
//					while(sc.hasNextInt()) {
//						lines.add(sc.nextInt());
//					}
//					a = parseSpecialCommand(in.nextLine());
//					for(int l : lines) {
//						cf.set(l--, a);
//					}
//					sc.close();
//					break;
//				}
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
					int i = type == 1 ? 0 : (type == 2 ? 30 : 15);
					g.set(i + 69311, "0x" + Util.wepToHex(cl.wa) + "000000");
					switch(Util.wepToHex(cl.wa)) {
						case "03":
							g.set(i + 69313, "0x05500002");
							//no break here on purpose
						case "04":
							g.set(i + 69314, "0x02000000");
							break;
						case "05":
							g.set(i + 69313, "0x0A500001");
							g.set(i + 69305, "0x00340040");
							g.set(i + 69311, "0x" + Util.wepToHex(cl.wa) + "000101");
							g.set(i + 69318, "リライブ");
							break;
						case "06":
							g.set(i + 69305, "0x00740040");
							g.set(i + 69314, "0x02000000");
							g.set(i + 69315, "0x00080503");
							g.set(i + 69316, "0x02000A07");
							g.set(i + 69312, "0x00010000");
							break;
						case "07":
							g.set(i + 69305, "0x00740040");
							g.set(i + 69312, "0x00010000");
							g.set(i + 69316, "0x05040100");
							g.set(i + 69315, "0x00030005");
							break;
					}
					CoreData.GameData = g;
				}
				break;
			case "save":
				f.writeFile("/output" + in.nextLine().trim(), cf);
				break;
			default:
				throw new IllegalArgumentException("Command invalid: " + s);
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
			
			if(temp == null) {
				access.close();
				throw new IllegalStateException("Name of character is invalid in command: " + in);
			}
			switch(field) {
				case "CID":
					access.close();
					return temp.CID;
				case "CLASS":
					String ret = access.next().equals("F") ? temp.classset.get(0) : Util.cSearch(temp.classset.get(0)).promotions != null ? Util.cSearch(temp.classset.get(0)).promotions.get(0) : temp.classset.get(0);
					boolean m = access.next().equals("M");
					access.close();
					return Util.cSearch(ret).genJID(m);
				default:
					access.close();
					throw new IllegalStateException("Invalid field at command: " + in);
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
 *  string indexing reference
 */
	public static void CJRStatic() {
		int i = 3;
		int acc = 0;
		//16 + 143 * i
		ArrayList<String> Static = CoreData.Static;
		ArrayList<String> StaticS = (ArrayList<String>) Static.clone();
		//System.out.println(Data.units.size());
		StaticS.set(21, "0x" + Util.cSearch("Tactician").skills.get(0) + "000000");
		StaticS.set(12 + 143 + 9, "0x" + Util.cSearch("Tactician").skills.get(0) + "000000");
		for(Unit u : Data.rUnits) {
			int numSkills =  1;
			int k = 12 + 143 * i;
			int n = 12 + 143 * (Util.indexOf(Data.CharacterNames, u.name) + 3);
			if(i == 3) {
				StaticS.set(k - 9, "0x00" + Static.get(k -9).substring(4, 10));
				StaticS.set(n - 9, u.m ? "0x22" + Static.get(n -9).substring(4, 10) : "0x23" + Static.get(n -9).substring(4, 10));
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
							if(pc != null) {
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
						else {
							StaticS.set(n + 10, "0x00000000");
						}
					}
				}
				else {
					StaticS.set(n + 9, "0x" + c.skills.get(0) + "000000");
					StaticS.set(n + 10, "0x00000000");
				}
			}
			StaticS.set(n + 11, "0x00000000");
			if(Util.contains(Data.PromotedChars, u.replacementChar) && c.promotions != null ) {
				pc = Util.cSearch(c.promotions.get(0));
			}
			//StaticS.set(, null)
			StaticS.set(n - 5, pc != null ? pc.genJID(u.m) : c.genJID(u.m));
			
			StaticS.set(n + 18, Util.cSearch(u.classset.get(0)).genJID(true));
			StaticS.set(n + 19, Util.cSearch(u.classset.get(1)).genJID(true));
			StaticS.set(n + 20, Util.cSearch(u.classset.get(2)).genJID(true));
			StaticS.set(n + 21, Util.cSearch(u.classset.get(0)).genJID(false));
			StaticS.set(n + 22, Util.cSearch(u.classset.get(1)).genJID(false));
			StaticS.set(n + 23, Util.cSearch(u.classset.get(2)).genJID(false));
			
			String wep1 = Static.get(k + 6).substring(6,10);
			String wep2 = Static.get(k + 7).substring(2,10);
			String[] wep = (wep1 + wep2).split("(?<=\\G.{" + 2 + "})");
			Arrays.sort(wep, (String a, String b) -> Integer.parseInt(b, 16) - Integer.parseInt(a, 16));
			if(wep[0].equals("00") || wep[0].equals("01")) {
				wep[0] = "24";
			}
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
			
			
			
			String tempLv = Static.get(k + 8).substring(4,6);
			int lvInt = Integer.parseInt(tempLv, 16);
			//System.out.println(Static.get(k - 5));
			if(Util.contains(Data.sClasses, c.name) != Util.contains(Data.sClasses, Util.searchByJID(Static.get(k - 5)).name)) {
				if(Util.contains(Data.sClasses, Util.searchByJID(Static.get(k -5)).name)) {
					lvInt /= 2;
				}
				else {
					lvInt *= 2;
				}
			}
			tempLv = Integer.toHexString(lvInt);
			if(tempLv.length() == 1) {
				tempLv = "0" + tempLv;
			}
			i++;
			StaticS.set(n + 8, Static.get(n+8).substring(0, 4) + tempLv + Static.get(n+8).substring(6, 10));
			if(Util.search(u.name).equals("Lucina")) {
				if(u.name.equals("Lucina")) {
					continue;
				}
				StaticS.set(3721, Static.get(3721).substring(0,4) + "00" + Static.get(3721).substring(6));
				StaticS.set(n - 9, Static.get(n - 9).substring(0,4) + "10" + Static.get(n - 9).substring(6));
			}
			//i++;
		}
//		Unit temp = Util.search("Lucina");
//		if(!temp.name.equals("Lucina")) {
//			
//		}
		
		CoreData.Static = StaticS;
	}
	
	
	
	
	
}
