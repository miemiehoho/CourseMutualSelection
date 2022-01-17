package edu.gdut.imis.courseMutualSelection.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author miemiehoho
 * @date 2022/1/17 1:47
 */
@Data
public class UserTopicVo {

    /**
     * 主键
     */
    private String id;

    /**
     * 选题id
     */
    private String topicId;
    /**
     * 选题状态(0待确认、2驳回、1已确认)
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
}
