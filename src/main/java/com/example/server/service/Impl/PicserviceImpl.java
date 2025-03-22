package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.PicMapper;
import com.example.server.mapper.PoiMapper;
import com.example.server.pojo.Pic;
import com.example.server.pojo.Poi;
import com.example.server.service.Ipicservice;
import com.example.server.service.Ipoiservice;
import org.springframework.stereotype.Service;

@Service
public class PicserviceImpl extends ServiceImpl<PicMapper,Pic>implements Ipicservice {
}
