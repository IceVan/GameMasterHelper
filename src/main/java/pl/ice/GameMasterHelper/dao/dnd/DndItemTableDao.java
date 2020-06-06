package pl.ice.GameMasterHelper.dao.dnd;

import pl.ice.GameMasterHelper.model.dnd.DndItemTable;
import pl.ice.GameMasterHelper.model.dnd.DndItemTableType;

import java.util.List;

public interface DndItemTableDao {

    List<DndItemTable> getResultForRollInTable(int roll, DndItemTableType dndItemTableType);

    List<DndItemTable> getRandomResultInTable(DndItemTableType dndItemTableType);

}
