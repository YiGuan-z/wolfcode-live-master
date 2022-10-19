package cn.wolfcode.web.controller;

import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IUserService;
import cn.wolfcode.utils.Log;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    private final IUserService userService;
    private final ServletContext ctx;
    private final IEmployeeService employeeService;
	
	public FileController(IUserService userService, ServletContext ctx, IEmployeeService employeeService) {
		this.userService = userService;
		this.ctx = ctx;
		this.employeeService = employeeService;
	}
	
	@CrossOrigin
    @RequestMapping("/upload")
    public JsonResult<?> upload(@RequestParam MultipartFile file,Long id){
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
            final var syspath = System.getenv("PATH");
            String path = null;
            //判断操作系统
            if (syspath.startsWith("/")) {
                path = realPath + "/" + random;
            } else {
                path = realPath + "\\" + random;
            }
            file.transferTo(new File(path));
            String pathJsp="/upload/"+random;
            return JsonResult.success(pathJsp);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failed(e.getMessage());
        }
    }
}
