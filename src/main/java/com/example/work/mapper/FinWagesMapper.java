package com.example.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.work.entity.FinWages;

import java.util.List;

public interface FinWagesMapper{
    /**
     * 查询工资
     *
     * @param wageId 工资主键
     * @return 工资
     */
    public FinWages selectFinWagesByWageId(Long wageId);

    /**
     * 查询工资列表
     *
     * @param finWages 工资
     * @return 工资集合
     */
    public List<FinWages> selectFinWagesList(FinWages finWages);

    /**
     * 新增工资
     *
     * @param finWages 工资
     * @return 结果
     */
    public int insertFinWages(FinWages finWages);

    /**
     * 修改工资
     *
     * @param finWages 工资
     * @return 结果
     */
    public int updateFinWages(FinWages finWages);

    /**
     * 删除工资
     *
     * @param wageId 工资主键
     * @return 结果
     */
    public int deleteFinWagesByWageId(Long wageId);

    /**
     * 批量删除工资
     *
     * @param wageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinWagesByWageIds(Long[] wageIds);
}