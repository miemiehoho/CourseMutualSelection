package edu.gdut.imis.courseMutualSelection.vo;

import lombok.Data;

/**
 * @author miemiehoho
 * @date 2022/1/17 3:35
 */
@Data
public class TopicInformationVo {

    private String id;
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
}
