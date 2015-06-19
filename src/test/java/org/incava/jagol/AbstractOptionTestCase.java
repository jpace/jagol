package org.incava.jagol;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

public class AbstractOptionTestCase extends TestCase {
    public AbstractOptionTestCase(String name) {
        super(name);
    }

    public <T> void assertShortName(Character exp, Option<T> opt) {
        assertEquals(exp, opt.getShortName());
    }

    public <T> void assertValue(T exp, Option<T> opt) {
        String msg = opt.toString();
        assertEquals(msg, exp, opt.getValue());
    }

    public <T> void doSetFromArgsListTest(T exp, Option<T> opt, String arg) {
        doSetFromArgsListTest(exp, opt, arg, 0, new ArrayList<String>());
    }

    public <T> void doSetFromArgsListTest(T expValue, Option<T> opt, String arg, int expArgSize, List<String> otherArgs) {
        try {
            boolean processed = opt.set(arg, otherArgs);
            assertEquals("option processed", true, processed);
            assertValue(expValue, opt);
            assertEquals("argument removed from list", expArgSize, otherArgs.size());
        }
        catch (OptionException ite) {
            fail(ite);
        }
    }

    public <T> void doSetFromArgsListExceptionExpected(Option<T> opt, String arg, List<String> otherArgs) {
        try {
            opt.set(arg, otherArgs);
            fail("exception expected");
        }
        catch (OptionException ite) {
        }
    }

    public <T> void doSetValueFromStringTest(Boolean expectException, T expectedValue, Option<T> opt, String value) {
        try {
            opt.setValueFromString(value);
            if (expectException) {
                fail("exception expected");
            }
            else {
                assertValue(expectedValue, opt);
            }
        }
        catch (InvalidTypeException ite) {
            if (!expectException) {
                fail(ite);
            }
        }
    }
    
    public <T> void doSetValueTest(T value, Option<T> opt) {
        opt.setValue(value);
        assertValue(value, opt);
    }

    public void fail(Exception e) {
        fail(e.getMessage());
    }
}
