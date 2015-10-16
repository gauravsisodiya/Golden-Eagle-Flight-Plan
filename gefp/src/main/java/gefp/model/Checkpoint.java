package gefp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="checkpoint")
public class Checkpoint {

	@Id
	@GeneratedValue
	Integer id;
	
	Integer orderId;
	
    String description;
    
    @ManyToOne
    Cell cell;
    
    public int getId() {
		return id;
    }


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Checkpoint() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public Cell getCell() {
		return cell;
	}


	public void setCell(Cell cell) {
		this.cell = cell;
	}

    
}
