package org.songdan.spring.mybatis.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Songdan
 * @create: 2019-07-23 11:37
 **/
@Data
@NoArgsConstructor
public class BizOriginFee {

    private int fee;

    private int minAmount;

    private int mode;

    private int bizId;

    private long id;


}
