package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.FinWages;
import com.example.work.mapper.FinWagesMapper;
import com.example.work.service.IFinWagesService;
import com.example.work.util.Helper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FinWagesServiceImpl extends ServiceImpl<FinWagesMapper, FinWages> implements IFinWagesService {
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
        return finWagesMapper.selectById(wageId);
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
        QueryWrapper<FinWages> wrapper =new QueryWrapper<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(finWages.getDate());
        int year = calendar.get(Calendar.YEAR);
        wrapper.lambda()
                .apply("YEAR(date)={0}",year)
                .eq(FinWages::getName,finWages.getName())
                .eq(FinWages::getCompany,finWages.getCompany());
        return finWagesMapper.selectList(wrapper);
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
        finWages.setDate(new Date());
        save(finWages);
        return 1;
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
        finWages.setDate(new Date());
        return finWagesMapper.updateById(finWages);
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
        return finWagesMapper.deleteBatchIds(Arrays.stream(wageIds).toList());
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
        return finWagesMapper.deleteById(wageId);
    }
}