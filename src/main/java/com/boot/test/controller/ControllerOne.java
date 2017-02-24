package com.boot.test.controller;

import com.boot.test.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/10.
 */
@Api(value = "ControllerOne", description = "ControllerOne描述")
@RestController
@RequestMapping("/controller_one")
public class ControllerOne {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes = "测试接口详细描述")//swagger的配置
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("1");
        modelAndView.addObject("msg", "刘涛");
        return modelAndView;
    }


    @GetMapping("/test3")
    public List test3(){

        List<Lie> lieList = new ArrayList<>();
        lieList.add(new Lie(1, 1, 1, "1-1-1"));
        lieList.add(new Lie(1, 1, 2, "1-1-2"));
        lieList.add(new Lie(1, 1, 3, "1-1-3"));
        lieList.add(new Lie(1, 2, 1, "1-2-1"));
        lieList.add(new Lie(1, 2, 2, "1-2-2"));
        lieList.add(new Lie(1, 3, 1, "1-3-1"));
        lieList.add(new Lie(2, 1, 1, "2-1-1"));
        lieList.add(new Lie(2, 1, 2, "2-1-2"));
        lieList.add(new Lie(2, 2, 1, "2-2-1"));
        lieList.add(new Lie(2, 2, 2, "2-2-2"));
        lieList.add(new Lie(2, 2, 3, "2-2-3"));
        lieList.add(new Lie(2, 3, 1, "2-3-1"));
        lieList.add(new Lie(3, 1, 1, "3-1-1"));
        lieList.add(new Lie(3, 1, 2, "3-1-2"));
        lieList.add(new Lie(3, 2, 1, "3-2-1"));
        lieList.add(new Lie(3, 2, 2, "3-2-2"));
        lieList.add(new Lie(3, 2, 3, "3-2-3"));
        lieList.add(new Lie(3, 3, 1, "3-3-1"));
        List<Ping> pingList = new ArrayList<>();

        for (Lie lie1 : lieList){
            if(pingList.size()<=lie1.getPing()-1){
                Ping ping = new Ping();
                ping.setPingNum(lie1.getPing());
                List<Hang> hangList = new ArrayList<>();
                for (Lie lie : lieList){
                    if(lie1.getPing() == lie.getPing()) {
                        if (hangList.size() <= lie.getHang() - 1) {
                            Hang hang = new Hang();
                            hang.setHangNum(lie.getHang());
                            hang.setLie(new ArrayList<>());
                            hang.getLie().add(lie);
                            hangList.add(hang);
                        } else {
                            hangList.get(lie.getHang() - 1).getLie().add(lie);
                        }
                    }
                }
                ping.setHang(hangList);
                pingList.add(ping);
            }
        }

        return pingList;
    }



    /**
     * 测试Validator效验
     *
     * @param vo
     * @param bindingResult
     * @return
     */
    @PutMapping("/test2")
    @ApiOperation(value = "测试Validator效验", notes = "测试Validator效验详细描述")//swagger的配置
    public String test2(@RequestBody @Valid UserVo vo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                logger.debug(error.getObjectName());
                logger.debug(error.getDefaultMessage());
                logger.debug(error.getCode());
            }
        }
        return "test";
    }
}
