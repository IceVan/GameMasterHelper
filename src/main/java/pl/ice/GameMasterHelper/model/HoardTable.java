package pl.ice.GameMasterHelper.model;

import lombok.Data;
import pl.ice.GameMasterHelper.service.converter.DiceRuleConverter;

import javax.persistence.*;

@Data
@MappedSuperclass
public class HoardTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * SELECT ht.* FROM `tdd_hoard_table` ht WHERE 56 BETWEEN ht.range_from and ht.range_to and ht.encounter_type = 'EARLY'
     */
    @Column
    private Integer rangeFrom;

    @Column
    private Integer rangeTo;

    @Column
    @Convert(converter = DiceRuleConverter.class)
    private DiceRule valuablesDice;

    @Column
    @Convert(converter = DiceRuleConverter.class)
    private DiceRule itemsDice;

}
