package org.shelltea.seeker.util;

import org.junit.Test;

/**
 * Created by xiongshuhong on 14/11/9.
 */
public class ConditionalExpressionTest {
    @Test
    public void testName() throws Exception {
        Object o1 = true ? new Integer(1) : new Double(2.0);

        Object o2;
        if (true) {
            o2 = new Integer(1);
        } else {
            o2 = new Double(2.0);
        }
        System.out.println(o1);
        System.out.println(o2);

        for (int i = 0; i < 10; i++) {
            System.out.println((Integer) i);
        }
    }

    @Test
    public void testString() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
    }
}
