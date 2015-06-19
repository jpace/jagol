package org.incava.jagol;

import static org.incava.ijdk.util.IUtil.*;

public class TestNonBooleanOption extends AbstractOptionTestCase {    
    NonBooleanOption<Object> opt = new NonBooleanOption<Object>("nbopt", "this is the description of nbopt") {
            public void setValueFromString(String value) throws InvalidTypeException {        
            }

            protected String getType() {
                return "";
            }
        };
    
    public TestNonBooleanOption(String name) {
        super(name);
    }

    public void testSetFromArgsListEqual() {
        doSetFromArgsListTest(null, opt, "--nbopt=444");
    }

    public void testSetFromArgsListSeparateString() {
        doSetFromArgsListTest(null, opt, "--nbopt", 0, list("41"));
    }

    public void testSetFromLongerArgsListEqual() {
        doSetFromArgsListTest(null, opt, "--nbopt=666", 1, list("--anotheropt"));
    }

    public void testSetFromLongerArgsListSeparateString() {
        doSetFromArgsListTest(null, opt, "--nbopt", 1, list("1234", "--anotheropt"));
    }

    public void testSetInvalidValueDanglingEquals() {
        doSetFromArgsListExceptionExpected(opt, "--nbopt=", list("--anotheropt"));
    }
}
