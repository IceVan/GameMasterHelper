package pl.ice.GameMasterHelper.dao.dnd;

import pl.ice.GameMasterHelper.model.dnd.DndEncounterType;
import pl.ice.GameMasterHelper.model.dnd.DndHoardTable;

import java.util.List;

public interface DndHoardTableDao {

    public List<DndHoardTable> getResultForRoll(int roll, DndEncounterType encounterType);


}
