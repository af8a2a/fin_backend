package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.FinContract;
import com.example.work.mapper.FinContractMapper;
import com.example.work.service.IFinContractService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;

import javax.security.auth.login.LoginException;
import java.util.List;

public class FinContractServiceImpl extends ServiceImpl<FinContractMapper, FinContract> implements IFinContractService {
    @Resource
    private FinContractMapper finContractMapper;
    @Resource
    protected Validator validator;
    @Override
    public FinContract selectFinContractByContractId(Long contractId) {
        QueryWrapper<FinContract> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FinContract::getContractId,contractId);
        FinContract finContract=finContractMapper.selectOne(queryWrapper);
        return finContract;

    }

    @Override
    public List<FinContract> selectFinContractList(FinContract finContract) {
        QueryWrapper<FinContract> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FinContract::getContractId,finContract.getContractId());
        var contracts=finContractMapper.selectList(queryWrapper);
        return contracts;

    }

    @Override
    public FinContract selectFinContractByContractNumber(String ContractNumber) {
        QueryWrapper<FinContract> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FinContract::getContractNumber,ContractNumber);
        return finContractMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean checkFnContractNumberUnique(FinContract finContract) {
        QueryWrapper<FinContract> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FinContract::getContractNumber,finContract.getContractNumber());
        return finContractMapper.selectCount(queryWrapper)<=1;
    }

    @Override
    public int insertFinContract(FinContract finContract) {
        Boolean res= save(finContract);
        if(res==true){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateFinContract(FinContract finContract) {
        UpdateWrapper<FinContract> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda().eq(FinContract::getContractId,finContract.getContractId())
                ;
        return finContractMapper.update(finContract,updateWrapper);
    }

    @Override
    public int updateFinContractStatus(FinContract finContract) {
        UpdateWrapper<FinContract> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda().eq(FinContract::getContractId,finContract.getContractId())
                .set(FinContract::getStatus,finContract.getStatus());
        return finContractMapper.update(null,updateWrapper);
    }

    @Override
    public int deleteFinContractByContractIds(Long[] contractIds) {
        UpdateWrapper<FinContract> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda().eq(FinContract::getContractId,contractIds)
                .set(FinContract::getDelFlag,2);
        return finContractMapper.update(null,updateWrapper);
    }

    @Override
    public int deleteFinContractByContractId(Long contractId) {
        UpdateWrapper<FinContract> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda().eq(FinContract::getContractId,contractId)
                .set(FinContract::getDelFlag,2);
        return finContractMapper.update(null,updateWrapper);
    }

    /**
     * 导入合同数据
     *
     * @param finContractList 合同数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importFinContract(List<FinContract> finContractList, Boolean isUpdateSupport, String operName) {


        QueryWrapper<FinContract> queryWrapper = new QueryWrapper<>();
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for(FinContract finContract : finContractList){
            queryWrapper.lambda().eq(FinContract::getContractNumber,finContract.getContractNumber());
            FinContract f = finContractMapper.selectOne(queryWrapper);
            if(f==null){
                finContract.setCreateBy(operName);
                this.insertFinContract(finContract);
                successNum++;
                successMsg.append("<br/>" + successNum + "、合同 " + finContract.getContractName() + " 导入成功");

            }
            else
            {
                failureNum++;
                failureMsg.append("<br/>" + failureNum + "、合同 " + finContract.getContractName() + " 已存在");
            }

        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
            return successMsg.toString();
    }

    @Override
    public int addContractFile(Long contractId, String fileName) {
        //No implement
              return 0;
    }
}
