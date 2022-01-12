package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.biz.utils.DataCrypto;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.entity.Emp;

import java.util.Map;

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

    /**
     * 根据员工编号获取员工名称
     * @param empUuid
     * @param empMap
     * @return
     */
    public String getEmpName(Long empUuid, Map<Long,String> empMap){
        if(empUuid == null){
            return null;
        }
        if(!empMap.containsKey(empUuid)){
            //System.out.print("=================key不存在，查询数据库"+empUuid);
            String name = this.empDao.get(empUuid).getName();
            //System.out.println(", name:"+name);
            empMap.put(empUuid, name);
            return name;
        }
        return empMap.get(empUuid);

    }

}
