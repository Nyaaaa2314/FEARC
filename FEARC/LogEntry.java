
public class LogEntry {
	
	public String name;
	public int[] growths;
	public int[] caps;
	
	
	
	public LogEntry (String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder(name + "\n");
		if(growths != null) {
			ret.append("Growths [ HP: " + growths[0] + " | Str: " + growths[1] +
					" | Mag: " + growths[2] + " | Spd: " + growths[3] + " | Skl: " + 
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
