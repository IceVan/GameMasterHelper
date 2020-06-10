package pl.ice.GameMasterHelper.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class ItemTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer rangeFrom;

    private Integer rangeTo;

    public boolean isInRange(int i){
        return i >= rangeFrom && i <= rangeTo;
    }
}
