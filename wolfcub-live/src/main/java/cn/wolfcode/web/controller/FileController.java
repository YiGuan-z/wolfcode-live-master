package cn.wolfcode.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author 代星宇
 * @date 2022/10/17 9:43
 * @Version cn.wolfcode.web.controller
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @CrossOrigin
    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        //判空
        String[] split = originalFilename.split("\\.");


        String realPath="C:\\Users\\25135\\Desktop\\zu\\wolfcode-live-master\\wolfcub-live\\src\\main\\webapp\\upload";
        File filepath = new File(realPath);

        if (!filepath.exists()) {
            filepath.mkdir();
        }

        try {
            String path = realPath +"\\"+ UUID.randomUUID()+"."+split[1];
            System.out.println(path);
            file.transferTo(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
