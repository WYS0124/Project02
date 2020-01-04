package cn.kgc.controller;

import cn.kgc.domain.District;
import cn.kgc.service.DistrictService;
import cn.kgc.util.PageUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")  //指定请求前缀
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    /**
     * 查询区域分页
     * @param pageUtils
     * @return
     */
    @RequestMapping("getDistrictData")
    public Map<String,Object> getDistrict(PageUtils pageUtils){
        PageInfo<District> pageInfo = districtService.getDistrictByPage(pageUtils);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    /**
     * 添加区域
     * @param district
     * @return
     */
    @RequestMapping("addDistrict")
    public String addDistrict(District district){
        try{
            //调用业务
            Integer result = districtService.insertDistrict(district);
            //封装返回数据
            //Map<String,Object> map = new HashMap<String, Object>();
            //map.put("count",count);
            return "{\"result\":"+result+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    /**
     * 修改回显
     * @param id
     * @return
     */
    @RequestMapping("getDistrict")
    public District getDistrict(Integer id){
        try{
            //调用业务
            District district = districtService.findDistrictById(id);
            return district;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改功能
     * @param district
     * @return
     */
    @RequestMapping("updateDistrict")
    public String updateDistrictData(District district){
        try{
            //调用业务
            Integer result = districtService.updateDistrict(district);
            //封装返回数据
            return "{\"result\":"+result+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("deleteDistrict")
    public String deleteDistrict(Integer id){
        try{
            //调用业务
            districtService.deleteDistrict(id);
            //封装返回数据
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    /**
     * 批量删除区域
     * @param ids
     * @return
     */
    //前台传递数据的格式：ids=1,2,3,4  后台：String ids变量接收数据
    //前台传递数据的格式：ids=1&ids=2&ids=3   后台：Integer []ids变量接收数据
    @RequestMapping("deleteMoreDistrict")
    public String deleteMoreDistrict(String ids){
        try{
            String[] strList = ids.split(",");
            Integer[] idList = new Integer[strList.length];
            for (int i = 0;i < strList.length;i++){
                idList[i] = new Integer(strList[i]);
            }
            //调用业务
            int more = districtService.deleteMoreDistrict(idList);
            //封装返回数据
            return "{\"result\":"+more+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

}
