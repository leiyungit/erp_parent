package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.biz.utils.DataCrypto;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.dao.IRoleDao;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Role;
import cn.itcast.erp.entity.Tree;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class EmpBiz extends BaseBiz<Emp> implements IEmpBiz {

	private IEmpDao empDao;
	private IRoleDao roleDao;
	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(this.empDao);
	}

    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
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

    /**
     * 读取角色组
     *
     * @param empUuid 员工Id
     * @return
     */
    @Override
    public List<Tree> readEmpRoles(Long empUuid) {
        List<Tree> treeList = new ArrayList<>();
        Emp emp = this.empDao.get(empUuid);
        // 获取用户下的角色列表
        List<Role> roleList = new ArrayList<>();
        if(null != emp){
            roleList = emp.getRoles();
            // System.out.println("角色组size："+roleList.size()+", empUuid:"+empUuid);
        }
        List<Role> roles = this.roleDao.getList(null, null, null);
        Tree t1 = null;
        for (Role role : roles) {
            t1 = new Tree();
            t1.setText(role.getName());
            t1.setId(String.valueOf(role.getUuid()));
            if(roleList.contains(role)){
                t1.setChecked(true);
            }
            treeList.add(t1);
        }
        return treeList;
    }

    /**
     * 更新角色组
     *
     * @param empUuid    员工Id
     * @param checkedStr 角色Id,多个值用逗号连接，如 1,2,3
     */
    @Override
    public void updateEmpRoles(Long empUuid, String checkedStr) {
        if(null == empUuid){
            throw new ErpException("请先选择员工");
        }
        Emp emp = this.empDao.get(empUuid);
        if(null == emp){
            throw new ErpException("员工未定义！");
        }
        // 清除原来的数据
        emp.setRoles(new ArrayList<>());
        if(StringUtils.isBlank(checkedStr)){
            return;
        }
        Role t1 = null;
        String[] idArr = checkedStr.split(",");
        for (String id : idArr) {
            t1 = this.roleDao.get(Long.valueOf(id));
            emp.getRoles().add(t1);
        }
    }

}
