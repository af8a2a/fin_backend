package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Transaction;
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

    @Override
    public int addTransaction(Transaction transaction) {
        transaction.setDate(new Date());
        save(transaction);
        return 1;
    }

    @Override
    public List<Transaction> getTransaction(Transaction transaction) {
        Calendar calendar = Calendar.getInstance();
        QueryWrapper<Transaction> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Transaction::getCompany,transaction.getCompany());
        if (transaction.getDate() != null) {
            calendar.setTime(transaction.getDate());
            int year = calendar.get(Calendar.YEAR);
            wrapper.apply("YEAR(date)={0}",year);
        }
        if (transaction.getType() != null) {

            wrapper.lambda().eq(Transaction::getType, transaction.getType());
        }
        return mapper.selectList(wrapper);
    }

    @Override
    public int editTransaction(Transaction transaction) {
        updateById(transaction);
        return 1;
    }

    @Override
    public List<Map<String, Object>> analysis() {
        QueryWrapper<Transaction> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "SUM(incoming) as value").groupBy("name");
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> tax(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);


        QueryWrapper<Transaction> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MONTH(date) as month", "SUM(incoming) as totalIncoming")
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
