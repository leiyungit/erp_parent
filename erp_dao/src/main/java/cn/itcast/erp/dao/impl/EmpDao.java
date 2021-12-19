package cn.itcast.erp.dao.impl;
import cn.itcast.erp.dao.IUserDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.entity.Emp;

import java.util.List;

/**
 * 数据访问类
 * @author Administrator
 *
 */
public class EmpDao extends BaseDao<Emp> implements IEmpDao {

	
	/**
	 * 构建查询条件
	 * @param emp1
	 * @param emp2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Emp emp1,Emp emp2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Emp.class);
		if(emp1!=null){
			if(emp1.getUsername()!=null &&  emp1.getUsername().trim().length()>0)
			{
				dc.add(Restrictions.like("username", emp1.getUsername(), MatchMode.ANYWHERE));			
			}
			if(emp1.getPwd()!=null &&  emp1.getPwd().trim().length()>0)
			{
				dc.add(Restrictions.like("pwd", emp1.getPwd(), MatchMode.ANYWHERE));			
			}
			if(emp1.getName()!=null &&  emp1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", emp1.getName(), MatchMode.ANYWHERE));			
			}
			if(emp1.getEmail()!=null &&  emp1.getEmail().trim().length()>0)
			{
				dc.add(Restrictions.like("email", emp1.getEmail(), MatchMode.ANYWHERE));			
			}
			if(emp1.getTele()!=null &&  emp1.getTele().trim().length()>0)
			{
				dc.add(Restrictions.like("tele", emp1.getTele(), MatchMode.ANYWHERE));			
			}
			if(emp1.getAddress()!=null &&  emp1.getAddress().trim().length()>0)
			{
				dc.add(Restrictions.like("address", emp1.getAddress(), MatchMode.ANYWHERE));			
			}
            if(emp1.getGender()!= null)
            {
                dc.add(Restrictions.eq("gender", emp1.getGender()));
            }
            if(emp1.getDep()!= null && emp1.getDep().getUuid() != null)
            {
                dc.add(Restrictions.eq("dep", emp1.getDep()));
            }
            if(null != emp1.getBirthday()){
            	dc.add(Restrictions.ge("birthday", emp1.getBirthday()));
			}
		}
		if(null != emp2){
			if(null != emp2.getBirthday()){
				dc.add(Restrictions.le("birthday", emp2.getBirthday()));
			}
		}
		return dc;
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username
	 * @return
	 */
	@Override
	public Emp findByUsername(String username) {
		List<Emp> list = (List<Emp>) this.getHibernateTemplate().find("from Emp where username=?", username);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}

