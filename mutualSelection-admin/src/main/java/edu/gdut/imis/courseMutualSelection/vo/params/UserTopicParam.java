package edu.gdut.imis.courseMutualSelection.vo.params;

import lombok.Data;

/**
 * @author miemiehoho
 * @date 2022/1/17 4:33
 */
@Data
public class UserTopicParam {

    /**
     * 主键
     */
    private Long id;

    /**
     * 选题状态(0待确认、2驳回、1已确认)
     */
    private Integer topicStatus;
}
