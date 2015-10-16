package gefp.model.dao;

import gefp.model.Cell;

import java.util.List;

public interface CellDao {

	Cell isExistCell(Integer runway_id, Integer stage_id,Integer plan_id);

	Cell saveCell(Cell cell);

	Cell getCell(Integer cell_id);
	

}
