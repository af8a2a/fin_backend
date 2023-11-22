package com.example.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.work.entity.FinPurchase;

import java.util.List;

public interface FinPurchaseMapper  {
    /**
     * 查询采购订单
     *
     * @param purchaseId 采购订单主键
     * @return 采购订单
     */
    public FinPurchase selectFinPurchaseByPurchaseId(Long purchaseId);

    /**
     * 查询采购订单列表
     *
     * @param finPurchase 采购订单
     * @return 采购订单集合
     */
    public List<FinPurchase> selectFinPurchaseList(FinPurchase finPurchase);

    /**
     * 新增采购订单
     *
     * @param finPurchase 采购订单
     * @return 结果
     */
    public int insertFinPurchase(FinPurchase finPurchase);

    /**
     * 修改采购订单
     *
     * @param finPurchase 采购订单
     * @return 结果
     */
    public int updateFinPurchase(FinPurchase finPurchase);

    /**
     * 删除采购订单
     *
     * @param purchaseId 采购订单主键
     * @return 结果
     */
    public int deleteFinPurchaseByPurchaseId(Long purchaseId);

    /**
     * 批量删除采购订单
     *
     * @param purchaseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinPurchaseByPurchaseIds(Long[] purchaseIds);
}
