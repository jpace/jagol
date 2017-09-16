package org.incava.jagol;

import static org.incava.ijdk.util.IUtil.*;

public class IntegerOptionTest extends AbstractOptionTestCase {
    IntegerOption opt = new IntegerOption("intopt", "this is the description of intopt");

    public IntegerOptionTest(String name) {
        super(name);
    }

    public void testDefaultNull() {
        assertEquals("intopt", opt.getLongName());
        assertEquals("this is the description of intopt", opt.getDescription());
        assertValue(null, opt);
    }

    public void testDefaultValue() {
        IntegerOption opt = new IntegerOption("intopt", "this is the description of intopt", new Integer(1012));
        assertValue(1012, opt);
    }
    
    public void testSetIntegerValue() {
        doSetValueTest(14, opt);
    }

    public void testSetInvalidValueString() {
        doSetValueFromStringTest(true, null, opt, "fred");
    }

    public void testSetInvalidValueFloatingPoint() {
        doSetValueFromStringTest(true, null, opt, "1.4");
    }

    public void testSetValidValueNegative() {
        doSetValueFromStringTest(false, -987, opt, "-987");
    }

    public void testSetFromArgsListEqual() {
        doSetFromArgsListTest(444, opt, "--intopt=444");
    }

    public void testSetFromArgsListSeparateString() {
        doSetFromArgsListTest(41, opt, "--intopt", 0, list("41"));
    }

    public void testSetFromLongerArgsListEqual() {
        doSetFromArgsListTest(666, opt, "--intopt=666", 1, list("--anotheropt"));
    }

    public void testSetFromLongerArgsListSeparateString() {
        doSetFromArgsListTest(1234, opt, "--intopt", 1, list("1234", "--anotheropt"));
    }

    public void testSetInvalidValueDanglingEquals() {
        doSetFromArgsListExceptionExpected(opt, "--intopt=", list("--anotheropt"));
    }
}
