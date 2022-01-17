package edu.gdut.imis.courseMutualSelection.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 选题表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Data
@TableName("cms_topic_information")
public class TopicInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 教师id
     */
    private Long teacherId;
    /**
     * 题目
     */
    private String topic;
    /**
     * 英文题目
     */
    private String englishTopic;
    /**
     * 简介
     */
    private String description;
    /**
     * 备注
     */
    private String note;
    /**
     * 已选人数
     */
    private Integer selectedNum;
    /**
     * 人数限额
     */
    private Integer numLimit;
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
