package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.PicMapper;
import com.example.server.mapper.PoiMapper;
import com.example.server.pojo.Pic;
import com.example.server.pojo.Poi;
import com.example.server.service.Ipoiservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiserviceImpl extends ServiceImpl<PoiMapper, Poi> implements Ipoiservice {
    @Autowired
    private PoiMapper poiMapper;
    @Autowired
    private PicMapper picMapper;

    @Override
    public void saveMain(Poi poi, List<Pic> pics){
    poiMapper.insert(poi);
    if (pics != null){

        for (Pic pic : pics){
            pic.setPoiId(poi.getId());
            picMapper.insert(pic);
        }
    }
    }
}
