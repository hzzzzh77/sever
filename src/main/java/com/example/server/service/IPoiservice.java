package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Poi;
import com.example.server.pojo.Pic;

import java.util.List;

public interface IPoiservice extends IService<Poi> {


    void saveMain(Poi poi, List<Pic> pics);

    void deleteMain(Integer id);

    void updateMain(Poi poi, List<Pic> pics);
}
