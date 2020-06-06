package pl.ice.GameMasterHelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Treasure<T extends Item, R extends Currency> {

    private String info;

    private R currency;

    private List<T> items;

    private List<T> valuables;

    public void addItemsToList(List<T> t){
        if(items == null){
            items = new LinkedList<>();
        }
        items.addAll(t);
    }

    public void addItemToList(T t){
        if(items == null){
            items = new LinkedList<>();
        }
        items.add(t);
    }

    public void addValuablesToList(List<T> t){
        if(valuables == null){
            valuables = new LinkedList<>();
        }
        valuables.addAll(t);
    }

    public void addValuableToList(T t){
        if(valuables == null){
            valuables = new LinkedList<>();
        }
        valuables.add(t);
    }

}
