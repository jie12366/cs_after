package com.cs.demo.control;

import com.cs.demo.entity.User;
import com.cs.demo.service.impl.UserServiceImpl;
import com.cs.demo.utils.GetString;
import com.cs.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/22 13:19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public JsonResult regi(HttpServletRequest request,
                           @ApiParam("用户名（邮箱）")@RequestParam("userName") String userName,
                           @ApiParam("密码")@RequestParam("password") String password,
                           @ApiParam("手机号")@RequestParam("phone") String phone,
                           @ApiParam("用户角色")@RequestParam("role") String role)throws Exception{
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhone(phone);

        userService.saveUser(user);
        String role1 = "ROLE_STUDENT";
        String role2 = "ROLE_MARCHANT";
        int userId = userService.getIdByName(user.getUserName()).getId();
        if (role.equals(role1)){
            userService.saveUserRole(userId,1);
        }else if (role.equals(role2)){
            userService.saveUserRole(userId,2);
        }
        return JsonResult.ok("注册成功");
    }

    @ApiOperation("判断用户名已经被注册")
    @PostMapping("/isRegister")
    public JsonResult isRegister(@ApiParam("用户名（邮箱）")@RequestParam("userName") String userName){
        User user = userService.getIdByName(userName);
        if (user == null){
            return JsonResult.ok("ok");
        }else {
            return JsonResult.ok("no");
        }
    }

    @ApiOperation("发送重置密码的验证码邮件")
    @PostMapping("/sendEmail")
    public JsonResult sendEmail(@ApiParam("用户名（邮箱）")@RequestParam("userName") String userName,HttpServletRequest request){
        String theme = "重置密码";
        String code = GetString.getCode();
        String content = "亲，您的重置密码的验证码为 " + code + "如果不是本人操作，请忽略！";
        JSONObject object = new JSONObject();
        object.put("code",code);
        object.put("time",System.currentTimeMillis());
        HttpSession session = request.getSession();
        session.setAttribute("emailCode",object);
        userService.sendEmail(theme,content,userName);
        return JsonResult.ok();
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public JsonResult resetPassword(@ApiParam("用户名（邮箱）")@RequestParam("userName") String userName,
                                    @ApiParam("密码")@RequestParam("password") String password,
                                    @ApiParam("验证码")@RequestParam("code") String code,
                                    HttpServletRequest request){

        JSONObject object = (JSONObject) request.getSession().getAttribute("emailCode");

        //验证码过期时间为五分钟
        if ((System.currentTimeMillis() - object.getLong("time")) > 1000 * 300){
            return JsonResult.errorMsg("验证码已过期");
        }
        else if (!object.getString("code").equals(code)){
            return JsonResult.errorMsg("验证码错误！");
        }
        int res =  userService.updateUser(password,userName);
        return JsonResult.ok(res);
    }
}