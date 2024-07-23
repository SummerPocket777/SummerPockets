package com.sp.controller;

import com.sp.pojo.Result;
import com.sp.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    /**
     * 登陆
     * @param user 提交的用户数据，包含用户名和密码
     * @param session
     * @return
     */
    @PostMapping("/login")
    @CrossOrigin
    public Result login(@RequestBody User user, HttpSession session) {
        Result result = new Result();
        if(user != null && "123".equals(user.getPassword())) {
            result.setFlag(true);
            //将数据存储到session对象中
            session.setAttribute("user",user.getUsername());
        } else {
            result.setFlag(false);
            result.setMessage("登陆失败");
        }
        return result;
    }

    /**
     * 获取用户名
     * @param session
     * @return
     */
    @GetMapping("/getUsername")
    @CrossOrigin
    public String getUsername(HttpSession session) {

        String username = (String) session.getAttribute("user");
        return username;
    }
}
