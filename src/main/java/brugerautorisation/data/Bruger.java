package brugerautorisation.data;

import java.io.Serializable;
import java.util.HashMap;

public class Bruger implements Serializable
{
	// Vigtigt: Sæt versionsnummer så objekt kan læses selvom klassen er ændret!
	private static final long serialVersionUID = 12345; // bare et eller andet nr.

	public String brugernavn; // studienummer
	public String email;
	public long sidstAktiv;
	public String campusnetId; // campusnet database-ID
	public String studeretning;
	public String fornavn;
	public String efternavn;
	public String adgangskode;
  	public HashMap<String,Object> ekstraFelter = new HashMap<>();


	public String toString()
	{
		return email;
	}
}