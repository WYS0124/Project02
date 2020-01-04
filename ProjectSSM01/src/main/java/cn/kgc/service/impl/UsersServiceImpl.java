package cn.kgc.service.impl;

import cn.kgc.domain.Users;
import cn.kgc.domain.UsersExample;
import cn.kgc.mapper.UsersMapper;
import cn.kgc.service.UsersService;
import cn.kgc.util.MD5Utils;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     *
     * @param condition
     * page属性接收页码，rows属性接收页大小
     * @return
     */
    @Override
    public PageInfo<Users> getUsersByPage(UserCondition condition) {
        //开启分页查询
        PageHelper.startPage(condition.getPage(),condition.getRows());
        //查询数据
        UsersExample usersExample =new UsersExample();
        //封装条件
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if (condition.getName() != null){
            criteria.andNameLike("%"+condition.getName()+"%");
        }
        if (condition.getTel() != null){
            criteria.andTelephoneLike("%"+condition.getTel()+"%");
        }
        List<Users> list = usersMapper.selectByExample(usersExample);
        return new PageInfo<Users>(list);
    }

    /**
     * 注册查询用户是否存在
     * @param name
     * @return  布尔值
     */
    @Override
    public boolean findUname(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(new Integer("0")); //保证是房东用户
        criteria.andNameEqualTo(name);

        List<Users> list = usersMapper.selectByExample(usersExample);
        if (list != null && list.size() != 0){
            return false;
        }else
        return true;
    }

    /**
     * 房东用户注册
     * @param users
     * @return
     */
    @Override
    public int addUsers(Users users) {
        //出于系统用户安全考虑_对密码进行加密码
        //使用MD5工具类对密码加密
        String newPassword = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(newPassword);
        return usersMapper.insertSelective(users);
    }

    /**
     * 登录功能
     * @param name
     * @param password
     * @return 返回用户信息
     */
    @Override
    public Users login(String name, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(new Integer("0"));
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));     //密码加密
        List<Users> list = usersMapper.selectByExample(usersExample);
        if (list != null && list.size() > 0 ){
            return list.get(0);     //返回登入用户的信息
        }
        return null;
    }
}
