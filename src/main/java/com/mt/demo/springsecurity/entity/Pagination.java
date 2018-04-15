package com.mt.demo.springsecurity.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Pagination
 *
 * @author MT.LUO
 * 2018/1/20 23:57
 * @Description:
 */
@Data
public class Pagination implements Serializable {
    private int pageNumber;
    private int pageSize;
    private String sortOrder;
    private String sortKey;
    private int searchFlag;
    private JSONObject search;

    public Sort getSort() {
        Sort sort = null;
        String sortKey = "id";
        if (this.getSortKey() != null) {
            if (this.getSortKey().equals("userName")) {
                sortKey = "userName";
            }

        }
        if ((this.getSortOrder() != null) && (this.getSortOrder().equals("DESC") || this.getSortOrder().equals("desc"))) {

            sort = new Sort(Sort.Direction.DESC, sortKey);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortKey);
        }
        return sort;
    }



}
