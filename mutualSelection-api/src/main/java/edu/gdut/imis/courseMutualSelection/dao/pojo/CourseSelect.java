package edu.gdut.imis.courseMutualSelection.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

    import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 可选课程表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
@Data
@TableName("cms_course_select")
public class CourseSelect implements Serializable{
private static final long serialVersionUID=1L;

    /**
     * 主键
     */
        @TableId
    private Long id;
    /**
     * 开课学期id
     */
    private Long semesterId;
    /**
     * 授课教师id
     */
    private Long teacherId;
    /**
     * 备注
     */
    private String note;
    /**
     * 限选人数
     */
    private Integer numLimit;
    /**
     * 可选人数
     */
    private Integer selectedNum;
    /**
     * 选课开始时间
     */
    private Long startTime;
    /**
     * 选课结束时间
     */
    private Long endTime;
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
