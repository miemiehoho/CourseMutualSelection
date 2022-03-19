package edu.gdut.imis.courseMutualSelection.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author miemiehoho
 * @date 2022/3/16 15:09
 */
@Data
public class CourseSelectVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 开课学期id
     */
    private String semesterName;
    /**
     * 授课教师id
     */
    private String teacherName;
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

}
