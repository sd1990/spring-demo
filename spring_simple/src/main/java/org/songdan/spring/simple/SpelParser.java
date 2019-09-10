package org.songdan.spring.simple;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: Songdan
 * @create: 2019-09-10 11:00
 **/
public class SpelParser {

    ExpressionParser parser = new SpelExpressionParser();

    public Boolean parseBoolean(String str,Object target) {
        Expression expression = parser.parseExpression(str);
        return expression.getValue(target, Boolean.class);
    }

    public Expression parse(String str) {
        return parser.parseExpression(str);
    }

}
