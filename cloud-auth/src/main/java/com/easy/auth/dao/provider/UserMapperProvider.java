package com.easy.auth.dao.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class UserMapperProvider {

    /**
     * 联表查询 user_account和 user_info 两个表的用户信息，返回List<UserInfoVO>
     *
     * @param ids 用户 id 集合
     * @return List<UserInfoVO>
     */
    public String selectUserInfoList(@Param("ids") List<String> ids) {
        SQL sql = new SQL();
        return sql.SELECT("ua.id AS id, ua.username AS username, ua.email AS email, ua.phone AS phone, ua.client AS client, ua.status AS status, ua.create_time AS createTime, ui.nickname AS nickname, ui.avatar AS avatar, ui.sex AS sex ")
                .FROM("user_account ua")
                .LEFT_OUTER_JOIN("user_info ui ON ua.id = ui.user_id")
                .WHERE("ua.id IN (" + String.join(",", ids.stream().toList()) + " )")
                .toString();
    }
}
