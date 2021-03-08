package com.nowcoder.community.community.actuator;

import com.nowcoder.community.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


//这样通过  http://localhost:8080/community/actuator/database
//查看我们数据库有没有连接成功

@Component
@Endpoint(id = "database")
public class DatabaseEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseEndpoint.class);

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @ReadOperation   //这个是针对于get请求的
    public String checkConnection(){
        try (
                Connection conn = dataSource.getConnection();
                ){
            return CommunityUtil.getJSONString(0,"获取连接成功!");

        }catch (SQLException e){
            logger.error("获取连接失败："+e.getMessage());
            return CommunityUtil.getJSONString(1,"获取连接失败!");
        }
    }

}
