package cat.model;

import java.io.Serializable;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private boolean imGeb�ude = false;
	
	public abstract int getEinflussfaktor();	
	
	// Getter und Setter
	public final String getName() {
		return name;
	}
	
	public final void setName(String name) {
		this.name = name;
	}
	
	public final void geb�udeBetreten() {
		this.imGeb�ude = true;
	}
	
	public final void geb�udeVerlassen() {
		this.imGeb�ude = false;
	}
	
	public final boolean getStatus() {
		return imGeb�ude;
	}
	
	private final String statusAlsText() {
		if (imGeb�ude)
			return "ja";
		else
			return "nein";
	}
	
	//Name, Status
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Name: " + name);
		sb.append("\n Im Geb�ude: " + statusAlsText());
	
		return sb.toString();
	}
	
	
}
