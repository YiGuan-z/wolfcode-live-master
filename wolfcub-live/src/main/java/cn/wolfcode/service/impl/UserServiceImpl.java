package cn.wolfcode.service.impl;

import cn.wolfcode.domain.User;
import cn.wolfcode.mapper.UserMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IUserService;
import cn.wolfcode.utils.FormatDateUtils;
import cn.wolfcode.utils.TokenManager;
import cn.wolfcode.vo.LoginInfo;
import cn.wolfcode.vo.Page;
import cn.wolfcode.vo.Today;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserMapper mapper;

    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Page<User> query(QueryObject qo) {
        Long total = mapper.selectForCount(qo);
        if (total == 0) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<User> users = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), total, users);
    }

    @Override
    public void save(User obj) {
        mapper.insert(obj);
    }

    @Override
    public void updateById(User obj) {
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public void deleteById(Long id, boolean deleted) {
        mapper.deleteByPrimaryKey(id, deleted);
    }

    @Override
    public User findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void changeStatus(Long id, String status) {
        mapper.changeStatus(id, status);
    }

    @Override
    public Boolean repetition(String username) {
        //判断用户名是否已存在
        User userUsername=mapper.selectByUsername(username);

        //false为不存在
        if(userUsername==null){
            return false;
        }
        return true;
    }

    @Override
    public Today selectByCount() {
        Today today = new Today();
        //当前在线
        //今日注册
        Integer register = mapper.selectCountByDate(FormatDateUtils.minNowTime(), FormatDateUtils.maxNowTime());
        today.setRegister(register);
        //今日弹幕
        //今日举报
        return today;
    }

    @Override
    public String login(String username, String password, String verifyCode, String code) {
        // 1. 基于用户名去数据库查询用户信息
        User user = mapper.selectByUsername(username);
        // 2. 如果信息不存在, 提示用户名或密码错误
        if (user == null) {
            log.error("[员工服务] 登录失败, 用户名输入错误, 用户名={}, 提交的密码={}", username, password);
            throw new RuntimeException("用户名或密码错误");
        }
        // 3. 判断密码是否正确, 如果不正确, 提示用户名或密码错误
        if (!password.equals(user.getPassword())) {
            log.error("[员工服务] 登录失败, 密码输入错误, 用户名={}, 提交的密码={}", username, password);
            throw new RuntimeException("用户名或密码错误");
        }
        if(code==null){
            code="1111";
        }
        //验证码判断
        if (!code.equalsIgnoreCase(verifyCode)) {
            throw new RuntimeException("验证码错误!!!");
        }
        //状态为1时拒绝登录，状态为0登录。
        if ("1".equals(user.getStatus())) {
            throw new RuntimeException("你已被封号");
        }

        // 4. 生成 token, 并将当前用户存入 map
        String token = TokenManager.getToken();

        LoginInfo loginInfo = new LoginInfo();
        // 将 employee 中所有与 loginInfo 对象中属性名相同的属性值全部 copy 到 loginInfo
        BeanUtils.copyProperties(user, loginInfo);

        TokenManager.setInfo(token, loginInfo);
        // 5. 返回 token
        return token;
    }
}
