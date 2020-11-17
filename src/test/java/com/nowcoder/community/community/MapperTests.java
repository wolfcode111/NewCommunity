package com.nowcoder.community.community;

import com.nowcoder.community.community.dao.DiscussPostMapper;
import com.nowcoder.community.community.dao.UserMapper;
import com.nowcoder.community.community.entity.DiscussPost;
import com.nowcoder.community.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
     private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user =userMapper.selectById(101);
        System.out.println(user);
        userMapper.selectByName("徐");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user=new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setType(1);
        user.setStatus(1);
        user.setActivationCode("1234");
        user.setHeaderUrl("http:sss");
        user.setCreateTime(new Date());

        int rows=userMapper.insertUser(user);
        System.out.println("这是我新生成的id  "+rows);
        System.out.println(user.getId());

    }

    @Test
    public void testSelectPost(){
      List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149,0,10);
        for(DiscussPost post : list){
            System.out.println(post);
        }
        int rows=discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

}

