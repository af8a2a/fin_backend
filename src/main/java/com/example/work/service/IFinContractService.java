package com.example.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.work.entity.FinContract;

import java.util.List;

public interface IFinContractService extends IService<FinContract> {
    /**
     * 查询合同管理
     *
     * @param contractId 合同管理主键
     * @return 合同管理
     */
    public FinContract selectFinContractByContractId(Long contractId);

    /**
     * 查询合同管理列表
     *
     * @param finContract 合同管理
     * @return 合同管理集合
     */
    public List<FinContract> selectFinContractList(FinContract finContract);

    /**
     * 通过合同编号查询合同
     *
     * @param ContractNumber 合同编号
     * @return 用户对象信息
     */
    public FinContract selectFinContractByContractNumber(String ContractNumber);

    /**
     * 校验合同编号是否唯一
     *
     * @param finContract 合同信息
     * @return 结果
     */
    public Boolean checkFnContractNumberUnique(FinContract finContract);

    /**
     * 新增合同管理
     *
     * @param finContract 合同管理
     * @return 结果
     */
    public int insertFinContract(FinContract finContract);

    /**
     * 修改合同管理
     *
     * @param finContract 合同管理
     * @return 结果
     */
    public int updateFinContract(FinContract finContract);

    /**
     * 修改合同状态
     *
     * @param finContract 合同
     * @return 结果
     */
    public int updateFinContractStatus(FinContract finContract);

    /**
     * 批量删除合同管理
     *
     * @param contractIds 需要删除的合同管理主键集合
     * @return 结果
     */
    public int deleteFinContractByContractIds(Long[] contractIds);

    /**
     * 删除合同管理信息
     *
     * @param contractId 合同管理主键
     * @return 结果
     */
    public int deleteFinContractByContractId(Long contractId);

    /**
     * 导入合同数据
     *
     * @param finContractList 合同数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importFinContract(List<FinContract> finContractList, Boolean isUpdateSupport, String operName);

    /**
     * 合同文件上传
     *
     * @param contractId 合同主键
     * @param fileName 文件名字
     * @return 结果
     */
    public int addContractFile(Long contractId, String fileName);
}
