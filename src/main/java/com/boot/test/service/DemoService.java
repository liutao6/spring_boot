package com.boot.test.service;

import com.boot.test.dao.DemoMapper;
import com.boot.test.model.Demo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/10.
 */
@Service
public class DemoService {


    @Resource
    private DemoMapper demoMapper;


    @Transactional
    public void save(String name){
        Demo demo = new Demo();
        demo.setName(name);
        demoMapper.insertSelective(demo);
    }


    public Demo getDemoById(Long id){
        return demoMapper.selectByPrimaryKey(id);
    }
}
