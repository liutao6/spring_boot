package com.boot.test.dao;

import com.boot.test.model.Demo;
import com.boot.test.model.DemoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DemoMapper {
    long countByExample(DemoExample example);

    int deleteByExample(DemoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Demo record);

    int insertSelective(Demo record);

    List<Demo> selectByExample(DemoExample example);

    Demo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Demo record, @Param("example") DemoExample example);

    int updateByExample(@Param("record") Demo record, @Param("example") DemoExample example);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
}