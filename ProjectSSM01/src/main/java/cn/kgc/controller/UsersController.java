package cn.kgc.controller;

import cn.kgc.domain.Users;
import cn.kgc.service.UsersService;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")  //指定请求前缀
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * 查询区域分页
     * @param condition
     * @return
     */
    @RequestMapping("getUsersData")
    public Map<String,Object> getUsersData(UserCondition condition){
        PageInfo<Users> pageInfo = usersService.getUsersByPage(condition);
        Map<String,Object> map = new HashMap <String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
