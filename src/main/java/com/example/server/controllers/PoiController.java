package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.form.PoiForm;
import com.example.server.pojo.Pic;
import com.example.server.pojo.Poi;
import com.example.server.service.Ipicservice;
import com.example.server.service.Ipoiservice;
import com.example.server.vo.PoiVo;
import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/poi")
public class PoiController {//111111

    @Autowired
    private Ipoiservice poiservice;

    @Autowired
    private Ipicservice picservice;

    @GetMapping("/list")
    public Result list(
            @RequestParam(defaultValue = "1") int pagenum,//@RequestParam(defaultValue = "")设置默认值
            @RequestParam(defaultValue = "30") int pagesize){
        log.info("poi list，pagenum={},pagesize={}",pagenum,pagesize);

       Page<Poi> page = new Page<>(pagenum,pagesize);
       IPage<Poi> pageResult = poiservice.page(page);

        List voList= pageResult.getRecords().stream().map(poi -> {
            PoiVo poiVo = new PoiVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("poi_id", poi.getId());
           List<Pic> pics = picservice.list(query);
            BeanUtils.copyProperties(poi,poiVo);
            poiVo.setPics(pics);
            return poiVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(voList);

        return Result.success(pageResult);
    }
    @GetMapping("/detail/{id}")//查看
    public Result detail(@PathVariable int id){
        log.info("poi detail,id={}",id);
        PoiVo poiVo = new PoiVo();
        Poi poi = poiservice.getById(id);
        QueryWrapper query = new QueryWrapper();
        query.eq("poi_id", poi.getId());
        List<Pic> pics = picservice.list(query);
        BeanUtils.copyProperties(poi,poiVo);
        poiVo.setPics(pics);
        return Result.success(poiVo);
    }

    @PostMapping("/add")//增加
    public Result add(@RequestBody PoiForm poiForm){
        log.info("poi add,poi={}",poiForm);
        Poi poi = new Poi();
        BeanUtils.copyProperties(poiForm,poi);
        poiservice.saveMain(poi,poiForm.getPics());

//        PoiVo poiVo = new PoiVo();
//        BeanUtils.copyProperties(poi,poiVo);
//        return Result.success(poiVo);
        return detail(poi.getId());
    }
    @PutMapping("/edit/{id}")//修改
    public Result edit(@RequestBody Poi poi,@PathVariable int id){
        log.info("poi edit,poi={}",poi);
        poi.setId(id);
        poiservice.updateById(poi);

        return Result.success();
    }
    @DeleteMapping ("/delete/{id}")//删除
    public Result delete(@PathVariable int id){
        log.info("poi delete,id={}",id);
        poiservice.removeById(id);
       return Result.success();
    }
}

