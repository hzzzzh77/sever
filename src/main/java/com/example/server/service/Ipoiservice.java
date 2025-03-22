package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Poi;
import com.example.server.pojo.Pic;

import java.util.List;

public interface Ipoiservice extends IService<Poi> {


    void saveMain(Poi poi, List<Pic> pics);
}
