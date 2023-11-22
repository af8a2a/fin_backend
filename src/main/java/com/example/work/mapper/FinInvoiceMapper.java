package com.example.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.work.entity.FinInvoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinInvoiceMapper
    {
        /**
         * 查询发票
         *
         * @param invoiceId 发票主键
         * @return 发票
         */
        public FinInvoice selectFinInvoiceByInvoiceId(Long invoiceId);

        /**
         * 查询发票列表
         *
         * @param finInvoice 发票
         * @return 发票集合
         */
        public List<FinInvoice> selectFinInvoiceList(FinInvoice finInvoice);

//    /**
//     * 查询同一来源所有发票信息
//     *
//     * @param invoiceFrom 发票来源
//     * @return 发票
//     */
//    public List<FinInvoice> selectFinInvoiceByInvoiceFrom(String invoiceFrom);

        /**
         * 新增发票
         *
         * @param finInvoice 发票
         * @return 结果
         */
        public int insertFinInvoice(FinInvoice finInvoice);

        /**
         * 修改发票
         *
         * @param finInvoice 发票
         * @return 结果
         */
        public int updateFinInvoice(FinInvoice finInvoice);

        /**
         * 删除发票
         *
         * @param invoiceId 发票主键
         * @return 结果
         */
        public int deleteFinInvoiceByInvoiceId(Long invoiceId);

        /**
         * 批量删除发票
         *
         * @param invoiceIds 需要删除的数据主键集合
         * @return 结果
         */
        public int deleteFinInvoiceByInvoiceIds(Long[] invoiceIds);


        /**
         * 添加发票文件
         *
         * @param invoiceId 发票主键
         * @param fileId 文件主键
         * @retyrn
         */
        public int insertFinInvoiceFile(@Param("invoiceId") Long invoiceId, @Param("fileId")  Long fileId);

}
