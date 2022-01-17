package edu.gdut.imis.courseMutualSelection.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接受的参数
 *
 * @author miemiehoho
 * @date 2021/11/17 10:55
 */
@Data
@ApiModel("分页参数")
public class PageParams {

    @ApiModelProperty("页数")
    private int page = 1;

    @ApiModelProperty("page_size")
    private int page_size = 10;

}
