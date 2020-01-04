package cn.kgc.service.impl;

import cn.kgc.domain.Type;
import cn.kgc.domain.TypeExample;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.mapper.TypeMapper;
import cn.kgc.service.TypeService;
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
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

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
    public PageInfo<Type> getTypeByPage(PageUtils pageUtils) {
        PageHelper.startPage(pageUtils.getPage(),pageUtils.getRows());
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 添加参数
     * @param type  //传入参数
     * @return
     */
    @Override
    public Integer insertType(Type type) {
        return typeMapper.insertSelective(type);
    }

    /**
     * 根据id查询区域数据
     * 修改回显
     * @param id
     * @return
     */
    @Override
    public Type findTypeById(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改类型数据
     * @param type
     * @return
     */
    @Override
    public Integer updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    /**
     * 删除类型及类型下的街道
     * @param id
     * @return
     */
    @Override
    public void deleteType(Integer id) {
        //删除区域
        typeMapper.deleteByPrimaryKey(id);
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
    public int deleteMoreType(Integer[] ids) {
        //删除区域
        return typeMapper.deleteType(ids);
    }

    /**
     * 查询所有的区域
     * @return
     */
    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
