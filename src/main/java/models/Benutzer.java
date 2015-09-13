package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Benutzer
 *
 */
@Entity
public class Benutzer implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Benutzer() {
		super();
	}
	
	public String getVorname() {
		return Vorname;
	}

	public void setVorname(String vorname) {
		Vorname = vorname;
	}

	@Id
	@GeneratedValue
	private Long ID;
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	private String Vorname;
   
}
