package gefp.model.dao;

import gefp.model.Stage;

import java.util.List;

public interface StageDao {

	List<Stage> getStages();

	Stage getStage(int id);

	Stage saveStage(Stage stage);

}
