import java.util.ArrayList;
import java.util.Arrays;

public class Unit {
	
	public String name;
	public int[] growths;
	public int[] caps;
	public String CID;
	public ArrayList<String> classset;
	public String replacementChar;
	public boolean cjr;
	public boolean isRobin;
	
	public Unit (String name) {
		this.name = name;
		isRobin = false;
	}
	public Unit(String name, String CID) {
		this.name = name;
		this.CID = CID;
		isRobin = false;
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
			ret.append("CID: " + CID + "\n");
			ret.append("Class set [ " + classset.get(0) + " " + classset.get(1) + " " + classset.get(2) + " ]");
			//TODO: add code for logging stuff
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
	
	
	
}
