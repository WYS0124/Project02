package cn.kgc.service.impl;

import cn.kgc.domain.District;
import cn.kgc.domain.DistrictExample;
import cn.kgc.mapper.DistrictMapper;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.DistrictService;
import cn.kgc.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private StreetMapper streetMapper;

    /**
     * 分页查询
     * @param pageUtils
     * page属性接收页码，rows属性接收页大小
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)//事务挂起
    public PageInfo<District> getDistrictByPage(PageUtils pageUtils) {
        PageHelper.startPage(pageUtils.getPage(),pageUtils.getRows());
        DistrictExample districtExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 添加参数
     * @param district  //传入参数
     * @return
     */
    @Override
    public Integer insertDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    /**
     * 根据id查询区域数据
     * 修改回显
     * @param id
     * @return
     */
    @Override
    public District findDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改区域数据
     * @param district
     * @return
     */
    @Override
    public Integer updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    /**
     * 删除区域及区域下的街道
     * @param id
     * @return
     */
    @Override
    public void deleteDistrict(Integer id) {
        //删除区域
        districtMapper.deleteByPrimaryKey(id);
//        int i=10;
//        int j=0;
//        i = i/j;
        //删除街道
        streetMapper.deleteSereetByDid(id);
    }

    /**
     * 批量删除区域
     * @param ids
     * @return
     */
    @Override
    public int deleteMoreDistrict(Integer[] ids) {
        //删除区域
        return districtMapper.deleteDistrict(ids);
    }

    /**
     * 查询所有的区域
     * @return
     */
    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
