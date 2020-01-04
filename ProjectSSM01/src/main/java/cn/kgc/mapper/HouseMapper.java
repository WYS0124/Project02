package cn.kgc.mapper;

import cn.kgc.domain.House;
import cn.kgc.domain.HouseExample;
import cn.kgc.util.SearchCondition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询当前用户的出租房
    List<House> getHouseUser(Integer userId);

    //查询单条出租房信息
    House getHouseById(String id);

    //查询所有出租房信息 (进行审核)
    List<House> getBackHouseAll();

    //显示所有可浏览的出租房信息
    List<House> getBroswerHouse(SearchCondition searchCondition);
}