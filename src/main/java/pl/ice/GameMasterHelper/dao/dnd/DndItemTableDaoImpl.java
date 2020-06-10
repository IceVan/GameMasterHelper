package pl.ice.GameMasterHelper.dao.dnd;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import pl.ice.GameMasterHelper.dao.GenericDao;
import pl.ice.GameMasterHelper.model.dnd.DndItemTable;
import pl.ice.GameMasterHelper.model.dnd.DndItemTableType;

import java.util.List;
import java.util.Random;

@Component
public class DndItemTableDaoImpl extends GenericDao<DndItemTable> implements DndItemTableDao {

    @Override
    public List<DndItemTable> getResultForRollInTable(int roll, DndItemTableType dndItemTableType) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.GameMasterHelper.model.dnd.DndItemTable dit inner join fetch dit.item WHERE :roll BETWEEN dit.rangeFrom AND dit.rangeTo AND dit.dndItemTableType = :dndItemTableType", DndItemTable.class);
        query.setParameter("roll", roll);
        query.setParameter("dndItemTableType", dndItemTableType);
        return query.list();
    }

    @Override
    public List<DndItemTable> getRandomResultInTable(DndItemTableType dndItemTableType) {
        Random random = new Random();
        Query query = getCurrentSession().createQuery("FROM pl.ice.GameMasterHelper.model.dnd.DndItemTable dit inner join fetch dit.item WHERE :roll BETWEEN dit.rangeFrom AND dit.rangeTo AND dit.dndItemTableType = :dndItemTableType", DndItemTable.class);
        query.setParameter("roll", random.nextInt(100)+1);
        query.setParameter("dndItemTableType", dndItemTableType);
        return query.list();
    }

    @Override
    public List<DndItemTable> getRowsForItemTableType(DndItemTableType dndItemTableType) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.GameMasterHelper.model.dnd.DndItemTable dit inner join fetch dit.item WHERE dit.dndItemTableType = :dndItemTableType", DndItemTable.class);
        query.setParameter("dndItemTableType", dndItemTableType);
        return query.list();
    }
}
