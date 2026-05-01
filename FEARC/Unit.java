import java.util.ArrayList;
import java.util.Arrays;

public class Unit {
	
	public String name;
	public int[] growths;
	public int[] caps;
	public String CID;
	public ArrayList<String> classset;
	public String replacementChar;
	public String parent;
	public boolean isChild;
	public boolean cjr;
	public boolean isRobin;
	public boolean isMorgan;
	public boolean m;
	
	public Unit (String name) {
		this.name = name;
		isRobin = false;
		isChild = Util.contains(Data.gen2, name);
		isMorgan = name.contains("Morgan");
	}
	public Unit(String name, String CID) {
		this.name = name;
		this.CID = CID;
		isChild = Util.contains(Data.gen2, name);
		isRobin = false;
		isMorgan = name.contains("Morgan");
	}
	public Unit (String name, boolean isRobin) {
		this.name = name;
		this.isRobin = isRobin;
	}
	
	public String basicToString() {
		return "Unit [name=" + name + ", growths=" + Arrays.toString(growths) + ", caps=" + Arrays.toString(caps)
				+ ", classset=" + classset + ", replacementChar=" + replacementChar + ", cjr=" + cjr + ", isRobin="
				+ isRobin + "]";
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder(name + "\n");
		if(cjr) {
			ret.append("Original Character: " + replacementChar + "\n");
			if(isChild && !isMorgan){
				ret.append("Parent: " + parent + "\n");
			}
			//ret.append("CID: " + CID + " | DEBUG LINE, REMOVE LATER" + "\n"); //TODO: remove line
			ret.append("Class set [ " + classset.get(0) + ", " + classset.get(1) + ", " + classset.get(2) + " ]\n");
			//TODO: add code for logging stuff
		}
		if(name.contains("Robin")) {
			ret.append("Note: below are Robin's stats BEFORE boons and banes, so their actual stats may vary from the log\n");
		}
		if(growths != null) {
			ret.append("Growths [ HP: " + growths[0] + " | Str: " + growths[1] +
					" | Mag: " + growths[2] + " | SKl: " + growths[3] + " | Spd: " + 
					growths[4] + " | Lck: " + growths[5] + " | Def: " + growths[6] +
					" | Res: " + growths[7] + " ]\n");
		}
		if(caps != null) {
			ret.append("Stat Cap Modifiers [ Str: " + caps[0] +
					" | Mag: " + caps[1] + " | Spd: " + caps[2] + " | Skl: " + 
					caps[3] + " | Lck: " + caps[4] + " | Def: " + caps[5] +
					" | Res: " + caps[6] + " ]\n");
		}
		
		return ret.append("\n").toString();
		
		
	}

	public String toJsonString(){
		StringBuilder ret = new StringBuilder("{\n");
		ret.append("\t\"name\": \"" + name + "\",\n");
		//appent parent and replacement char if cjr
		if(cjr) {
			ret.append("\t\"originalChar\": \"" + replacementChar + "\",\n");
			if(isChild && !isMorgan){
				ret.append("\t\"parent\": \"" + parent + "\",\n");
			}
		}
		if(growths != null) {
			ret.append("\t\"growths\": [ " + growths[0] + ", " + growths[1] +
					", " + growths[2] + ", " + growths[3] + ", " + growths[4] + 
					", " + growths[5] + ", " + growths[6] + ", " + growths[7] + " ]");
		}
		if(caps != null) {
			ret.append(",\n");
			ret.append("\t\"caps\": [ " + caps[0] +
					", " + caps[1] + ", " + caps[2] + ", " + caps[3] +
					", " + caps[4] + ", " + caps[5] + ", " + caps[6] + " ]");
		}
		if(classset != null) {
			ret.append(",\n");
			ret.append("\t\"classset\": [ \"" + classset.get(0) +
					"\", \"" + classset.get(1) +
					"\", \"" + classset.get(2) +
					"\" ]\n");
		}
		
		return ret.append("},").toString();
	}
	
	
	
}
