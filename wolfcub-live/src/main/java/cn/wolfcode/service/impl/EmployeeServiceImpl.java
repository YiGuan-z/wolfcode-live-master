package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.utils.FormatDateUtils;
import cn.wolfcode.utils.TokenManager;
import cn.wolfcode.vo.LoginInfo;
import cn.wolfcode.vo.Page;
import cn.wolfcode.vo.Today;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {

    //private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Page<Employee> query(QueryObject qo) {
        long total = mapper.selectForCount(qo);
        if (total == 0) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<Employee> employees = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), total, employees);
    }

    @Override
    public void save(Employee obj) {
        mapper.insert(obj);
    }

    @Override
    public void updateById(Employee obj) {
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Employee findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> findAll() {
        return mapper.selectAll();
    }

    @Override
    public String login(String username, String password) {
        //判段是否重复登录
        TokenManager.logged(username);
        // 1. 基于用户名去数据库查询用户信息
        Employee employee = mapper.selectByUsername(username);
        // 2. 如果信息不存在, 提示用户名或密码错误
        if (employee == null) {
            log.error("[员工服务] 登录失败, 用户名输入错误, 用户名={}, 提交的密码={}", username, password);
            throw new RuntimeException("用户名或密码错误");
        }
        // 3. 判断密码是否正确, 如果不正确, 提示用户名或密码错误
        if (!password.equals(employee.getPassword())) {
            log.error("[员工服务] 登录失败, 密码输入错误, 用户名={}, 提交的密码={}", username, password);
            throw new RuntimeException("用户名或密码错误");
        }
        // 4. 生成 token, 并将当前用户存入 map
        String token = TokenManager.getToken();

        LoginInfo loginInfo = new LoginInfo();
        // 将 employee 中所有与 loginInfo 对象中属性名相同的属性值全部 copy 到 loginInfo
        BeanUtils.copyProperties(employee, loginInfo);

        TokenManager.setInfo(token, loginInfo);
        System.out.println(TokenManager.TOKEN_NAME);
        // 5. 返回 token
        return token;
    }
    
    @Override
    public int resetPassword(String userId, String password, LoginInfo info) {
        final var i = mapper.resetPassword(Long.valueOf(userId), password);
        log.info("id为[{}]的账户[{}]尝试操作id为[{}]的账户密码",info.getId(),info.getUsername(),userId);
        if (i!=0){
            log.info("id为[{}]的账户[{}]已操作id为[{}]的账户密码",info.getId(),info.getUsername(),userId);
        }else {
            log.info("id为[{}]的账户[{}]操作id为[{}]的账户密码失败",info.getId(),info.getUsername(),userId);
            throw new RuntimeException("更新失败");
        }
        return i;
    }

    @Override
    public Today selectByCount() {
        Today today = new Today();
        int length = TokenManager.getSize();

        //当前在线
        today.setOnline(length);
        //今日注册
        Integer register = mapper.selectCountByDate(FormatDateUtils.minNowTime(), FormatDateUtils.maxNowTime());
        today.setRegister(register);
        //今日弹幕
        Integer barrage = mapper.selectCountByBulletDate(FormatDateUtils.minNowTime(), FormatDateUtils.maxNowTime());
        today.setBarrage(barrage);
        //今日举报
//        mapper.selectCount
        return today;
    }
}
