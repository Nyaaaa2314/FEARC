import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Data {
	private static Data d;
	public final static String[] CharacterNames = { "Chrom", "Lissa", "Frederick", "Virion", "Sully", "Vaike", "Stahl", "Miriel", "Kellam", "Sumia", "Lon'qu", "Ricken", "Maribelle", "Panne", "Gaius", "Cordelia", "Gregor", "Nowi", "Libra", "Tharja", "Olivia", "Cherche", "Henry", "Lucina", "Say'ri", "Basilio", "Flavia", "Donnel", "Anna", "Owain", "Inigo", "Brady", "Kjelle", "Cynthia", "Severa", "Gerome", "M!Morgan", "F!Morgan", "Yarne", "Laurent", "Noire", "Nah", "Tiki", "Gangrel", "Walhart", "Emmeryn", "Yen'fay", "Aversa", "Priam" };
	public static ArrayList<Unit> units;
	public final static String[] Skills = {"00","01", "02", "03", "04", "05", "06", "07", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57","58","5A","63","64","65","66"};
	public static ArrayList<Class> classes;
	public final static boolean[] charGender = { true,false,true,true,false,true,true,false,true,false,true,true,false,false,true,false,true,false,true,false,false,false,true,false,false,true,false,true,false,true,true,true,false,false,false,true,true,false,true,true,false,false,false,true,true,false,true,false,true};
	public final static String[] skillNames = {"No Skill", "HP +5", "Strength +2", "Magic +2", "Skill +2", "Speed +2", "Defense +2", "Resistance +2", "Hit Rate +20", "Avoid +10", "Movement +1", "Locktouch", "Veteran", "Aptitude", "Discipline", "Armsthrift", "Dual Support+", "Dual Strike+", "Dual Guard+", "Rightful King", "Odd Rhythm", "Even Rhythm", "Quick Burn", "Slow Burn", "Lucky Seven", "Gamble", "Outdoor Fighter", "Indoor Fighter", "Tantivy", "Focus", "Zeal", "Wrath", "Prescience", "Patience", "Underdog", "Charm", "Solidarity", "Demoiselle", "Hex", "Anathema", "Healtouch", "Relief", "Renewal", "Deliverer", "Defender", "Acrobat", "Pass", "Swordfaire", "Lancefaire", "Axefaire", "Bowfaire", "Tomefaire", "Luck +4", "Special Dance", "Rally Strength", "Rally Magic", "Rally Skill", "Rally Speed", "Rally Luck", "Rally Defense", "Rally Resistance", "Rally Movement", "Rally Spectrum", "Swordbreaker", "Lancebreaker", "Axebreaker", "Bowbreaker", "Tomebreaker", "Wyrmsbane", "Beastbane", "Lethality", "Aether", "Astra", "Sol", "Luna", "Ignis", "Vengeance", "Vantage", "Pavise", "Aegis", "Counter", "Miracle", "Despoil", "Galeforce", "Lifetaker", "Conquest", "Shadowgift","All Stats +2","Iote's Shield","Resistance +10","Aggressor","Rally Heart","Bond"};
	public final static String[] sClasses = {"Villager", "Dancer", "Taguel", "Manakete", "Lodestar", "Dread Fighter", "Bride", "Conqueror"};
	public final static String[] fliers = {"Dark Flier", "Pegasus Knight", "Falcon Knight", "Wyvern Knight", "Wyvern Lord", "Griffon Rider"};
	public final static String[] gen2 = {"Lucina","Owain", "Inigo", "Severa", "Brady", "Kjelle", "Noire","F!Morgan","M!Morgan", "Gerome", "Cynthia", "Yarne", "Laurent", "Nah"};
	public final static String[] CID = { "PID_クロム", "PID_リズ", "PID_フレデリク", "PID_ヴィオール", "PID_ソワレ", "PID_ヴェイク", "PID_ソール", "PID_ミリエル", "PID_カラム", "PID_スミア", "PID_ロンクー", "PID_リヒト", "PID_マリアベル", "PID_ベルベット", "PID_ガイア", "PID_ティアモ", "PID_グレゴ", "PID_ノノ", "PID_リベラ", "PID_サーリャ", "PID_オリヴィエ", "PID_セルジュ", "PID_ヘンリー", "PID_マルス", "PID_サイリ", "PID_バジーリオ", "PID_フラヴィア", "PID_ドニ", "PID_アンナ", "PID_ウード", "PID_アズール", "PID_ブレディ", "PID_デジェル", "PID_シンシア", "PID_セレナ", "PID_ジェローム", "PID_マーク男", "PID_マーク女", "PID_シャンブレー", "PID_ロラン", "PID_ノワール", "PID_ンン", "PID_チキ", "PID_ギャンレル", "PID_ヴァルハルト", "PID_エメリナ", "PID_レンハ", "PID_インバース", "PID_パリス"};
	
	
	public static ArrayList<ArrayList<String>> classSets;
	public static ArrayList<Unit> rUnits;
	public static HashMap<String, ArrayList<String>> bpPromote;
	
	public final static String[] swords = {"IID_青銅の剣", "IID_鉄の剣", "IID_鋼の剣", "IID_銀の剣", "IID_勇者の剣", "IID_キルソード", "IID_アーマーキラー", "IID_アーマーキラー", "IID_サンダーソード", "IID_太陽", "IID_ラグネル", "IID_メリクル" };
    public final static String[] lances = { "IID_青銅の槍", "IID_鉄の槍", "IID_鋼の槍", "IID_銀の槍", "IID_勇者の槍", "IID_キラーランス","NA", "IID_ビーストキラー","IID_しびれる槍", "IID_グラディウス", "IID_グングニル", "IID_ゲイボルグ" };
    public final static String[] axe = { "IID_青銅の斧", "IID_鉄の斧", "IID_鋼の斧", "IID_銀の斧", "IID_勇者の斧", "IID_キラーアクス", "IID_ハンマー","NA", "IID_ボルトアクス", "IID_オートクレール", "IID_スワンチカ", "IID_アルマーズ"};
    public final static String[] bows = { "IID_青銅の弓", "IID_鉄の弓", "IID_鋼の弓", "IID_銀の弓", "IID_勇者の弓", "IID_キラーボウ","NA","NA","NA", "IID_流星", "IID_パルティア", "IID_イチイバル"};
    public final static String[][] magic = { { "IID_ファイアー", "IID_ウインド", "IID_サンダー", "IID_ミィル" },{ "IID_エルファイアー", "IID_エルサンダー", "IID_エルウインド", "IID_リザイア" },{ "IID_ギガファイアー", "IID_ギガサンダー", "IID_ギガウインド", "IID_ルイン" },{ "IID_ボルガノン", "IID_トロン", "IID_レクスカリバー", "IID_ゲーティア" },/*skip to ledendary*/ { "IID_トールハンマー", "IID_フォルセティ", "IID_ファラフレイム", "IID_インバースの暗闇" } };
    public final static String[] staves = { "IID_ライブ", "IID_リライブ", "IID_リブロー", "IID_リカバー", "IID_リザーブ",/*skip to legendary again, omly 1*/ "IID_女神の杖" };
    public final static String[][] stones = { { "IID_獣石", "IID_竜石" }, { "IID_超獣石", "IID_真竜石" } };
	
	
	
	
	
//	public final static char m;
//	public final static char f;
	private Data() throws IOException {
		units = new ArrayList<Unit>();
		units.add(new Unit("M!Robin", true));
		units.add(new Unit("F!Robin", true));
		int t = 0;
		for(String s : CharacterNames) {
			Unit temp = new Unit(s, CID[t]);
			temp.m = charGender[t++];
			units.add(temp);
		}
		
		//Class parsing from classes.txt
		classes = new ArrayList<Class>();
		FileSys f = FileSys.getInstance();
		ArrayList<String> GameData = f.readFile("/data/bins/GameData.txt");
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
				for(int j = 0; j < temp.slines.size(); j++) {
					temp.pointers[j] = Integer.parseInt(GameData.get(Integer.parseInt(temp.slines.get(j)) - 18).substring(6));
				}
				
			}
			temp.skills.add(GameData.get(Integer.parseInt(temp.slines.get(0))).substring(2,4));
			temp.skills.add(GameData.get(Integer.parseInt(temp.slines.get(0))).substring(6,8));
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
			for(int j = 0; j < 2; j++) {
				String temp2 = all.get(rng.nextInt(0, all.size()));
				while(temp.contains(temp2)) {
					temp2 = all.get(rng.nextInt(0, all.size()));
				}
				temp.add(temp2);
			}
//			temp.add(all.get(rng.nextInt(0, all.size())));
//			temp.add(all.get(rng.nextInt(0, all.size())));
			
			if(temp.contains("Tactician")) {
				if(rng.nextInt(0,20) == 0) {
					classSets.add(temp);
					continue;
				}
				else {
					temp.remove("Tactician");
					String temp2 = all.get(rng.nextInt(0, all.size()));
					while(temp.contains(temp2) || temp2.equals("Tactician")) {
						temp2 = all.get(rng.nextInt(0, all.size()));
					}
					temp.add(temp2);
				}
			}
			classSets.add(temp);
		}
		
		ArrayList<Unit> temp = new ArrayList<Unit>(units.subList(2, units.size()));
		
		temp = Util.shuffleGens(temp);
		ArrayList<Unit> temp2 = new ArrayList<Unit>(units.subList(0,2));
		units = new ArrayList<Unit>();
		units.addAll(temp2);
		units.addAll(temp);
		int i = 0;
		for(Unit u : units) {
			if(u.isRobin) {
				continue;
			}
			u.cjr = true;
			u.replacementChar = CharacterNames[i];
			u.classset = classSets.get(i++);
		}
		rUnits = new ArrayList<Unit>(units.subList(2, units.size()));
		
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
	
	
	
	
	/*
    The way im gonna do this method is that im going to have arrays of the iids of every major weapon type
    each array will be ordered by the "tier" they are (not all of them are precisely ordered), that way 
    characters will both get a weapon that they can use that's also fitting for whatever chapter they are on, 
    the tiers are:
    0 - bronze
    1 - iron
    2 - steel
    3 - silver
    4 - brave
    5 - killer (if applicable)
    6-7 - special (armor eff, dragon eff; if applicable)
    8 - magic-physical (levin, shockstick, etc,; if applicable)
    9-11 - legendary (ragnell, amatsu, etc)

    *non-dark magic has multiple items per tier, for example, tier 0 for magic has fire, wind, and thunder as possible options
    */
	
	public static String weaponSwap(String type, int tier, int btier, boolean canusedarkmagic)
    {
        switch (type)
        {
            case "Sword":
                if(tier >= 9)
                {
                    Random rnd = new Random();
                    return swords[9 + rnd.nextInt(3)];
                }
                else
                {
                   return swords[tier];
                }
            case "Lance":
                if (tier >= 9)
                {
                    Random rnd = new Random();
                    return lances[9 + rnd.nextInt(3)];
                }
                if (tier == 6)
                {
                    return lances[btier];
                }
                else
                {
                    return lances[tier];
                }
            case "Axe":
                if (tier >= 9)
                {
                    Random rnd = new Random();
                    return axe[9 + rnd.nextInt(3)];
                }
                if (tier == 7)
                {
                    return axe[btier];
                }
                else
                {
                    return axe[tier];
                }
            case "Bow":
                if (tier >= 9)
                {
                    Random rnd = new Random();
                    return bows[9 + rnd.nextInt(3)];
                }
                if (tier < 9 && tier > 5)
                {
                    return bows[btier];
                }
                else
                {
                    return bows[tier];
                }
            case "Tome":
                if (btier > 3)
                {
                    btier = 3;
                }
                if (canusedarkmagic)
                {
                    if(tier < 4)
                    {
                        return magic[tier][3];
                    }
                    else if(tier >= 9)
                    {
                        Random rnd = new Random();
                        return magic[4][3];
                    }
                    else
                    {
                        return magic[btier][3];
                    }
                }
                else
                {
                    Random rnd = new Random();
                    if (tier < 4)
                    {
                        return magic[tier][rnd.nextInt(3)];
                    }
                    else if (tier >= 9)
                    {
                        
                        return magic[4][rnd.nextInt(3)];
                    }
                    else
                    {
                        return magic[btier][rnd.nextInt(3)];
                    }
                }
            case "Staff":
                if (tier < 5)
                {
                    return staves[tier];
                }
                else if(tier >= 9)
                {
                    return staves[5];
                }
                else
                {
                    return staves[btier];
                }
            case "Dragonstone":
                if(tier >= 9 || btier == 4)
                {
                    return stones[1][0];
                }
                else
                {
                    return stones[0][1];
                }
            case "Beaststone":
                if (tier >= 9 || btier == 4)
                {
                    return stones[1][0];
                }
                else
                {
                    return stones[0][0];
                }
            case "none":
                return "None";

        }
        
        return type;
    }
	
	
	
	
	
	
	
	
	
}
