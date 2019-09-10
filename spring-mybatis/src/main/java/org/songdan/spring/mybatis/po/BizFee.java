package org.songdan.spring.mybatis.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Songdan
 * @create: 2019-07-23 11:36
 **/
@Data
@NoArgsConstructor
public class BizFee{

    private Fee data;

    private int mode;

    private int bizId;

    private long id;

}
