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
	
	
	
	
	//temporary simple constructor
	public Class(String name) { 
		this.name = name;
		slines = new ArrayList<String>();
		skills = new ArrayList<String>();
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
		if(skills.size() > 0) {
			ret.append("Skills [ " + Data.skillNames[Util.indexOf(Data.Skills, skills.get(0))] + " | " + Data.skillNames[Util.indexOf(Data.Skills, skills.get(1))] + " ]");
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
