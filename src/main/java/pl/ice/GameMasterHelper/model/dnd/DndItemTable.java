package pl.ice.GameMasterHelper.model.dnd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.ice.GameMasterHelper.model.ItemTable;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity(name = "tdd_item_table")
public class DndItemTable extends ItemTable {

    @ManyToOne(fetch = FetchType.LAZY)
    private DnDItem item;

    @Enumerated(EnumType.STRING)
    private DndItemTableType dndItemTableType;
}
