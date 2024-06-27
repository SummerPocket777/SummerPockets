package com.sp.utils;

import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static com.sp.core.constants.SystemConstants.DEFAULT_BUFFER_SIZE;
import static com.sp.core.constants.SystemConstants.FILE_END;


/**
 * 文件工具
 *
 * @author 罗汉
 * @date 2023/06/22
 */
@Slf4j
@Component
public class FileUtils {

    private static String basePath;

    /**
     * 上传文件到本地
     *
     * @param file 文件
     * @return {@link String}
     */
    public static String uploadFile2Local(MultipartFile file) {
        File localFile = transferFile(file);
        return localFile.getName();
    }

    /**
     * 上传文件到云端
     *
     * @param file 文件
     * @return {@link String}
     */
    public static String uploadFile2Cloud(MultipartFile file) {
        File localFile = transferFile(file);
        byte[] imageStream = getImageStream(localFile);
        String fileName = QiNiuUtils.upload(imageStream);
        boolean delete = localFile.delete();
        if (!delete) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        //上传七牛云
        return fileName;
    }

    /**
     * 获取图像流
     *
     * @param imageFile 图像文件
     * @return {@link byte[]}
     */
    public static byte[] getImageStream(File imageFile) {
        byte[] buffer = null;
        FileInputStream fis;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            fis = new FileInputStream(imageFile);
            byte[] b = new byte[DEFAULT_BUFFER_SIZE];
            int n;
            while ((n = fis.read(b)) != FILE_END) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            log.error("exception message", e);
        }
        return buffer;
    }

    /**
     * 文件上传方法。
     * 该方法接收一个MultipartFile对象，将其保存到服务器的指定目录，并返回保存后的文件对象。
     * 文件名由UUID生成，以避免重复，同时保留了原文件的后缀名。
     *
     * @param file 上传的文件对象
     * @return 保存后的文件对象
     * @throws BusinessException 如果上传过程中出现错误，如文件名为空、文件保存失败等，将抛出业务异常
     */
    /**
     * 传输文件
     *
     * @param file 文件
     * @return {@link File}
     */

    /**
     * 传输文件
     *
     * @param file 文件
     * @return {@link File}
     */
    public static File transferFile(MultipartFile file) {
        // 打印文件的原始信息
        System.out.println("上传文件原始名: " + file.getOriginalFilename());
        System.out.println("上传文件大小: " + file.getSize());
        // 生成唯一标识符作为文件名，以避免重复
        String uid = UUID.randomUUID().toString().replace("-", "");
        // 获取上传文件的原名
        String originalFilename = file.getOriginalFilename();
        // 检查原文件名是否为空，为空则抛出业务异常
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 提取原文件的后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 检查后缀名是否为空，为空则抛出业务异常
        if (suffix.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 构建文件保存的目录
        File dir = new File(System.getProperty("user.dir") + basePath);
        // 如果目录不存在，则创建目录
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs(); // 使用 mkdirs() 创建多级目录
            // 如果创建目录失败，则抛出业务异常
            if (!mkdirs) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
        // 构建保存后的文件路径
        Path tempFilePath = Paths.get(System.getProperty("user.dir") + basePath + uid + suffix);
        try (InputStream inputStream = file.getInputStream()) {
            // 将上传的文件保存到指定路径
            Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
            //刷新缓存
            Files.newOutputStream(tempFilePath).flush();
            System.out.println("文件已成功保存到：" + tempFilePath.toAbsolutePath().toString());
        } catch (IOException e) {
            // 如果保存文件失败，则抛出业务异常
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        // 确认文件是否存在
        File localFile = tempFilePath.toFile();
        if (localFile.exists() && localFile.isFile()) {
            System.out.println("文件存在且保存成功: " + localFile.getAbsolutePath());
        } else {
            System.out.println("文件未能成功保存: " + localFile.getAbsolutePath());
        }
        // 构建新文件名，即唯一标识符加上原文件的后缀名
        String newFileName = uid + suffix;
        System.out.println(":"+System.getProperty("user.dir"));

        File newFile = new File(System.getProperty("user.dir") + basePath + newFileName);
        // 如果新文件名已存在，则删除原保存的文件，避免重复
        if (newFile.exists()) {
            boolean deleted = localFile.delete();
            // 如果删除原文件失败，则抛出业务异常
            if (!deleted) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        } else {
            // 如果新文件名不存在，则将原保存的文件重命名为新文件名
            boolean renamed = localFile.renameTo(newFile);
            // 如果重命名失败，则抛出业务异常
            if (!renamed) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
        // 添加更多日志
        System.out.println("最终文件位置：" + newFile.getAbsolutePath());
        System.out.println("目录内容：");
        for (String fileName : dir.list()) {
            System.out.println(fileName);
        }
        return newFile;
    }

    /**
     * init基本路径
     *
     * @param b b
     */
    @Value("${file.img}")
    public void initBasePath(String b) {
        basePath = b;
    }
}
