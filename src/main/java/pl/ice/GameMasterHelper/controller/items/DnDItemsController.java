package pl.ice.GameMasterHelper.controller.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ice.GameMasterHelper.model.DiceRule;
import pl.ice.GameMasterHelper.model.dnd.*;
import pl.ice.GameMasterHelper.service.dnd.DndTreasureService;

import java.util.List;

@RestController
@RequestMapping("/dnd/items")
public class DnDItemsController {

    @Autowired
    private DndTreasureService dndTreasureService;
    @Autowired
    private DndItemTableRepository dndItemTableRepository;
    @Autowired
    private DndHoardTableRepository dndHoardTableRepository;

    @GetMapping("/test")
    public DndCurrency testController(){
        return dndTreasureService.generateCurrency(
                new DiceRule(6,6,100,0),
                new DiceRule(3,6,100,0),
                null,
                new DiceRule(2,6,10,0),
                null);
    }

    @GetMapping("/testParser")
    public DndCurrency testParse(@RequestParam(value = "rule", defaultValue = "") String rule){
        return dndTreasureService.generateCurrency(
                new DiceRule("6d6x100p0"),
                new DiceRule("3d6x100p0"),
                null,
                new DiceRule("2d6x10p0"),
                null);
    }

    @GetMapping("/generateCurrency")
    public DndCurrency generateValuables(){
        return dndTreasureService.generateCurrency();
    }

    @GetMapping("/randomTreasureRow")
    public List<DndHoardTable> generateRandomRowFromHoardTable(){
        return dndTreasureService.getRandomHoardTableResults(DndEncounterType.EARLY);
    }

    @PostMapping("/createItem")
    public String createItem(){

        DnDItem dnDItem = new DnDItem();

        return null;
    }
}
