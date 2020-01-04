package cn.kgc.controller;

import cn.kgc.domain.Type;
import cn.kgc.service.TypeService;
import cn.kgc.util.PageUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")  //指定请求前缀
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 查询区域分页
     * @param pageUtils
     * @return
     */
    @RequestMapping("getTypeData")
    public Map<String,Object> getType(PageUtils pageUtils){
        PageInfo<Type> pageInfo = typeService.getTypeByPage(pageUtils);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    /**
     * 添加区域
     * @param type
     * @return
     */
    @RequestMapping("addType")
    public String addType(Type type){
        try{
            //调用业务
            Integer result = typeService.insertType(type);
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
    @RequestMapping("getType")
    public Type getType(Integer id){
        try{
            //调用业务
            Type type = typeService.findTypeById(id);
            return type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改功能
     * @param type
     * @return
     */
    @RequestMapping("updateType")
    public String updateTypeData(Type type){
        try{
            //调用业务
            Integer result = typeService.updateType(type);
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
    @RequestMapping("deleteType")
    public String deleteType(Integer id){
        try{
            //调用业务
            typeService.deleteType(id);
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
    @RequestMapping("deleteMoreType")
    public String deleteMoreType(String ids){
        try{
            String[] strList = ids.split(",");
            Integer[] idList = new Integer[strList.length];
            for (int i = 0;i < strList.length;i++){
                idList[i] = new Integer(strList[i]);
            }
            //调用业务
            int more = typeService.deleteMoreType(idList);
            //封装返回数据
            return "{\"result\":"+more+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

}
