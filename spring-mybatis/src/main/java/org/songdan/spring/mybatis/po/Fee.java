package org.songdan.spring.mybatis.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Songdan
 * @create: 2019-07-23 11:34
 **/
@Data
@NoArgsConstructor
public class Fee extends Versionable{

    private OriginFee originFee;

    private Discount discount;


}
