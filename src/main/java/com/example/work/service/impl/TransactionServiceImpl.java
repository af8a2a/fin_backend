package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Examine;
import com.example.work.entity.Transaction;
import com.example.work.entity.TransactionDTO;
import com.example.work.mapper.ExamineMapper;
import com.example.work.mapper.TransactionMapper;
import com.example.work.service.ITransactionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {
    @Resource
    private TransactionMapper mapper;

    @Resource
    private ExamineMapper examineMapper;
    @Override
    public int addTransaction(Transaction transaction) {
        Examine examine=new Examine();
        transaction.setStatus("待审批");
        mapper.insert(transaction);
        examine.setCommitId(transaction.getTransactionId());
        examine.setExamineTime(transaction.getDate());
        examine.setExamineCompany(transaction.getCompany());
        examine.setType("营业审核");
        examine.setMoney(transaction.getIncoming());
        return examineMapper.insert(examine);

    }

    @Override
    public List<Transaction> getTransaction(Transaction transaction) {
        Calendar calendar = Calendar.getInstance();
        QueryWrapper<Transaction> wrapper = new QueryWrapper<>();
            calendar.setTime(transaction.getDate());
            int year = calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
        wrapper.lambda().eq(Transaction::getCompany,transaction.getCompany())
                .eq(Transaction::getType, transaction.getType())
                .eq(Transaction::getStatus,"已审核")
                .apply("YEAR(date)={0}",year)
                .apply("MONTH(date)={0}",month+1);
        return mapper.selectList(wrapper);
    }

    @Override
    public int deleteTransactionByID(Integer transactionID) {
        return mapper.deleteById(transactionID);
    }

    @Override
    public int editTransaction(Transaction transaction) {
        transaction.setStatus("待审批");
        updateById(transaction);
        return 1;
    }

    @Override
    public List<Map<String, Object>> analysis(Transaction transaction) {
        QueryWrapper<Transaction> queryWrapper = new QueryWrapper<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);

        queryWrapper.select("name", "SUM(incoming) as value").groupBy("name")
                .eq("company",transaction.getCompany())
                .ge("date", getFirstDayOfYear(year))
                .lt("date", getFirstDayOfYear(year + 1))
                .eq("status","已审核");
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> tax(TransactionDTO dto) {
        int year = Integer.parseInt(dto.getDate());


        QueryWrapper<Transaction> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MONTH(date) as month", "SUM(incoming) as totalIncoming")
                .eq("company",dto.getCompany())
                .eq("status","已审核")
                .ge("date", getFirstDayOfYear(year))
                .lt("date", getFirstDayOfYear(year + 1))
                .groupBy("MONTH(date)");

        List<Map<String, Object>> resultMaps = mapper.selectMaps(queryWrapper);
        Map<String, Object> monthlyIncomingMap = new HashMap<>();
        for (Integer i = 1; i <= 12; i++) {
            monthlyIncomingMap.put(i.toString(), BigDecimal.ZERO);
        }
        for (Map<String, Object> map : resultMaps) {
            Integer month = (Integer) map.get("month");
            BigDecimal totalIncoming = (BigDecimal) map.get("totalIncoming");
            monthlyIncomingMap.put(month.toString(), totalIncoming);
        }
        resultMaps.clear();
        for (Integer i = 1; i <= 12; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("month", i.toString());
            map.put("incoming", monthlyIncomingMap.get(i.toString()));
            map.put("tax", 0);

            resultMaps.add(map);
        }
        return resultMaps;


    }

    private Date getFirstDayOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


}
