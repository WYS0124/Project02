package cn.kgc.service;

import cn.kgc.domain.District;
import cn.kgc.util.PageUtils;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DistrictService {

    /**
     * 查询区域分页
     * @param pageUtils
     * page属性接收页码，rows属性接收页大小
     * @return
     */
    PageInfo<District> getDistrictByPage(PageUtils pageUtils);

    /**
     * 添加区域功能
     * @param district  //传入参数
     * @return
     */
    Integer insertDistrict(District district);

    /**
     * 根据id查询区域数据
     * 修改回显
     * @param id
     * @return
     */
    District findDistrictById(Integer id);

    /**
     * 修改区域功能
     * @param district
     * @return
     */
    Integer updateDistrict(District district);

    /**
     * 根据id删除区域及该区域下的街道
     * @param id
     */
    void deleteDistrict(Integer id);

    /**
     * 批量删除区域
     * @param ids
     * @return
     */
    int deleteMoreDistrict(Integer [] ids);

    /**
     * 查询所有的区域
     * @return
     */
    List<District> getAllDistrict();
}
