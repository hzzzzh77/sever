package com.example.server.controllers;

import com.example.server.service.IStorageService;
import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/")
public class CommonController {

    @Autowired
    private IStorageService storageService;

    @PostMapping(value = "/upload")
    public Result upload(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("file");
        file.getOriginalFilename();
        String filename=file.getOriginalFilename();
        log.info("Upload file.filename={}",filename);
        storageService.save(file,filename,"E:/Work project/web/upload/");
        return Result.success(filename);

    }
}
