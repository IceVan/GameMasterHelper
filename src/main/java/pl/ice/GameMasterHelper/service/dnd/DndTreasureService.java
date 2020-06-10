package pl.ice.GameMasterHelper.service.dnd;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ice.GameMasterHelper.dao.dnd.DndHoardTableDao;
import pl.ice.GameMasterHelper.model.DiceRule;
import pl.ice.GameMasterHelper.model.dnd.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Log
@Service
public class DndTreasureService {

    private DnDItemRepository dnDItemRepository;
    private DndItemTableRepository dndItemTableRepository;
    private DndHoardTableRepository dndHoardTableRepository;

    private DndHoardTableDao dndHoardTableDao;

    private DndItemTablesService dndItemTablesService;

    @Autowired
    public DndTreasureService(DnDItemRepository dnDItemRepository,
                              DndItemTableRepository dndItemTableRepository,
                              DndHoardTableRepository dndHoardTableRepository,
                              DndHoardTableDao dndHoardTableDao,
                              DndItemTablesService dndItemTablesService){
        this.dnDItemRepository = dnDItemRepository;
        this.dndItemTableRepository = dndItemTableRepository;
        this.dndHoardTableRepository = dndHoardTableRepository;
        this.dndHoardTableDao = dndHoardTableDao;
        this.dndItemTablesService = dndItemTablesService;
    }

    public DndCurrency generateCurrency(){
        return generateCurrency(0,0,0,100,0);
    }

    public DndCurrency generateCurrency(int cp, int sp, int ep, int gp, int pp){
        Random r = new Random();
        return new DndCurrency(
                cp == 0 ? 0 : r.nextInt(cp),
                sp == 0 ? 0 : r.nextInt(sp),
                ep == 0 ? 0 : r.nextInt(ep),
                gp == 0 ? 0 : r.nextInt(gp),
                pp == 0 ? 0 : r.nextInt(pp));
    }

    public DndCurrency generateCurrency(DiceRule cp, DiceRule sp, DiceRule ep, DiceRule gp, DiceRule pp){
        return new DndCurrency(
                cp == null ? 0 : cp.generateValue(),
                sp == null ? 0 : sp.generateValue(),
                ep == null ? 0 : ep.generateValue(),
                gp == null ? 0 : gp.generateValue(),
                pp == null ? 0 : pp.generateValue());
    }

    private DndCurrency generateCurrencyForEncounterType(DndEncounterType dndEncounterType){
        switch (dndEncounterType){
            case EARLY:
                return generateCurrency(new DiceRule(6,6,100,0),
                        new DiceRule(3,6,100,0),
                        null,
                        new DiceRule(2,6,10,0),
                        null);
            case MID:
                return generateCurrency(new DiceRule(2,6,100,0),
                        new DiceRule(2,6,1000,0),
                        null,
                        new DiceRule(6,6,100,0),
                        new DiceRule(3,6,10,0));
            case LATE:
                return generateCurrency(null,
                        null,
                        null,
                        new DiceRule(4,6,1000,0),
                        new DiceRule(5,6,100,0));
            case END:
                return generateCurrency(null,
                        null,
                        null,
                        new DiceRule(12,6,1000,0),
                        new DiceRule(8,6,1000,0));
            default:
                return generateCurrency(null,null,null,null,null);

        }
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
    public DndTreasure createTreasure(DndEncounterType encounterType){
        DndTreasure treasure = new DndTreasure();
        treasure.setInfo("Treasure generated for " + encounterType.toString() + " game.");
        treasure.setCurrency(generateCurrencyForEncounterType(encounterType));

        List<DndHoardTable> hoardTables = getRandomHoardTableResults(encounterType);
        for(DndHoardTable hTable:hoardTables){
            DiceRule valuableDice = hTable.getValuablesDice();
            DiceRule itemDice = hTable.getItemsDice();
        }

        return treasure;
    }

    @Transactional
    public DndTreasure testCreateTreasure(int roll, DndEncounterType encounterType){
        DndTreasure treasure = new DndTreasure();
        treasure.setInfo("Treasure generated for " + encounterType.toString() + " game.");
        treasure.setCurrency(generateCurrencyForEncounterType(encounterType));

        List<DndHoardTable> hoardTables = getHoardTableResults(roll,encounterType);
        for(DndHoardTable hTable:hoardTables){
            DiceRule valuableDice = hTable.getValuablesDice();
            log.info(valuableDice != null ? valuableDice.toString() : null);
            DiceRule itemDice = hTable.getItemsDice();
            log.info(itemDice != null ? itemDice.toString() : null);

            if(itemDice != null){
//                treasure.addItemsToList(dndItemTablesService.getRandomItemsFromTable(itemDice.generateValue(),hTable.getItemTableType()));
                treasure.addItemsToList(dndItemTablesService.getRandomItemsFromTableAsWrappers(itemDice.generateValue(),hTable.getItemTableType()));
            }

            if(valuableDice != null){
//                treasure.addValuablesToList(dndItemTablesService.getRandomItemsFromTable(valuableDice.generateValue(),hTable.getValuableTable()));
                treasure.addValuablesToList(dndItemTablesService.getRandomItemsFromTableAsWrappers(valuableDice.generateValue(),hTable.getValuableTable()));
            }
        }

        return treasure;
    }

    @Transactional
    public List<DndTreasure> createTreasures(int number, DndEncounterType encounterType){
        List<DndTreasure> treasures = new ArrayList<>(number);
        Random random = new Random();

        for(int i = 0; i < number; i++){
            DndTreasure treasure = new DndTreasure();
            treasure.setInfo("Treasure generated for " + encounterType.toString() + " game.");
            treasure.setCurrency(generateCurrencyForEncounterType(encounterType));

            List<DndHoardTable> hoardTables = getHoardTableResults(random.nextInt(100)+1,encounterType);
            for(DndHoardTable hTable:hoardTables){
                DiceRule valuableDice = hTable.getValuablesDice();
                DiceRule itemDice = hTable.getItemsDice();

                if(itemDice != null){
//                    treasure.addItemsToList(dndItemTablesService.getRandomItemsFromTable(itemDice.generateValue(),hTable.getItemTableType()));
                    treasure.addItemsToList(dndItemTablesService.getRandomItemsFromTableAsWrappers(itemDice.generateValue(),hTable.getItemTableType()));
                }

                if(valuableDice != null){
//                    treasure.addValuablesToList(dndItemTablesService.getRandomItemsFromTable(valuableDice.generateValue(),hTable.getValuableTable()));
                    treasure.addValuablesToList(dndItemTablesService.getRandomItemsFromTableAsWrappers(valuableDice.generateValue(),hTable.getValuableTable()));
                }
            }

            treasures.add(treasure);
        }

        return treasures;
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
