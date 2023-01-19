
public class LogEntry {
	
	public String name;
	public int[] growths;
	
	public LogEntry (String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder(name + "\n");
		if(growths != null) {
			ret.append("Growths [HP: " + growths[0] + " | Str: " + growths[1] +
					" | Mag: " + growths[2] + " | Spd: " + growths[3] + " | Skl: " + 
					growths[4] + " | Lck: " + growths[5] + " | Def: " + growths[6] +
					" | Res: " + growths[7] + " ]\n");
		}
		return ret.append("\n").toString();
		
		
	}
	
	
	
}
