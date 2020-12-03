package com.nowcoder.community.community.dao;

import com.nowcoder.community.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Mapper
public interface CommentMapper {

    //分页的功能
    List<Comment> selectCommentsByEntity(int entityType,int entityId,int offset,int limit);

    int selectCountByEntity(int entityType,int entityId);

}
