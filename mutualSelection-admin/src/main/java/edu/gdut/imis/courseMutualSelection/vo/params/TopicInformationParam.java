package edu.gdut.imis.courseMutualSelection.vo.params;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author miemiehoho
 * @date 2022/1/17 3:21
 */
@Data
public class TopicInformationParam {

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
     * 人数限额
     */
    private Integer numLimit;
}
