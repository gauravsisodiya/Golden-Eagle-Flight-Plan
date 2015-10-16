package gefp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="cell")
public class Cell {
	
	@Id
	@GeneratedValue
	Integer id;

	@ManyToOne
    Plan plan;

	@OneToOne
    Runway runway;

	@OneToOne
    Stage stage;
    
    public Cell(Plan plan, Runway runway, Stage stage,
			List<Checkpoint> checkpoints) {
		super();
		this.plan = plan;
		this.runway = runway;
		this.stage = stage;
		this.checkpoints = checkpoints;
	}

    @OrderBy("orderId")
	@OneToMany(mappedBy="cell")
    List<Checkpoint> checkpoints;

	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Runway getRunway() {
		return runway;
	}

	public void setRunway(Runway runway) {
		this.runway = runway;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public List<Checkpoint> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public Cell() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
