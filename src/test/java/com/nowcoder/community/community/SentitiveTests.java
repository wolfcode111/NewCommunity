package com.nowcoder.community.community;

import com.nowcoder.community.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SentitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSenstiveFileter(){
        String text = "赌妮妮博，可以嫖娼，可以开票，哈哈哈哈! ";
        text =sensitiveFilter.filter(text);
        System.out.println(text);
    }

}
