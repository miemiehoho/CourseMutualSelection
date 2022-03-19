package edu.gdut.imis.courseMutualSelection.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

    import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 课程表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
@Data
@TableName("cms_course_imformation")
public class CourseImformation implements Serializable{
private static final long serialVersionUID=1L;

    /**
     * 主键
     */
        @TableId
    private Long id;
    /**
     * 课程分类
     */
    private Integer classify;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程简介
     */
    private String description;
    /**
     * 课程学分
     */
    private Integer credit;
    /**
     * 创建人
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 最后修改人
     */
    private Long lastUserId;
    /**
     * 最后修改时间
     */
    private Long lastTime;
    /**
     * 是否删除
     */
    private Boolean deleted;

}
