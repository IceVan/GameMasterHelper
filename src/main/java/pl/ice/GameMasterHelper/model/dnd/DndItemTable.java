package pl.ice.GameMasterHelper.model.dnd;

import lombok.Data;
import pl.ice.GameMasterHelper.model.ItemTable;

import javax.persistence.*;

@Data
@Entity(name = "tdd_item_table")
public class DndItemTable extends ItemTable {

    @ManyToOne(fetch = FetchType.EAGER)
    private DnDItem item;

    @Enumerated(EnumType.STRING)
    private DndItemTableType dndItemTableType;
}
