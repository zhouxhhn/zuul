package cn.siyueli.platform.zuul.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

public class PatternTest {

    @Test
    public void testPattern() {
        //boolean result = Pattern.matches("(\\s*\\S*)*快餐店(\\s*\\S*)*", StringUtils.trimToEmpty(" bv快餐店aa"));

        boolean result = Pattern.matches("^/api/[\\s\\S]+/v2/api-docs$", "/api/siyueli-member/v2/api-docs");

        Assert.assertTrue(result);
    }
}
