package pl.ice.GameMasterHelper.service.dnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ice.GameMasterHelper.dao.dnd.DnDItemDao;
import pl.ice.GameMasterHelper.dao.dnd.DndHoardTableDao;
import pl.ice.GameMasterHelper.dao.dnd.DndItemTableDao;
import pl.ice.GameMasterHelper.model.DiceRule;
import pl.ice.GameMasterHelper.model.dnd.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DndTreasureService {

    private DnDItemRepository dnDItemRepository;
    private DndItemTableRepository dndItemTableRepository;
    private DndHoardTableRepository dndHoardTableRepository;

    private DnDItemDao dnDItemDao;
    private DndItemTableDao dndItemTableDao;
    private DndHoardTableDao dndHoardTableDao;

    @Autowired
    public DndTreasureService(DnDItemRepository dnDItemRepository,
                              DndItemTableRepository dndItemTableRepository,
                              DndHoardTableRepository dndHoardTableRepository,
                              DnDItemDao dnDItemDao,
                              DndItemTableDao dndItemTableDao,
                              DndHoardTableDao dndHoardTableDao){
        this.dnDItemRepository = dnDItemRepository;
        this.dndItemTableRepository = dndItemTableRepository;
        this.dndHoardTableRepository = dndHoardTableRepository;
        this.dnDItemDao = dnDItemDao;
        this.dndItemTableDao = dndItemTableDao;
        this.dndHoardTableDao = dndHoardTableDao;
    }

    public DndCurrency generateCurrency(){
        return generateCurrency(0,0,0,100,0);
    }

    public DndCurrency generateCurrency(int cp, int sp, int ep, int gp, int pp){
        Random r = new Random();
        DndCurrency currency = new DndCurrency(
                cp == 0 ? 0 : r.nextInt(cp),
                sp == 0 ? 0 : r.nextInt(sp),
                ep == 0 ? 0 : r.nextInt(ep),
                gp == 0 ? 0 : r.nextInt(gp),
                pp == 0 ? 0 : r.nextInt(pp));
        return currency;
    }

    public DndCurrency generateCurrency(DiceRule cp, DiceRule sp, DiceRule ep, DiceRule gp, DiceRule pp){
        return new DndCurrency(
                cp == null ? 0 : cp.generateValue(),
                sp == null ? 0 : sp.generateValue(),
                ep == null ? 0 : ep.generateValue(),
                gp == null ? 0 : gp.generateValue(),
                pp == null ? 0 : pp.generateValue());
    }

    public List<DndHoardTable> getHoardTableResults(int roll, DndEncounterType dndEncounterType){
        return dndHoardTableDao.getResultForRoll(roll, dndEncounterType);
    }

    @Transactional
    public List<DndHoardTable> getRandomHoardTableResults(DndEncounterType dndEncounterType){
        Random random = new Random();
        return dndHoardTableDao.getResultForRoll(random.nextInt(100)+1, dndEncounterType);
    }

    @Transactional
    public List<DnDItem> getRandomItemsFromTable(DndItemTableType dndItemTableType){
        Random random = new Random();
        List<DndItemTable> dndItemTableList = dndItemTableDao.getResultForRollInTable(random.nextInt(100)+1, dndItemTableType);

        return dndItemTableList.stream().map(DndItemTable::getItem).collect(Collectors.toList());
    }

    @Transactional
    public void saveItem(DnDItem item){
        dnDItemRepository.save(item);
    }

    @Transactional
    public void saveItemTable(DndItemTable itemTable) { dndItemTableRepository.save(itemTable); }

    @Transactional
    public void saveHoardTable(DndHoardTable hoardTable) { dndHoardTableRepository.save(hoardTable); }
}
