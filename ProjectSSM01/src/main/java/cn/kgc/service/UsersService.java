package cn.kgc.service;

import cn.kgc.domain.Users;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageInfo;

public interface UsersService {

    /**
     * 查询区域分页
     * @param condition
     * page属性接收页码，rows属性接收页大小
     * @return
     */
    PageInfo<Users> getUsersByPage(UserCondition condition);

    /**
     * 注册查询用户是否存在
     * @param name
     * @return
     */
    public boolean findUname(String name);

    /**
     * 房东用户注册
     * @param users
     * @return
     */
    public int addUsers(Users users);

    /**
     * 登录功能
     * @param name
     * @param password
     * @return
     */
    public Users login(String name,String password);
}
