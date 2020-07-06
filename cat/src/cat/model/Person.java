package cat.model;

import java.io.Serializable;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private boolean imGebäude = false;
	
	public abstract int getEinflussfaktor();	
	
	// Getter und Setter
	public final String getName() {
		return name;
	}
	
	public final void setName(String name) {
		this.name = name;
	}
	
	public final void gebäudeBetreten() {
		this.imGebäude = true;
	}
	
	public final void gebäudeVerlassen() {
		this.imGebäude = false;
	}
	
	public final boolean getStatus() {
		return imGebäude;
	}
	
	private final String statusAlsText() {
		if (imGebäude)
			return "ja";
		else
			return "nein";
	}
	
	//Name, Status
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Name: " + name);
		sb.append("\n Im Gebäude: " + statusAlsText());
	
		return sb.toString();
	}
	
	
}
