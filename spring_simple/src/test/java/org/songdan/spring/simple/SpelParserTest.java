package org.songdan.spring.simple;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelParserTest {

    private SpelParser spelParser = new SpelParser();

    @Test
    public void parseBoolean() {
        SpelParser spelParser = new SpelParser();
        Human human = new Human();
        human.setAge(11);
        Assert.assertTrue(spelParser.parseBoolean("age>10", human));
    }

    @Test
    public void reflect() {
        String exp = "#num>10";
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("num", 11);
        Boolean result = spelParser.parse(exp).getValue(context, Boolean.class);
        Assert.assertTrue(result);
    }

    class ReflectClass {


        public void method(long num, Human human) {

        }
    }

}