package pl.ice.GameMasterHelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Treasure<T extends Item, R extends Currency> {

    private String info;

    private R currency;

    private List<T> items;

    private List<T> valuables;

}
