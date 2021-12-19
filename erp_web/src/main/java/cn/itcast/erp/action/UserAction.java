package cn.itcast.erp.action;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.biz.IUserBiz;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.User;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;

public class UserAction extends BaseAction<User> {

    private IUserBiz userBiz;

    private IEmpBiz empBiz;


    public void setUserBiz(IUserBiz userBiz) {
        this.userBiz = userBiz;
        super.setBaseBiz(userBiz);
    }

    public void setEmpBiz(IEmpBiz empBiz) {
        this.empBiz = empBiz;
        setBaseBiz(empBiz);
    }
    private String username;//登陆名
    private String pwd;//登陆密码
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void signIn(){
        try {
            System.out.println("signIn");
            boolean isLogin = userBiz.signIn(username, pwd);
            System.out.println(isLogin);
            if(isLogin) {
                Emp emp = this.empBiz.findByUsername(username);
                System.out.println(emp);
                if(null == emp){
                    ResultUtil.ajaxReturn(false,"登录失败，用户不存在");
                    return;
                }
                // 保存到session中，表示用户已经登录了
                ActionContext.getContext().getSession().put("login",emp);
                //String stringList = JSON.toJSONString(emp);
                // ResultUtil.write(stringList);
                ResultUtil.ajaxReturnSuccess("");
            }else{
                ResultUtil.ajaxReturn(false,"用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.ajaxReturn(false,"登录失败");
        }

    }

    public void showName(){
        Emp emp = (Emp)ActionContext.getContext().getSession().get("login");
        if(null != emp){
            ResultUtil.ajaxReturnSuccess(emp.getName());
        }else{
            ResultUtil.ajaxReturnFail("");
        }
    }

    public void loginOut(){
        ActionContext.getContext().getSession().remove("login");
    }
}
