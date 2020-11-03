
package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class TestMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser() {
//        User user = userMapper.selectById(101);
        User user = userMapper.selectByName("liubei");
        System.out.println(user);
        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("huahongzhi");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setSalt("123");
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public  void testUpdateUser() {
        int rows = userMapper.updatePassword(150, "654321");
        System.out.println(rows);

    }

    @Test
    public void testSelectPosts() {
        List<DiscussPost> discussPost = discussPostMapper.selectDiscussPost(101, 0,10);
        for(DiscussPost post : discussPost){
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(101);
        System.out.println(rows);
    }


}
