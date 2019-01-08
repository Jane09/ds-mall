package com.ds.mall.api.vo.search;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/8 11:11
 */
@Getter
@Setter
public class IndexVo implements Comparable<IndexVo>, Serializable {

    private Long id;
    private String title;
    private String keywords;
    private String descripton;
    private String postDate;
    private String url;
    /*相似度*/
    private float score;

    @Override
    @ParametersAreNonnullByDefault
    public int compareTo(IndexVo o) {
        if(this.score < o.getScore()){
            return 1;
        }else if(this.score > o.getScore()){
            return -1;
        }
        return 0;
    }
}
