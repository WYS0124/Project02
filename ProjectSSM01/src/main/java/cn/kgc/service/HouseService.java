package cn.kgc.service;

import cn.kgc.domain.House;
import cn.kgc.util.PageUtils;
import cn.kgc.util.SearchCondition;
import com.github.pagehelper.PageInfo;

public interface HouseService {

    /**
     * 发布房屋信息
     * @param house 出租房信息
     * @return
     */
    public int addHouse(House house);

    /**
     * 查询用户发布的出租房信息
     * @param id    用户编号
     * @return      出租房信息
     */
    public PageInfo<House> getHouseByUser(Integer id,PageUtils pageUtils);

    /**
     * 获取单条房子信息
     * @param id    房子的id
     * @return
     */
    public House getHouse(String id);

    /**
     * 修改出租房信息
     * @param house
     * @return
     */
    public int updateHouse(House house);

    /**
     * 删除出租房
     * @param houseId   出租房编号
     * @param delState  是否删除的状态
     * @return  影响的行数
     */
    public int deleteHouse(String houseId,Integer delState);

    /**
     * 查询所有后台出租房信息
     * @return
     */
    public PageInfo<House> getBackHouseAll(PageUtils pageUtils);

    /**
     * 更新出租房审核转态
     * @param houseId   出租房编号
     * @param passState 出租房审核转态
     * @return
     */
    public int passHouse(String houseId,Integer passState);

    /**
     * 获取可浏览的租房信息
     * @param searchCondition
     * @return
     */
    public PageInfo<House> getBroswerHouse(SearchCondition searchCondition);

}
