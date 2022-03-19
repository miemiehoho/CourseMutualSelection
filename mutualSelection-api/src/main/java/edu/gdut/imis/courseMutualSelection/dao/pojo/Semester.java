package edu.gdut.imis.courseMutualSelection.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

    import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 学期表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
@Data
@TableName("cms_semester")
public class Semester implements Serializable{
private static final long serialVersionUID=1L;

    /**
     * 主键
     */
        @TableId
    private Long id;
    /**
     * 学期名
     */
    private String name;
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
