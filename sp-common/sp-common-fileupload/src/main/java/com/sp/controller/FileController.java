package com.sp.controller;

import com.sp.FileUploadApplication;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.properties.FileUploadProperties;
import com.sp.utils.AliOssUtil;
import com.sp.utils.FileUtils;
import com.sp.utils.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;




/**
 * 文件控制器
 *
 * @author 罗汉
 * @date 2023/06/11
 */
@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
public class FileController {

    /**
     * 基本路径
     */
    @Value("${file.img}")
    private String basePath;

    @Resource
    private AliOssUtil aliOssUtil;

    @Resource
    private FileUploadProperties findFriendProperties;

    @Value("${file.qiniu.url:null}")
    private String qiniuUrl;

    @Value("${server.servlet.session.cookie.domain}")
    private String host;

    @Value("${server.port}")
    private String port;

    /**
     * 上传
     *
     * @param file    文件
     * @param request 请求
     */
    @PostMapping("/upload")
    public BaseResponse<String> upload(MultipartFile file, HttpServletRequest request) {
        if (file == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请上传文件");
        }
        if (findFriendProperties.isUseLocalStorage()) {// 使用本地存储
            String fileName = FileUtils.uploadFile2Local(file);
            String fileUrl = "http://" + host + ":" + port + "/api/common/image/" + fileName;
            return ResultUtils.success(fileUrl);
        } else if (findFriendProperties.isUseAliOss()) {//使用阿里云上传
            try {
                // 获取原来的文件名
                String originalFilename = file.getOriginalFilename();
                // 截取扩展名后缀
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String objectName = UUID.randomUUID() + extension;
                // 文件请求路径
                String filePath = aliOssUtil.upload(file.getBytes(), objectName);
                return ResultUtils.success(filePath);
            } catch (IOException e) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件上传失败");
            }
        } else if(findFriendProperties.isUseQiNiu()){// 是否使用七牛云上传
//            String filename = FileUtils.uploadFile2Cloud(file);
            try {
                String filename = QiNiuUtils.upload(file.getBytes());
                String fileUrl = qiniuUrl + filename;
                return ResultUtils.success(fileUrl);
            } catch (IOException e) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件上传失败");
            }
        }
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "上传失败");
    }

//    /**
//     * 下载
//     *
//     * @param name     名字
//     * @param response 响应
//     */
//    @GetMapping("/image/{name}")
//    @ApiOperation(value = "文件下载")
//    @ApiImplicitParams(
//            {@ApiImplicitParam(name = "name", value = "文件名"),
//                    @ApiImplicitParam(name = "request", value = "request请求")})
//    public void download(@PathVariable String name, HttpServletResponse response) {
//        try {
//            // 获取指定文件
//            File img = new File(System.getProperty("user.dir") + basePath + name);
//            // 获取输入流
//            FileInputStream inputStream = new FileInputStream(img);
//            // 获取输出流
//            ServletOutputStream outputStream = response.getOutputStream();
//            // 指定response类型
//            response.setContentType("image/jpeg");
//            int len = 0;
//            // 设置缓冲区大小
//            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
//            // 将文件从输入流读到缓冲区，输出流读取缓冲区内容
//            while ((len = inputStream.read(bytes)) != FILE_END) {
//                outputStream.write(bytes, 0, len);
//                outputStream.flush();
//            }
//            inputStream.close();
//            outputStream.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
