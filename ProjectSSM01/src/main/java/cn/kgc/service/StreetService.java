package cn.kgc.service;

import cn.kgc.domain.Street;

import java.util.List;

public interface StreetService {

    /**
     * 获取某区域下的街道
     * @return
     */
    public List<Street> getStreetByDistrict(Integer did);
}
