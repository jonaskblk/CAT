package cat.model;

import java.io.Serializable;

public class Kunde extends Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String typ = "Kunde";
	private static final int EINFLUSSFAKTOR = 2;
	
	// Konstruktor
	public Kunde(String name){
		super.setName(name);
	}
	
	// Name, Status, Typ, Einflussfaktor
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.toString());		
		sb.append("\n Typ: " + typ);
		sb.append("\n Einflussfaktor: " + EINFLUSSFAKTOR);
		sb.append("\n\n");
	
		return sb.toString();
	}

	@Override
	public int getEinflussfaktor() {
		return EINFLUSSFAKTOR;
	}
}
