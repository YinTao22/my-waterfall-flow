package com.example.mylife.service;

import com.example.mylife.model.Images;
import com.example.mylife.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ImageRepository imageRepository;

    private static final List<String> ALLOWED_EXTENSIONS = List.of(
            ".jpg", ".jpeg", ".png", ".gif", ".webp"
    );

    public Map<String, Object> saveImageToDisk(MultipartFile file,String text) {
        try {
            // 验证文件类型
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isBlank()) {
                throw new IllegalArgumentException("无效的文件名");
            }

            // 验证文件扩展名
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                throw new IllegalArgumentException("不支持的文件类型");
            }

            // 创建上传目录
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // 生成唯一文件名
            String uniqueFileName = generateUniqueFileName(originalFilename);

            // 保存文件到磁盘
            Path targetPath = uploadDir.resolve(uniqueFileName);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }

            // 读取图片尺寸
            BufferedImage bufferedImage = ImageIO.read(new File(targetPath.toString()));
            if (bufferedImage == null) {
                Files.deleteIfExists(targetPath); // 删除无效文件
                throw new IllegalArgumentException("无效的图片文件");
            }

            // 保存到数据库
            Images image = new Images();
            image.setImageName(uniqueFileName);
            image.setImageAlt(text);
            image.setImageUrl("/uploads/" + uniqueFileName); // 需要配置静态资源映射
            image.setWidth(bufferedImage.getWidth());
            image.setHeight(bufferedImage.getHeight());
            imageRepository.save(image);

            // 返回详细信息
            return Map.of(
                    "status", "success",
                    "filename", uniqueFileName,
                    "url", image.getImageUrl(),
                    "width", image.getWidth(),
                    "height", image.getHeight()
            );

        } catch (IOException e) {
            throw new RuntimeException("文件处理失败: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("文件验证失败: " + e.getMessage(), e);
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }
}