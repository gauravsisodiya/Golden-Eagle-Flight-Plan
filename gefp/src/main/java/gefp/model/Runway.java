package gefp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="runway")
public class Runway {

	@Id
	@GeneratedValue
	Integer id;

    String name;

   
	public int getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Runway() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
