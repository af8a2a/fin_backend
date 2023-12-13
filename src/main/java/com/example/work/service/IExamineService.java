package com.example.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.work.entity.Examine;

import java.util.List;
import java.util.Map;

public interface IExamineService extends IService<Examine> {
    public List<Examine> selectExamineList(Examine examine);

    public List<Map<String, Object>> selectCompany(Examine examine);

    public int finishExamine(Examine examine);

    public int addExamine(Examine examine);
}
