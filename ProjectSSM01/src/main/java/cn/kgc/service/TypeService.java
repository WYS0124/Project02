package cn.kgc.service;

import cn.kgc.domain.Type;
import cn.kgc.util.PageUtils;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {

    /**
     * 查询区域分页
     * @param pageUtils
     * page属性接收页码，rows属性接收页大小
     * @return
     */
    PageInfo<Type> getTypeByPage(PageUtils pageUtils);

    /**
     * 添加区域功能
     * @param type  //传入参数
     * @return
     */
    Integer insertType(Type type);

    /**
     * 根据id查询区域数据
     * 修改回显
     * @param id
     * @return
     */
    Type findTypeById(Integer id);

    /**
     * 修改区域功能
     * @param type
     * @return
     */
    Integer updateType(Type type);

    /**
     * 根据id删除区域及该区域下的街道
     * @param id
     */
    void deleteType(Integer id);

    /**
     * 批量删除区域
     * @param ids
     * @return
     */
    int deleteMoreType(Integer[] ids);

    /**
     * 查询所有的类型
     * @return
     */
    public List<Type> getAllType();
}
