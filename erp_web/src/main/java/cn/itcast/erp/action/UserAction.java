package cn.itcast.erp.action;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.biz.IUserBiz;
import cn.itcast.erp.biz.exception.ERPException;
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
        Emp emp = getLoginUser();
        if(null != emp){
            ResultUtil.ajaxReturnSuccess(emp.getName());
        }else{
            ResultUtil.ajaxReturnFail("");
        }
    }

    public void loginOut(){
        ActionContext.getContext().getSession().remove("login");
    }

    private String oldPwd; // 新密码
    private String newPwd; // 旧密码
    public String getOldPwd() {
        return oldPwd;
    }
    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
    public String getNewPwd() {
        return newPwd;
    }
    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    /**
     * 修改密码
     */
    public void updatePwd(){
        Emp loginUser = getLoginUser();
        if(null == loginUser){
            ResultUtil.ajaxReturnFail("亲，您还没有登录");
            return;
        }
        try {
            boolean result = this.userBiz.updatePwd(loginUser.getUsername(), oldPwd, newPwd);
            if(result){
                ResultUtil.ajaxReturnSuccess("修改密码成功");
            }else{
                ResultUtil.ajaxReturnFail("修改密码失败");
            }
        } catch (ERPException ex){
            ex.printStackTrace();
            ResultUtil.ajaxReturnFail(ex.getMessage());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 重置密码
     */
    public void updatePwd_reset(){
        try {
            boolean result = this.userBiz.updatePwd_reset(username, newPwd);
            if(result){
                ResultUtil.ajaxReturnSuccess("重置密码成功");
            }else{
                ResultUtil.ajaxReturnFail("重置密码失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.ajaxReturnFail("重置密码失败");
        }

    }

}
