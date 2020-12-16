package com.nowcoder.community.community.dao;

import com.nowcoder.community.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
@Deprecated
//@Deprecated若某类或某方法加上该注解之后，表示此方法或类不再建议使用，
// 调用时也会出现删除线，但并不代表不能用，只是说，不推荐使用，因为还有更好的方法可以调用。
public interface LoginTicketMapper {

      @Insert({
              "insert into login_ticket(user_id,ticket,status,expired) ",
              "values(#{userId},#{ticket},#{status},#{expired})"
      })
      @Options(useGeneratedKeys = true,keyProperty = "id")
      int insertLoginTicket(LoginTicket loginTicket);

      @Select({
              "select id,user_id,ticket,status,expired ",
              "from login_ticket where ticket=#{ticket}"
      })
      LoginTicket selectByTicket(String ticket);

      @Update({
              "update login_ticket set status=#{status} where ticket=#{ticket} "
      })
      int updateStatus(String ticket,int status);


}
