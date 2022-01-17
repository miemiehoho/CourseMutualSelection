package edu.gdut.imis.courseMutualSelection.dao.mapper;

import edu.gdut.imis.courseMutualSelection.dao.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员信息表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Component
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("SELECT * FROM `cms_permission` where id in(select permission_id from cms_admin_permission where role_id = #{roleId})")
    List<Permission> findPermissionByRoleId(int roleId);
}
