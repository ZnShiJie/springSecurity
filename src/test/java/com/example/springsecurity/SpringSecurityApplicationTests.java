package com.example.springsecurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecurity.domain.entity.User;
import com.example.springsecurity.mapper.MenuMapper;
import com.example.springsecurity.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    MenuMapper menuMapper;


    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        System.out.println(userService.list());
    }

    @Test
    void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1234"));

        System.out.println(encoder.matches("1234", "$2a$10$cyyiAc0pQUB.XD.ztqpK.O2GAUXl/888..afqJXtlFMbP.J2QHzJm"));
    }

    @Test
    void testMenu() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName, "xin");
        User one = userService.getOne(userLambdaQueryWrapper);
        List<String> strings =
                menuMapper.selectPermsByUerId(one.getId());
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println(strings.size());
    }

}
