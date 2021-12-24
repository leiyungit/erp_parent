package cn.itcast.erp.action;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.entity.Dep;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseAction<T> {

    private IBaseBiz<T> baseBiz;

    public void setBaseBiz(IBaseBiz baseBiz) {
        this.baseBiz = baseBiz;
    }


    // 查询条件
    private T t1;
    private T t2;
    private Object param;
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

    public T getT1() {
        return t1;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }

    public T getT2() {
        return t2;
    }

    public void setT2(T t2) {
        this.t2 = t2;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public void list() {
        List<T> list = baseBiz.getList(t1, t2, param);
        String stringList = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
        ResultUtil.write(stringList);
    }

    // http://localhost:8080/erp/dep_getList?dep1.name=%E9%83%A8&dep1.tele=5
    public void listByPage() {
        System.out.println("page: " + page + "   ,rows: " + rows);
        List<T> list = baseBiz.listByPage(t1, t2, param, (page - 1) * rows, rows);
        Map mapData = new HashMap<String, Object>();
        mapData.put("total", this.baseBiz.getCount(t1, t2, param));
        mapData.put("rows", list);
        String stringList = JSON.toJSONString(mapData, SerializerFeature.DisableCircularReferenceDetect);
        ResultUtil.write(stringList);
    }

    /**
     * 新增 对象
     */
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void add() {
        baseBiz.add(t);
        Map map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("message", "新增成功");
        String stringList = JSON.toJSONString(map);
        ResultUtil.write(stringList);
    }

    /**
     * 删除 主键
     */
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void delete() {
        System.out.println("delete=>id:" + id);
        try {
            baseBiz.delete(id);
            ResultUtil.ajaxReturn(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.ajaxReturn(false, "删除失败");
        }
    }

    public void get() {
        System.out.println("get=>id:" + id);
        T t = baseBiz.get(id);
        String stringList = JSON.toJSONStringWithDateFormat(t,"yyyy-MM-dd");
        String jsonString = mapData(stringList, "t");
        ResultUtil.write(jsonString);
    }

    public void update() {
        //System.out.println(t);
        try {
            baseBiz.update(t);
            ResultUtil.ajaxReturn(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.ajaxReturn(false, "修改失败");
        }
    }

    public Emp getLoginUser(){
        return (Emp) ActionContext.getContext().getSession().get("login");
    }

    /**
     * //{"name":"管理员组","tele":"000011","uuid":1}
     *
     * @param jsonString JSON数据字符串
     * @param prefix     要加上的前缀
     * @return {"t.name":"管理员组","t.tele":"000011","t.uuid":1}
     */
    public String mapData(String jsonString, String prefix) {
        Map<String, Object> map = JSON.parseObject(jsonString);
        Map<String, Object> result = new HashMap<>();
        for (String key : map.keySet()) {
            if(map.get(key) instanceof Map){
                System.out.println("========mapData");
                Map<String,Object> map2 = (Map)map.get(key);
                for (String key2 : map2.keySet()) {
                    result.put(prefix + "." + key+"."+key2, map2.get(key2));
                }
                System.out.println(result);
            }
            else{
                result.put(prefix + "." + key, map.get(key));
            }

        }
        return JSON.toJSONString(result);
    }

    /*public void ajaxReturn(boolean success, String message) {
        //返回前端的JSON数据
        Map<String, Object> rtn = new HashMap<String, Object>();
        rtn.put("success", success);
        rtn.put("message", message);
        write(JSON.toJSONString(rtn));
    }

    public void write(String jsonString) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
