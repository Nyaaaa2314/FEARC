import java.io.IOException;
import java.util.ArrayList;
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
	
//	for every character
//
//	if u consider the first growth line to be 0 then
//
//	+9,10,11 are skills
//
//	+18,19,20,21,22,23 are reclasses
	//0x 00 00 00 00
	public static void CJRStatic() {
		int i = 0;
		int acc = 0;
		//16 + 143 * i
		ArrayList<String> Static = CoreData.Static;
		ArrayList<String> StaticS = (ArrayList<String>) Static.clone();
		//System.out.println(Data.units.size());
		for(Unit u : Data.rUnits) {
			
			int k = 12 + 143 * i;
			int n = 12 + 143 * (Util.indexOf(Data.CharacterNames, u.name) + 3);
//			System.out.println(u.name);
//			System.out.println(u.replacementChar);
//			System.out.println("------------------");
			Class c = Util.cSearch(u.classset.get(0));
			Class pc = null;
			if(!Static.get(k + 9).substring(2, 4).equals("00")) {
				if(!Static.get(k + 9).substring(6, 8).equals("00")) {
					StaticS.set(n + 9, "0x" + c.skills.get(0) + "00" + c.skills.get(1) + "00");
					if(Util.contains(Data.sClasses, c.name)) {
						StaticS.set(n + 10, "0x00000000");
						StaticS.set(n + 11, "0x00000000");
					}
					else {
						if(!Static.get(k + 10).substring(2, 4).equals("00")) {
							pc = Util.cSearch(c.promotions.get(0));
							if(!Static.get(k + 10).substring(6, 8).equals("00")) {
								StaticS.set(n + 10, "0x" + pc.skills.get(0) + "00" + pc.skills.get(1) + "00");
							}
							else {
								StaticS.set(n + 10, "0x" + pc.skills.get(0) + "000000");
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
			//StaticS.set(, null)
			StaticS.set(n - 5, pc != null || u.replacementChar.equals("Frederick") ? pc.genJID(u.m) : c.genJID(u.m));
			
			
			i++;
		}
		CoreData.Static = StaticS;
	}
	
	
	
	
	
}
