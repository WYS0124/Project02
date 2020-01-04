package cn.kgc.pcontroller;

import cn.kgc.domain.Street;
import cn.kgc.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "StreetController2")
@RequestMapping("/page/")
public class StreetController {

    //调用业务
    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreetById")
    @ResponseBody
    public List<Street > getStreetById(Integer did){
        return streetService.getStreetByDistrict(did);
    }

}
