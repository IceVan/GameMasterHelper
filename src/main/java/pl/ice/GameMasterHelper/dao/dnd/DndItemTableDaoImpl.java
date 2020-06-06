package pl.ice.GameMasterHelper.dao.dnd;

import org.hibernate.loader.custom.sql.SQLCustomQuery;
import org.hibernate.query.NativeQuery;
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
        Query query = getCurrentSession().createQuery("FROM pl.ice.GameMasterHelper.model.dnd.DndItemTable WHERE :roll BETWEEN rangeFrom AND rangeTo AND dndItemTableType = :dndItemTableType", DndItemTable.class);
        query.setParameter("roll", roll);
        query.setParameter("dndItemTableType", dndItemTableType);
        return query.list();
    }

    @Override
    public List<DndItemTable> getRandomResultInTable(DndItemTableType dndItemTableType) {
        Random random = new Random();
        Query query = getCurrentSession().createQuery("FROM pl.ice.GameMasterHelper.model.dnd.DndItemTable WHERE :roll BETWEEN rangeFrom AND rangeTo AND dndItemTableType = :dndItemTableType", DndItemTable.class);
        query.setParameter("roll", random.nextInt(100)+1);
        query.setParameter("dndItemTableType", dndItemTableType);
        return query.list();
    }

    /*public List<DndItemTable> getRandomResultInTableWithUnion(int amount, DndItemTableType dndItemTableType) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder("");
        sb.append("SELECT * FROM tdd_")

        NativeQuery query = getCurrentSession().createNativeQuery("");




        query.setParameter("dndItemTableType", dndItemTableType);
        return query.list();
    }*/
}
