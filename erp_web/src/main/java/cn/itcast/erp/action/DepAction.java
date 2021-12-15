package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.entity.Dep;

public class DepAction {

	private IDepBiz depBiz;
    // 查询条件
    private Dep dep1;
	// 分页，第几页
	private int page;
	// 分页，记录数
    private int rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setDepBiz(IDepBiz depBiz) {
        this.depBiz = depBiz;
    }

    public Dep getDep1() {
        return dep1;
    }

    public void setDep1(Dep dep1) {
        this.dep1 = dep1;
    }

	public void list() {
        System.out.println("===========list");
		List<Dep> list = depBiz.getList();
		String stringList = JSON.toJSONString(list);
        write(stringList);
	}

	// http://localhost:8080/erp/dep_getList?dep1.name=%E9%83%A8&dep1.tele=5
    public void getList() {
        System.out.println("page: "+page+ "   ,rows: "+rows);
        List<Dep> list = depBiz.getList(dep1,(page -1) * rows,rows);
        Map map = new HashMap<String,Object>();
        map.put("total",this.depBiz.getCount(dep1));
        map.put("rows",list);
        String stringList = JSON.toJSONString(map);
        write(stringList);
    }

    /**新增 对象*/
    private Dep dep;
    public Dep getDep() {
        return dep;
    }
    public void setDep(Dep dep) {
        this.dep = dep;
    }

    public void add(){
        depBiz.add(dep);
        Map map = new HashMap<String,Object>();
        map.put("success",true);
        map.put("message","新增部门成功");
        String stringList = JSON.toJSONString(map);
        write(stringList);
    }

	public void write(String jsonString){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
