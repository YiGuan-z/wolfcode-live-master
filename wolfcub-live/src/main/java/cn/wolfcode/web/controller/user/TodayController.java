package cn.wolfcode.web.controller.user;

import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IUserService;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Today;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 代星宇
 * @date 2022/10/17 16:54
 * @Version cn.wolfcode.web.controller.user
 */
@RestController
@RequestMapping("/todayData")
public class TodayController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping
    public JsonResult<?> todayData(){
        Today todayData = new Today();
        Today todayUserData=userService.selectByCount();
        Today todayEmployeeData=employeeService.selectByCount();
        //TODO 这里弹幕数据可能没对齐
        todayData.setRegister(todayEmployeeData.getRegister()+todayUserData.getRegister());
        todayData.setBarrage(todayEmployeeData.getBarrage());
        todayData.setTipOff(todayEmployeeData.getTipOff());
        todayData.setOnline(todayEmployeeData.getOnline());
        return JsonResult.success(todayData);
    }
}
