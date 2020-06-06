package pl.ice.GameMasterHelper.controller.treasure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ice.GameMasterHelper.model.dnd.DndEncounterType;
import pl.ice.GameMasterHelper.model.dnd.DndTreasure;
import pl.ice.GameMasterHelper.model.response.DndTreasureResponse;
import pl.ice.GameMasterHelper.service.dnd.DndTreasureService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dnd/treasure")
public class DndTreasureController {

    private DndTreasureService dndTreasureService;

    @Autowired
    public DndTreasureController(DndTreasureService dndTreasureService) {
        this.dndTreasureService = dndTreasureService;
    }

    @GetMapping("/test")
    public DndTreasureResponse testController(@RequestParam(name = "roll") int roll){
        DndTreasureResponse treasureResponse = new DndTreasureResponse();
        List<DndTreasure> list = new ArrayList<>(1);
        list.add(dndTreasureService.testCreateTreasure(roll, DndEncounterType.EARLY));
        treasureResponse.setDndTreasure(list);
        treasureResponse.setStatus(treasureResponse.getDndTreasure().isEmpty() ? "ERROR" : "OK");
        return treasureResponse;
    }

    @GetMapping("/getTreasure")
    public DndTreasureResponse getTreasure(@RequestParam(name = "numberOfTreasures") int number, @RequestParam(name="encounterType") DndEncounterType encounterType){
        DndTreasureResponse treasureResponse = new DndTreasureResponse();

        treasureResponse.setDndTreasure(dndTreasureService.createTreasures(number, encounterType));
        treasureResponse.setStatus(treasureResponse.getDndTreasure().isEmpty() ? "ERROR" : "OK");

        return treasureResponse;
    }
}
