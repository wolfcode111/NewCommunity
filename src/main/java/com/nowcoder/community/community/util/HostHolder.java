package com.nowcoder.community.community.util;

import com.nowcoder.community.community.entity.User;
import org.springframework.stereotype.Component;

/*
* 持有用户信息，用于替代session对象
* */
@Component
public class HostHolder {
      private ThreadLocal<User> users = new ThreadLocal<>();

      public void setUsers(User user){
          users.set(user);
      }

      public User getUser(){
          return users.get();
      }

      public void clear(){
          users.remove();
      }
}
