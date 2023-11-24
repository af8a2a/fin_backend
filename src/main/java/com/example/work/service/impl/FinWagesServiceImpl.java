package com.example.work.service.impl;

import com.example.work.entity.FinWages;
import com.example.work.mapper.FinWagesMapper;
import com.example.work.service.IFinWagesService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FinWagesServiceImpl implements IFinWagesService {
    @Resource
    private FinWagesMapper finWagesMapper;

    /**
     * 查询工资
     *
     * @param wageId 工资主键
     * @return 工资
     */
    @Override
    public FinWages selectFinWagesByWageId(Long wageId)
    {
        return finWagesMapper.selectFinWagesByWageId(wageId);
    }

    /**
     * 查询工资列表
     *
     * @param finWages 工资
     * @return 工资
     */
    @Override
    public List<FinWages> selectFinWagesList(FinWages finWages)
    {
        return finWagesMapper.selectFinWagesList(finWages);
    }

    /**
     * 新增工资
     *
     * @param finWages 工资
     * @return 结果
     */
    @Override
    public int insertFinWages(FinWages finWages)
    {
        finWages.setCreateTime(new Date());
        return finWagesMapper.insertFinWages(finWages);
    }

    /**
     * 修改工资
     *
     * @param finWages 工资
     * @return 结果
     */
    @Override
    public int updateFinWages(FinWages finWages)
    {
        finWages.setUpdateTime(new Date());
        return finWagesMapper.updateFinWages(finWages);
    }

    /**
     * 批量删除工资
     *
     * @param wageIds 需要删除的工资主键
     * @return 结果
     */
    @Override
    public int deleteFinWagesByWageIds(Long[] wageIds)
    {
        return finWagesMapper.deleteFinWagesByWageIds(wageIds);
    }

    /**
     * 删除工资信息
     *
     * @param wageId 工资主键
     * @return 结果
     */
    @Override
    public int deleteFinWagesByWageId(Long wageId)
    {
        return finWagesMapper.deleteFinWagesByWageId(wageId);
    }
}