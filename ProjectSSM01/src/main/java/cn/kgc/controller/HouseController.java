package cn.kgc.controller;

import cn.kgc.domain.House;
import cn.kgc.service.HouseService;
import cn.kgc.util.PageUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "HouseController2")
@RequestMapping("/admin/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 更新出租房删除状态
     * @param pageUtils
     * @return
     */
    @RequestMapping("getHouse")
    public Map<String,Object> getHouse(PageUtils pageUtils){
        //调用业务获取数据
        PageInfo<House> pageInfo = houseService.getBackHouseAll(pageUtils);

        //封装返回数据
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    /**
     * 更新出租房审核状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("updatePassState")
    public Map<String,Object> updatePassState(String id,Integer state){
        System.out.println(state);
        //调用业务获取数据
        int temp = houseService.passHouse(id, state);
        //封装返回数据
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("result",temp);
        return map;
    }
}
