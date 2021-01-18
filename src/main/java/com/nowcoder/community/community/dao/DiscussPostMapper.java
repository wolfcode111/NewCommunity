package com.nowcoder.community.community.dao;

import com.nowcoder.community.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);
    //parm是取别名
    //如果只有一个参数，并且在if标签中使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    //显示增加的评论数量
    int updateCommentCount(int id,int commentCount);

    int updateType(int id,int type);

    int updateStatus(int id,int status);
}
