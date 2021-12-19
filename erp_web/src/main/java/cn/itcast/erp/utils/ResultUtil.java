package cn.itcast.erp.utils;

import com.alibaba.fastjson.JSON;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultUtil {


    public static void ajaxReturn(boolean success, String message) {
        //返回前端的JSON数据
        Map<String, Object> rtn = new HashMap<String, Object>();
        rtn.put("success", success);
        rtn.put("message", message);
        write(JSON.toJSONString(rtn));
    }

    public static void ajaxReturnSuccess(String message){
        ajaxReturn(true,message);
    }

    /**失败返回*/
    public static void ajaxReturnFail(String message){
        ajaxReturn(false,message);
    }


    public static void write(String jsonString) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
