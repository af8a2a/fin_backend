package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.FinContract;
import com.example.work.mapper.FinContractMapper;
import com.example.work.service.IFinContractService;
import com.example.work.util.BeanValidators;
import com.example.work.util.UserConstants;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import javax.validation.Validator;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
@Service

public class FinContractServiceImpl  implements IFinContractService{
    private static final Logger log = LoggerFactory.getLogger(FinContractServiceImpl.class);

    @Resource
    private FinContractMapper finContractMapper;

    @Resource
    protected Validator validator;




    /**
     * 查询合同管理
     *
     * @param contractId 合同管理主键
     * @return 合同管理
     */
    @Override
    public FinContract selectFinContractByContractId(Long contractId)
    {
        return finContractMapper.selectFinContractByContractId(contractId);
    }

    /**
     * 查询合同管理列表
     *
     * @param finContract 合同管理
     * @return 合同管理
     */
    @Override
    public List<FinContract> selectFinContractList(FinContract finContract)
    {
//        System.out.println("finContract.getContractDate()");
//        System.out.println(finContract);
        System.out.println(finContract.getContractDate());
        return finContractMapper.selectFinContractList(finContract);
    }

    /**
     * 通过合同编号查询合同
     *
     * @param contractNumber 合同编号
     * @return 用户对象信息
     */
    @Override
    public FinContract selectFinContractByContractNumber(String contractNumber)
    {
        return finContractMapper.selectFinContractByContractNumber(contractNumber);
    }

    /**
     * 校验合同编号是否唯一
     *
     * @param finContract 合同信息
     * @return 结果
     */
    @Override
    public String checkFnContractNumberUnique(FinContract finContract)
    {
        int count = finContractMapper.checkFnContractNumberUnique(finContract);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增合同管理
     *
     * @param finContract 合同管理
     * @return 结果
     */
    @Override
    public int insertFinContract(FinContract finContract)
    {
        finContract.setCreateTime(new Date());
        return finContractMapper.insertFinContract(finContract);
    }

    /**
     * 修改合同管理
     *
     * @param finContract 合同管理
     * @return 结果
     */
    @Override
    public int updateFinContract(FinContract finContract)
    {
        finContract.setUpdateTime(new Date());
        return finContractMapper.updateFinContract(finContract);
    }

    /**
     * 修改合同状态
     *
     * @param finContract 合同
     * @return 结果
     */
    @Override
    public int updateFinContractStatus(FinContract finContract){
        finContract.setUpdateTime(new Date());
        return finContractMapper.updateFinContractStatus(finContract);
    }

    /**
     * 批量删除合同管理
     *
     * @param contractIds 需要删除的合同管理主键
     * @return 结果
     */
    @Override
    public int deleteFinContractByContractIds(Long[] contractIds)
    {
        return finContractMapper.deleteFinContractByContractIds(contractIds);
    }

    /**
     * 删除合同管理信息
     *
     * @param contractId 合同管理主键
     * @return 结果
     */
    @Override
    public int deleteFinContractByContractId(Long contractId)
    {
        return finContractMapper.deleteFinContractByContractId(contractId);
    }

    /**
     * 导入合同数据
     *
     * @param finContractList 合同数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importFinContract(List<FinContract> finContractList, Boolean isUpdateSupport, String operName)
    {
        if (finContractList==null || finContractList.size() == 0)
        {
            throw new RuntimeException("导入合同数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (FinContract finContract : finContractList)
        {
            try
            {
                // 验证是否存在这个合同
                FinContract f = finContractMapper.selectFinContractByContractNumber(finContract.getContractNumber());
                System.out.println("fffff");
                System.out.println(finContract.getContractNumber());

                if (f==null)
                {
                    BeanValidators.validateWithException(validator, finContract);
                    finContract.setCreateBy(operName);
                    this.insertFinContract(finContract);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合同 " + finContract.getContractName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    System.out.println("fffffffffffff");
                    System.out.println(f.getContractNumber());
                    System.out.println(f);
                    BeanValidators.validateWithException(validator, finContract);
                    finContract.setUpdateBy(operName);
                    this.updateFinContract(finContract);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合同 " + finContract.getContractName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、合同 " + finContract.getContractName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合同 " + finContract.getContractName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new RuntimeException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 合同文件上传
     *
     * @param contractId 合同主键
     * @param fileName 文件名字
     * @return 结果
     */
    public int addContractFile(Long contractId, String fileName)
    {
        //todo
        return 0;
    }
}
