package com.xiaobai.jpa.student.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学生分页查询Request
 *
 * @author bail
 * @date 2019/05/13
 */
@ApiModel
@Data
public class StudentQueryRequest implements Serializable {

    /**
     * 第几页
     */
    @ApiModelProperty(value = "第几页")
    private Integer pageNum = 0;

    /**
     * 每页显示多少条
     */
    @ApiModelProperty(value = "每页显示多少条")
    private Integer pageSize = 10;

    /**
     * 排序类型
     */
    @ApiModelProperty(value = "排序类型")
    private String sortType;

    /**
     * 多重排序
     * 内容：key: 字段, value: desc或asc
     */
    @ApiModelProperty(value = "多重排序", notes = "内容：key:字段,value:desc或asc")
    private Map<String, String> sortMap = new LinkedHashMap<>();

    /**
     * 填充多个排序规则
     */
    public void putSort(String column, String sort) {
        sortMap.put(column, sort);
    }

    /**
     * 获取分页参数对象
     */
    public PageRequest getPageable() {
        return PageRequest.of(pageNum, pageSize);
    }

    /**
     * 获取分页参数对象与排序条件
     */
    public PageRequest getPageRequest() {
        return PageRequest.of(pageNum, pageSize, getSort());
    }

    /**
     * 获取多重排序
     */
    public Sort getSort() {
        if (MapUtils.isNotEmpty(sortMap)) {
            List<Sort.Order> orders =
                    sortMap.keySet().stream().filter(StringUtils::isNotBlank)
                            .map(column -> new Sort.Order("asc".equalsIgnoreCase(sortMap.get(column))
                                    ? Sort.Direction.ASC : Sort.Direction.DESC,
                                    column))
                            .collect(Collectors.toList());
            return Sort.by(orders);
        }
        return null;
    }

}
