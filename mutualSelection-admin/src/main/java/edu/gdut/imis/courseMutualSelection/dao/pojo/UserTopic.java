package edu.gdut.imis.courseMutualSelection.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 学生选题表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Data
@TableName("cms_user_topic")
public class UserTopic implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 学生id
     */
    private Long userId;
    /**
     * 选题id
     */
    private Long topicId;
    /**
     * 选题状态(待确认、驳回、已确认)
     */
    private Integer topicStatus;
    /**
     * 任务书
     */
    private String taskBook;
    /**
     * 开题报告
     */
    private String openingReport;
    /**
     * 开题报告状态(0：未提交，1：提交待审核，2：审核通过，3：审核不通过)
     */
    private Integer reportStatus;
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
    private Integer deleted;

}
