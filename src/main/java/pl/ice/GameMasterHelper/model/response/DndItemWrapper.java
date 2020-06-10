package pl.ice.GameMasterHelper.model.response;

import pl.ice.GameMasterHelper.model.dnd.DnDItem;

public class DndItemWrapper {

    private DnDItem item;

    private Integer amount;

    private String location;

    public void incrementAmount(){
        amount++;
    }

    public DndItemWrapper(DnDItem dndItem){
        this.item = dndItem;
        this.amount = 1;
        this.location = "Found among crates.";
    }

    @Override
    public boolean equals(Object w){
        if (w instanceof DndItemWrapper) {
            DndItemWrapper otherWrapper = (DndItemWrapper) w;
            return item.equals(otherWrapper.item) &&
                    location.equals(otherWrapper.location);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 17*item.hashCode()+31*location.hashCode();
    }

    public DnDItem getItem() {
        return item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
