package cn.wolfcode.web.controller;

import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
    @Autowired
    private IUserService userService;
    @Autowired
    private ServletContext ctx;
    @Autowired
    private IEmployeeService employeeService;
    @CrossOrigin
    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile file,Long id){
        String originalFilename = file.getOriginalFilename();
        //判空
        assert originalFilename != null;
        String[] split = originalFilename.split("\\.");


        String realPath = ctx.getRealPath("/upload");
        File filepath = new File(realPath);

        if (!filepath.exists()) {
            filepath.mkdir();
        }

        try {
            String random=UUID.randomUUID()+"."+split[1];
            String path = realPath+"\\" +random;
            file.transferTo(new File(path));
            String pathJsp="/upload/"+random;
            userService.updateByFile(pathJsp,id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @CrossOrigin
    @RequestMapping("/uploade")
    public String uploade(@RequestParam MultipartFile file,Long id){
        String originalFilename = file.getOriginalFilename();
        //判空
        assert originalFilename != null;
        String[] split = originalFilename.split("\\.");


        String realPath = ctx.getRealPath("/upload");
        File filepath = new File(realPath);

        if (!filepath.exists()) {
            filepath.mkdir();
        }

        try {
            String random=UUID.randomUUID()+"."+split[1];
            String path = realPath+"\\" +random;
            file.transferTo(new File(path));
            String pathJsp="/upload/"+random;
            employeeService.updateByFile(pathJsp,id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
