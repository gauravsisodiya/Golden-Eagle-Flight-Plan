package gefp.model.dao;

import java.util.List;

import gefp.model.Checkpoint;

public interface CheckpointDao {

	Checkpoint getCheckpoint(int id);

	List<Checkpoint> getCheckpoints();

	Checkpoint saveCheckpoint(Checkpoint checkpoint);

	Checkpoint getCheckpointByOrder(int orderId, int cellId);

}
