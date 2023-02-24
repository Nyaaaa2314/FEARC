import java.util.ArrayList;
//import javafx.util.Pair;

public class Class {
	public String name;
	public boolean promoted;
	public String wa;
	public String wb;
	public String wc;
	public String JID;
	public boolean flier;
	public int gender; //to keep track of gender-locked classes, 0 = both, 1 = male, 2 = female
	public ArrayList<String> promotions;
	public ArrayList<String> skills;
	public ArrayList<String> slines;
	public int[] growths;
	public int[] caps;
	public int[] pointers;
	public int[] pairups;
	public boolean sp; // == if promotions were modified
	public int pa; // # of possible answers for shuffling promotes
	boolean dark; //if it can use dark magic or not
	
	
	
	//temporary simple constructor
	public Class(String name) { 
		this.name = name;
		slines = new ArrayList<String>();
		skills = new ArrayList<String>();
		sp = false;
		flier = Util.contains(Data.fliers, name);
		dark = name.equals("Dark Mage") || name.equals("Sorcerer");
	}
	
	public void setGender(int gender) {
		this.gender = gender;
		if(promoted) {
			pointers = (gender == 0) ? new int[2] : new int[1];
		}
	}
	
	public String genJID(boolean m) {
		if(gender != 0) {
			return JID;
		}
		else {
			if(name.equals("War Monk / War Cleric")) {
				if(m) {
					return "JID_バトルモンク男";
				}
				else {
					return "JID_バトルシスター女";
				}
			}
			else if(name.equals("Priest/Cleric")) {
				if(m) {
					return "JID_僧侶男";
				}
				else {
					return "JID_シスター女";
				}
			}
			else {
				return JID + (m ? '男' : '女');
			}
		}
	}
	@Override
	public String toString() {
		return "Class [name=" + name + ", promoted=" + promoted + ", wa=" + wa + ", wb=" + wb + ", wc=" + wc + ", JID="
				+ JID + ", skills=" + skills + ", slines=" + slines + "]";
	}


	public String toLog() {
		//formatting this like logEntry for when i eventually add class growth and stat cap randomization
		StringBuilder ret = new StringBuilder(name + "\n");
		if(growths != null) {
			ret.append("Growths [ HP: " + growths[0] + " | Str: " + growths[1] +
					" | Mag: " + growths[2] + " | Skl: " + growths[3] + " | Spd: " + 
					growths[4] + " | Lck: " + growths[5] + " | Def: " + growths[6] +
					" | Res: " + growths[7] + " ]\n");
		}
		if(caps != null) {
			ret.append("Caps [ HP: " + caps[0] + " | Str: " + caps[1] +
					" | Mag: " + caps[2] + " | Skl: " + caps[3] + " | Spd: " + 
					caps[4] + " | Lck: " + caps[5] + " | Def: " + caps[6] +
					" | Res: " + caps[7] + " ]\n");
		}
		if(pairups != null) {
			ret.append("Pair Up Bonuses [ Mov: " + pairups[0] + " | Str: " + pairups[1] +
					" | Mag: " + pairups[2] + " | Skl: " + pairups[3] + " | Spd: " + 
					pairups[4] + " | Lck: " + pairups[5] + " | Def: " + pairups[6] +
					" | Res: " + pairups[7] + " ]\n");
		}
		if(skills.size() > 0) {
			ret.append("Skills [ " + Data.skillNames[Util.indexOf(Data.Skills, skills.get(0))] + " | " + Data.skillNames[Util.indexOf(Data.Skills, skills.get(1))] + " ]\n");
		}
		if(sp) {
			ret.append("Promotions [ " + promotions.get(0) + " | " + promotions.get(1) + " ]");
		}
		
		return ret.append("\n").toString();
	}
	
	public Class(String name, boolean promoted, String wa, String wb, String wc, String JID, boolean flier) {
		this.name = name;
		this.promoted = promoted;
		this.wa = wa;
		this.wb = wb;
		this.wc = wc;
		this.JID = JID;
		this.flier = flier;
	}
}
