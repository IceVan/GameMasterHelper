package pl.ice.GameMasterHelper.model.dnd;

import lombok.Data;
import pl.ice.GameMasterHelper.model.HoardTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity(name = "tdd_hoard_table")
public class DndHoardTable extends HoardTable {

    @Column
    @Enumerated(EnumType.STRING)
    private DndItemType valuableType;

    @Enumerated(EnumType.STRING)
    private DndItemTableType valuableTable;

    @Column
    private Integer valuableValue;

    @Column
    @Enumerated(EnumType.STRING)
    private DndItemTableType itemTableType;

    @Column
    @Enumerated(EnumType.STRING)
    private DndEncounterType encounterType;
}
