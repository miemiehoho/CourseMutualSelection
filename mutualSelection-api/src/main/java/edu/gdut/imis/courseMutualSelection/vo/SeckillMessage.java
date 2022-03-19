package edu.gdut.imis.courseMutualSelection.vo;

import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseSelect;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author miemiehoho
 * @date 2022/3/18 15:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillMessage {
    User user;
    Long courseSelectId;
}
