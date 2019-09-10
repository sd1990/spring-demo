package org.songdan.spring.mybatis.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Songdan
 * @create: 2019-07-23 11:31
 **/
@Data
@NoArgsConstructor
public class Discount extends Versionable{

    private int discountFee;

    private int discountMinAmount;

    private int discountPlatformFee;

    private int discountBusinessSupportFee;

    private int discountTechFee;

    private int discountPerformanceFee;

}
