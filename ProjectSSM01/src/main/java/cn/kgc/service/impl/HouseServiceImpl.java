package cn.kgc.service.impl;

import cn.kgc.domain.House;
import cn.kgc.mapper.HouseMapper;
import cn.kgc.service.HouseService;
import cn.kgc.util.PageUtils;
import cn.kgc.util.SearchCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "HouseServiceImpl2")
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    /**
     * 发布房屋信息
     * @param house 出租房信息
     * @return
     */
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    /**
     * 查询用户发布的出租房信息
     * @param id    用户编号
     * @return
     */
    @Override
    public PageInfo<House> getHouseByUser(Integer id, PageUtils pageUtils) {
        //开启分页查询
        PageHelper.startPage(pageUtils.getPage(),pageUtils.getRows());
        List<House> list = houseMapper.getHouseUser(id);
        return new PageInfo<House>(list);
    }

    /**
     *查询某出租房的信息
     * @param id    房子的id
     * @return  出租房实体
     */
    @Override
    public House getHouse(String id) {
        return houseMapper.getHouseById(id);
    }

    /**
     * 修改出租房信息
     * @param house
     * @return
     */
    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    /**
     * 删除出租房
     * @param houseId   出租房编号
     * @param delState  是否删除的状态
     * @return
     */
    @Override
    public int deleteHouse(String houseId, Integer delState) {
        House house = new House();
        house.setId(houseId);   //编号
        house.setIsdel(delState);   //更新状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    /**
     * 查询所有后台出租房信息
     * @param pageUtils
     * @return
     */
    @Override
    public PageInfo<House> getBackHouseAll(PageUtils pageUtils) {
        //开启分页
        PageHelper.startPage(pageUtils.getPage(),pageUtils.getRows());
        //查询数据
        List<House> list = houseMapper.getBackHouseAll();
        return new PageInfo<House>(list);
    }

    /**
     * 更新出租房审核转态
     * @param houseId   出租房编号
     * @param passState 出租房审核转态
     * @return
     */
    @Override
    public int passHouse(String houseId, Integer passState) {
        House house = new House();
        house.setId(houseId);   //编号
        house.setIspass(passState);   //更新审核状态
        return houseMapper.updateByPrimaryKeySelective(house);

    }

    /**
     * 获取可浏览的租房信息
     * @param searchCondition
     * @return
     */
    @Override
    public PageInfo<House> getBroswerHouse(SearchCondition searchCondition) {
        //开启分页查询
        PageHelper.startPage(searchCondition.getPage(),searchCondition.getRows());
        //调用方法查询数据
        List<House> list = houseMapper.getBroswerHouse(searchCondition);
        return new PageInfo<House>(list);
    }
}
