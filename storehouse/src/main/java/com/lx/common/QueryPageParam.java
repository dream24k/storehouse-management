package com.lx.common;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author:lixiang
 * @Description:
 */

@Data
public class QueryPageParam {
    private Integer pageSize = 20;
    private Integer pageNum = 1;

    private HashMap param = new HashMap();
}
