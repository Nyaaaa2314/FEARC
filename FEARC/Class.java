import java.util.ArrayList;

public class Class {
	public String name;
	public boolean promoted;
	public String wa;
	public String wb;
	public String wc;
	public String JID;
	public boolean flier;
	public ArrayList<String> promotions;
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
