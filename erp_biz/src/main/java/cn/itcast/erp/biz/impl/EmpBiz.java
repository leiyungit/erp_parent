package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.biz.utils.DataCrypto;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.entity.Emp;
/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class EmpBiz extends BaseBiz<Emp> implements IEmpBiz {

	private IEmpDao empDao;
	
	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(this.empDao);
	}


	@Override
	public Emp findByUsername(String username) {
		return empDao.findByUsername(username);
	}

	/**
	 * 新增员工时初始化密码
	 * @param emp
	 */
	@Override
	public void add(Emp emp){
		emp.setPwd(DataCrypto.encrypt(emp.getUsername(),emp.getUsername()));
		super.add(emp);
	}
}
