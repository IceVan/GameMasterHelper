package pl.ice.GameMasterHelper.dao.dnd;

import org.springframework.transaction.annotation.Transactional;
import pl.ice.GameMasterHelper.model.dnd.DnDItem;

import java.util.List;

public interface DnDItemDao {

    List<DnDItem> getList();

    DnDItem getItemById(Long id);
}
