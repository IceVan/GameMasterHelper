package pl.ice.GameMasterHelper.model.dnd;

import lombok.Data;
import pl.ice.GameMasterHelper.model.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity(name = "tdd_item")
public class DnDItem extends Item {

    @Column
    @Enumerated(EnumType.STRING)
    private DndItemType dndItemType;

    @Column
    @Enumerated(EnumType.STRING)
    private ItemRarity itemRarity;
}
