package edu.gdut.imis.courseMutualSelection.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

    import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 管理员_权限关系表
 *
 * @author miemiehoho
 * @date 2022-01-17 05:11:59
 */
@Data
@TableName("cms_admin_permission")
public class AdminPermission implements Serializable{
private static final long serialVersionUID=1L;

    /**
     * 主键
     */
        @TableId
    private Long id;
    /**
     * 管理员id
     */
    private Long adminId;
    /**
     * 权限id
     */
    private Long permissionId;

}
