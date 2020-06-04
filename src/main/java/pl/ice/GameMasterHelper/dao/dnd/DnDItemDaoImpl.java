package pl.ice.GameMasterHelper.dao.dnd;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.ice.GameMasterHelper.dao.GenericDao;
import pl.ice.GameMasterHelper.model.dnd.DnDItem;

import java.util.List;

@Component
public class DnDItemDaoImpl extends GenericDao<DnDItem> implements DnDItemDao {

    @Override
    public List<DnDItem> getList() {
        Query query = getCurrentSession().createQuery("FROM DnDItem");
        return query.list();
    }

    @Override
    public DnDItem getItemById(Long id) {
        Query query = getCurrentSession().createQuery("FROM DnDItem WHERE id = :id");
        query.setParameter("id", id);
        return (DnDItem)query.getSingleResult();
    }
}
