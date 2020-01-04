package cn.kgc.service.impl;

import cn.kgc.domain.Street;
import cn.kgc.domain.StreetExample;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "StreetServiceImpl2")
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;

    /**
     * 获取某区域下的街道
     * @param did
     * @return
     */
    @Override
    public List<Street> getStreetByDistrict(Integer did) {
        StreetExample streetExample = new StreetExample();
        //添加条件
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        return streetMapper.selectByExample(streetExample);
    }

    //测试业务方法
    public static void main(String[] args) {
        ApplicationContext apc = new ClassPathXmlApplicationContext("applicationContext.xml");
        StreetService streetService = (StreetService) apc.getBean("StreetServiceImpl2");
        System.out.println(streetService.getStreetByDistrict(1004));
    }
}
