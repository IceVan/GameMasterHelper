package pl.ice.GameMasterHelper.service.dnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ice.GameMasterHelper.dao.dnd.DnDItemDao;
import pl.ice.GameMasterHelper.dao.dnd.DndItemTableDao;
import pl.ice.GameMasterHelper.model.dnd.*;

import java.util.ArrayList;
import java.util.List;
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

}
