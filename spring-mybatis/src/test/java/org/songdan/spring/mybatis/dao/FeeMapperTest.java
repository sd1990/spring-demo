package org.songdan.spring.mybatis.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.songdan.spring.mybatis.aop.ReadControl;
import org.songdan.spring.mybatis.po.BizOriginFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FeeMapperTest {

    @Autowired
    private FeeMapper feeMapper;

    @Autowired
    private OriginFeeMapper originFeeMapper;

    @Test
    public void test() {
//        ConvertorHolder.set(new OriginFeeConvertor());
//        BizOriginFee newFee = feeMapper.selectByBizId(1);
//        ConvertorHolder.remove();
        BizOriginFee oldFee = originFeeMapper.selectByBizId(1);
        System.out.println(oldFee);
//        Assert.assertEquals(oldFee, newFee);
    }

    @Test
    public void test2() throws NoSuchMethodException {
        Method selectByBizId = OriginFeeMapper.class.getDeclaredMethod("selectByBizId", int.class);
        System.out.println(AnnotationUtils.findAnnotation(selectByBizId,ReadControl.class));
        System.out.println(selectByBizId.isAnnotationPresent(ReadControl.class));
    }

}