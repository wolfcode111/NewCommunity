package com.nowcoder.community.community.service;

import com.nowcoder.community.community.dao.CommentMapper;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nowcoder.community.community.entity.Comment;

import java.util.List;

@Service
public class CommentService {
    @Autowired(required = false)
    private CommentMapper commentMapper;

    public List<Comment> findCommentsByEntity(int entityType,int entityId,int offset,int limit){
        return commentMapper.selectCommentsByEntity(entityType,entityId,offset,limit);
    }

    public int findCommentCount(int entityType,int entityId){
        return commentMapper.selectCountByEntity(entityType,entityId);
    }

}
