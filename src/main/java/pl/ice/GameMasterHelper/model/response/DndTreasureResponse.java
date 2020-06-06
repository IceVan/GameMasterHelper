package pl.ice.GameMasterHelper.model.response;

import lombok.Data;
import pl.ice.GameMasterHelper.model.dnd.DndTreasure;

import java.util.List;

@Data
public class DndTreasureResponse {

    private List<DndTreasure> dndTreasure;

    private String status;
}
