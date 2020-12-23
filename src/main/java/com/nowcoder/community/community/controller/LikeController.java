package com.nowcoder.community.community.controller;

import com.nowcoder.community.community.entity.Event;
import com.nowcoder.community.community.entity.User;
import com.nowcoder.community.community.event.EventProducer;
import com.nowcoder.community.community.service.LikeService;
import com.nowcoder.community.community.util.CommunityConstant;
import com.nowcoder.community.community.util.CommunityUtil;
import com.nowcoder.community.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LikeController  implements CommunityConstant {

    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private EventProducer eventProducer;

    @RequestMapping(path="/like",method = RequestMethod.POST)
    //由于是异步请求，所以要加上这个
    @ResponseBody
    public String like(int entityType,int entityId,int entityUserId,int postId){
        User user = hostHolder.getUser();  //获取当前的用户

        //点赞
        likeService.like(user.getId(),entityType,entityId,entityUserId);
        //数量
        long likeCount = likeService.findEntityLikeCount(entityType,entityId);
        //点赞的状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(),entityType,entityId);
        //返回结果
        Map<String,Object> map = new HashMap<>();
        map.put("likeCount",likeCount);
        map.put("likeCount",likeCount);
        map.put("likeStatus",likeStatus);

        //触发点赞事件
        if(likeStatus==1){
            Event event = new Event()
                    .setTopic(TOPIC_LIKE)
                    .setUserId(hostHolder.getUser().getId())
                    .setEntityType(entityType)
                    .setEntityId(entityId)
                    .setEntityUserId(entityUserId)
                    .setData("postId",postId);
            eventProducer.fireEvent(event);
        }

        return CommunityUtil.getJSONString(0,null,map);
        //返回Json的数据，因为是要做异步刷新

    }



}
