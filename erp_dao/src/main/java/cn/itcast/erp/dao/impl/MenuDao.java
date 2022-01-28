package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IMenuDao;
import cn.itcast.erp.entity.Menu;

import java.util.List;

/**
 * 数据访问类
 * @author Administrator
 *
 */
public class MenuDao extends BaseDao<Menu> implements IMenuDao {

	
	/**
	 * 构建查询条件
	 * @param menu1
	 * @param menu2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Menu menu1,Menu menu2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Menu.class);
		if(null != menu1){
		    dc.add(Restrictions.eq("menuid",menu1.getMenuid()));
        }
		return dc;
	}


    /**
     * 根据员工编号获取菜单
     *
     * @param empUuid 员工编号
     * @return
     */
    @Override
    public List<Menu> getMenusByEmpuuid(Long empUuid) {
        String hql = "select m from Emp e join e.roles r join r.menus m where e.uuid=?";
        List<Menu> menus = (List<Menu>)this.getHibernateTemplate().find(hql,empUuid);
        return menus;
    }
}

