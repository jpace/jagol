package org.incava.jagol;

import static org.incava.ijdk.util.IUtil.*;

public class DoubleOptionTest extends AbstractOptionTestCase {
    DoubleOption opt = new DoubleOption("dblopt", "this is the description of dblopt");

    public DoubleOptionTest(String name) {
        super(name);
    }

    public void testDefaultNull() {
        assertEquals("dblopt", opt.getLongName());
        assertEquals("this is the description of dblopt", opt.getDescription());

        assertValue(null, opt);
    }

    public void testDefaultValue() {
        DoubleOption opt = new DoubleOption("dblopt", "this is the description of dblopt", new Double(6.66));
        assertValue(6.66, opt);
    }

    public void testShortName() {
        opt.setShortName('d');
        assertShortName('d', opt);
    }

    public void testSetDoubleValue() {
        doSetValueTest(1.4, opt);
    }

    public void testSetInvalidValueString() {
        doSetValueFromStringTest(true, null, opt, "fred");
    }

    public void testSetInvalidValue() {
        doSetValueFromStringTest(true, null, opt, "1.4.8");
    }

    public void testSetValidValueNegative() {
        doSetValueFromStringTest(false, -9.87, opt, "-9.87");
    }

    public void testSetValidValueNoLeadingZero() {
        doSetValueFromStringTest(false, 0.87, opt, ".87");
    }

    public void testSetFromArgsListEqual() {
        doSetFromArgsListTest(4.44, opt, "--dblopt=4.44");
    }

    public void testSetFromArgsListSeparateString() {
        doSetFromArgsListTest(41.82, opt, "--dblopt", 0, list("41.82"));
    }

    public void testSetFromLongerArgsListEqual() {
        doSetFromArgsListTest(3.1415, opt, "--dblopt=3.1415", 1, list("--anotheropt"));
    }

    public void testSetFromLongerArgsListSeparateString() {
        doSetFromArgsListTest(1234.567890, opt, "--dblopt", 1, list("1234.567890", "--anotheropt"));
    }

    public void testSetInvalidValueDanglingEquals() {
        doSetFromArgsListExceptionExpected(opt, "--dblopt=", list("--anotheropt"));
    }
}
