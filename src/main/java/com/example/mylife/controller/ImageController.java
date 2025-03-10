package com.example.mylife.controller;

import com.example.mylife.model.Images;
import com.example.mylife.repository.ImageRepository;
import com.example.mylife.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 显示图片上传界面（image.html）
     */

    @GetMapping("/images")
    public String showUploadForm() {
        return "images"; // 返回 Thymeleaf 模板
    }
    /**
     * 处理图片上传请求
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file" ) MultipartFile file, @RequestParam("text") String text) {
        fileUploadService.saveImageToDisk(file,text);
        return ResponseEntity.ok("图片上传成功");
    }
    /**
     * 显示图片瀑布流界面（index.html）
     */
    @GetMapping("/")
    public String showIndexPage(Model model) {
        // 查询所有图片数据
        List<Images> images = imageRepository.findAll();
        model.addAttribute("images", images);
        return "index"; // 返回 Thymeleaf 模板
    }



}
