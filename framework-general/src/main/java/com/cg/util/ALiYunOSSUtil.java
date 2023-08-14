package com.cg.util;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 阿里云OSS上传工具类
 */
public class ALiYunOSSUtil {
//    @Value("${ALiYun_OSS.accessKey}")
//    private String accessKey;
//    @Value("${ALiYun_OSS.secretKey}")
//    private String secretKey;
//    @Value("${ALiYun_OSS.bucket_img}")
//    private String bucket;

    /**
     * 上传图片至OSS
     * @param multipartFile 文件
     * @param fileName 文件全名
     * @return
     */
//    public String UploadOSS(MultipartFile multipartFile, String fileName) {
//        //OSS服务器地址
//        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
//
//        //上传的文件路径以及文件
//        Date date = new Date();//获取当前时间，根据时间将上传的文件按照时间分类
//        DateTime dateTime = new DateTime(date);
//        String prefix = dateTime.year() + "/" + (dateTime.month() + 1) + "/" + dateTime.dayOfMonth() + "/";//月份从0开始的
//
//        //将时间前缀和文件名称合并
//        String objectName = "userImg/" + prefix + fileName;
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
//
//        try {
//            InputStream inputStream = multipartFile.getInputStream();
//            ossClient.putObject(bucket, objectName,inputStream);
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//
//        //返回图片的访问路径
//        String url = "https://" + bucket + ".oss-cn-shanghai.aliyuncs.com/" + objectName;
//        return url;
//    }
}
