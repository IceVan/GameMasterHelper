package pl.ice.GameMasterHelper.dao.dnd;

import org.springframework.stereotype.Component;
import pl.ice.GameMasterHelper.dao.GenericDao;
import pl.ice.GameMasterHelper.model.dnd.DndEncounterType;
import pl.ice.GameMasterHelper.model.dnd.DndHoardTable;

import org.hibernate.query.Query;

import java.util.List;

@Component
public class DndHoardTableDaoImpl extends GenericDao<DndHoardTable> implements DndHoardTableDao{

    @Override
    public List<DndHoardTable> getResultForRoll(int roll, DndEncounterType encounterType) {
        Query query = getCurrentSession().createQuery("FROM pl.ice.GameMasterHelper.model.dnd.DndHoardTable WHERE :roll BETWEEN rangeFrom AND rangeTo AND encounterType = :encounterType",DndHoardTable.class);
        query.setParameter("roll", roll);
        query.setParameter("encounterType", encounterType);
        return query.list();
    }
}
