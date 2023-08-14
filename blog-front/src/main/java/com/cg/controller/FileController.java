package com.cg.controller;

import com.cg.service.FileService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;


    /**
     * 文件上传至OSS
     * @param file 接受文件
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult Upload(MultipartFile file){
        return fileService.Upload(file);
    }


    //文件下载
}
