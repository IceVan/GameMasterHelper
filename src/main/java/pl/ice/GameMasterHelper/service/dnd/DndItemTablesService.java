package pl.ice.GameMasterHelper.service.dnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ice.GameMasterHelper.dao.dnd.DnDItemDao;
import pl.ice.GameMasterHelper.dao.dnd.DndItemTableDao;
import pl.ice.GameMasterHelper.model.dnd.*;
import pl.ice.GameMasterHelper.model.response.DndItemWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DndItemTablesService {

    private DnDItemDao dnDItemDao;
    private DndItemTableDao dndItemTableDao;


    @Autowired
    public DndItemTablesService(DnDItemDao dnDItemDao, DndItemTableDao dndItemTableDao) {
        this.dnDItemDao = dnDItemDao;
        this.dndItemTableDao = dndItemTableDao;
    }

    @Transactional
    public List<DndItemTable> getRandomItemTableResults(DndItemTableType itemTableType){
        return dndItemTableDao.getRandomResultInTable(itemTableType);
    }

    @Transactional
    public List<DnDItem> getRandomItemsFromTable(DndItemTableType dndItemTableType){
        List<DndItemTable> dndItemTableList = dndItemTableDao.getRandomResultInTable(dndItemTableType);

        return dndItemTableList.stream().map(DndItemTable::getItem).collect(Collectors.toList());
    }

    @Transactional
    public List<DnDItem> getRandomItemsFromTable(int amount, DndItemTableType dndItemTableType){
        List<DndItemTable> dndItemTableList = new ArrayList<>(amount);
        for(int i = 0; i < amount; i++){
            dndItemTableList.addAll(dndItemTableDao.getRandomResultInTable(dndItemTableType));
        }
        return dndItemTableList.stream().map(DndItemTable::getItem).collect(Collectors.toList());
    }

    private List<DnDItem> generateValuableList(int amount, DndItemTableType dndItemTableType){
        List<DnDItem> valuables = new ArrayList<>(amount);

        for(int i = 0; i < amount; i++){
            valuables.add(dndItemTableDao.getRandomResultInTable(dndItemTableType).get(0).getItem());
        }

        return valuables;
    }

    /**
     * Getting list of random items from certain table type and returns them as wrappers.
     * @param amount            Number of items to generate
     * @param dndItemTableType  Type of item table
     * @return                  List of wrapped items
     */
    @Transactional
    public List<DndItemWrapper> getRandomItemsFromTableAsWrappers(int amount, DndItemTableType dndItemTableType){
        List<DndItemTable> dndItemTableList = dndItemTableDao.getRowsForItemTableType(dndItemTableType);
        List<DndItemWrapper> wrappers = new ArrayList<>(amount);
        Random random = new Random();

        for(int i = 0; i < amount; i++){
            DndItemWrapper wrapper = new DndItemWrapper(getRandomResultFromItemTable(dndItemTableList, random.nextInt(100)+1));
            if(!wrappers.contains(wrapper)) wrappers.add(wrapper);
            else wrappers.get(wrappers.indexOf(wrapper)).incrementAmount();
        }

        return wrappers;
    }

    private DnDItem getRandomResultFromItemTable(List<DndItemTable> itemTableList){
        Random random = new Random();
        return getRandomResultFromItemTable(itemTableList, random.nextInt(100)+1);
    }

    private DnDItem getRandomResultFromItemTable(List<DndItemTable> itemTableList, int roll){

        for(DndItemTable itemTable : itemTableList){
            if(itemTable.isInRange(roll))
                return itemTable.getItem();
        }
        return null;
    }

}
