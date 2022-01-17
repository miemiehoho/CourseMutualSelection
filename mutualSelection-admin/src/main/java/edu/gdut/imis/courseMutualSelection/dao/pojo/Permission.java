package edu.gdut.imis.courseMutualSelection.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

    import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 权限表
 *
 * @author miemiehoho
 * @date 2022-01-17 05:11:59
 */
@Data
@TableName("cms_permission")
public class Permission implements Serializable{
private static final long serialVersionUID=1L;

    /**
     * 主键
     */
        @TableId
    private Long id;
    /**
     * 权限名
     */
    private String name;
    /**
     * 路径
     */
    private String path;
    /**
     * 描述
     */
    private String description;

}
