package com.cs.demo.control;

import com.cs.demo.entity.ImageCode;
import com.cs.demo.entity.SmsCode;
import com.cs.demo.utils.CreateImageCode;
import com.cs.demo.utils.GetString;
import com.cs.demo.utils.JsonResult;
import com.zhenzi.sms.ZhenziSmsClient;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/8 12:52
 */
@RestController
public class CodeController {

    private static final String KEY = "code";

    @ApiOperation("图形验证码")
    @GetMapping(value = "/getCode",produces = "image/jpeg")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException{

        ImageCode imageCode = CreateImageCode.createImagecode();
        HttpSession session = request.getSession();
        session.setAttribute(KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    @ApiOperation("短信验证码")
    @PostMapping("/sendMsg")
    public JsonResult sendMsg(HttpServletRequest request, String phone) throws Exception{
        System.out.println(phone);
        //榛子短信的SDK
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "101348", "ZGZmNjM3MWYtZDVjMS00YWUyLWE4NmUtZDI5NjNmOGRjNTA1");
        String code = GetString.getCode();
        String result = client.send(phone, "您的验证码为" + code + "\n" + "如果不是本人操作，请忽略。");
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (jsonObject.getInt("code") != 0){
            return JsonResult.errorMsg("验证码发送失败");
        }

        return JsonResult.ok(code);
    }
}