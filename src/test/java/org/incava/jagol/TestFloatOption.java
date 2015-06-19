package org.incava.jagol;

import static org.incava.ijdk.util.IUtil.*;

public class TestFloatOption extends AbstractOptionTestCase {
    FloatOption opt = new FloatOption("fltopt", "this is the description of fltopt");

    public TestFloatOption(String name) {
        super(name);
    }

    public void testDefaultNull() {
        assertEquals("fltopt", opt.getLongName());
        assertEquals("this is the description of fltopt", opt.getDescription());
        assertValue(null, opt);
    }

    public void testDefaultValue() {
        FloatOption opt = new FloatOption("fltopt", "this is the description of fltopt", new Float(10.12F));
        assertValue(10.12F, opt);
    }

    public void testShortName() {
        opt.setShortName('d');
        assertShortName('d', opt);
    }

    public void testSetFloatValue() {
        doSetValueTest(1.4F, opt);
    }    

    public void testSetInvalidValueString() {
        doSetValueFromStringTest(true, null, opt, "fred");
    }

    public void testSetInvalidValue() {
        doSetValueFromStringTest(true, null, opt, "1.4.8");
    }

    public void testSetValidValueNegative() {
        doSetValueFromStringTest(false, -9.87F, opt, "-9.87");
    }

    public void testSetValidValueNoLeadingZero() {
        doSetValueFromStringTest(false, 0.87F, opt, ".87");
    }

    public void testSetFromArgsListEqual() {
        doSetFromArgsListTest(4.44F, opt, "--fltopt=4.44");
    }

    public void testSetFromArgsListSeparateString() {
        doSetFromArgsListTest(41.82F, opt, "--fltopt", 0, list("41.82"));
    }

    public void testSetFromLongerArgsListEqual() {
        doSetFromArgsListTest(3.1415F, opt, "--fltopt=3.1415", 1, list("--anotheropt"));
    }

    public void testSetFromLongerArgsListSeparateString() {
        doSetFromArgsListTest(1234.567890F, opt, "--fltopt", 1, list("1234.567890", "--anotheropt"));
    }

    public void testSetInvalidValueDanglingEquals() {
        doSetFromArgsListExceptionExpected(opt, "--fltopt=", list("--anotheropt"));
    }
}
