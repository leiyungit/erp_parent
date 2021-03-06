package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IRoleDao;
import cn.itcast.erp.entity.Role;
/**
 * 数据访问类
 * @author Administrator
 *
 */
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	
	/**
	 * 构建查询条件
	 * @param role1
	 * @param role2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Role role1,Role role2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Role.class);
		if(role1!=null){
			if(role1.getName()!=null &&  role1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", role1.getName(), MatchMode.ANYWHERE));			
			}
		
		}		
		return dc;
	}
	
	
}

