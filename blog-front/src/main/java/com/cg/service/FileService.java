package com.cg.service;

import com.cg.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseResult Upload(MultipartFile file);
}
