package cat.dev;

import java.util.ArrayList;

import cat.model.Kunde;
import cat.model.Mitarbeiter;
import cat.model.Person;

public class Dummy {
		
	public static ArrayList<Person> getPersonen10() {
		
		ArrayList<Person> vielePersonen = new ArrayList<>();

		vielePersonen.add(new Kunde("Jonas"));
		vielePersonen.add(new Mitarbeiter("Christian"));
		vielePersonen.add(new Kunde("Antonia"));
		vielePersonen.add(new Mitarbeiter("Felix"));
		vielePersonen.add(new Kunde("Max"));
		vielePersonen.add(new Mitarbeiter("Hannes"));
		vielePersonen.add(new Mitarbeiter("Tom"));
		vielePersonen.add(new Mitarbeiter("Stefan"));
		vielePersonen.add(new Kunde("Leon"));		
		vielePersonen.add(new Kunde("Uschi"));
		
		return vielePersonen;
	}
}
