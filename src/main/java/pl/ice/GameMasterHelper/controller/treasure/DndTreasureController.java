package pl.ice.GameMasterHelper.controller.treasure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dnd/treasure")
public class DndTreasureController {

    @GetMapping("/test")
    public String testController(){
        return new String("test");
    }
}
