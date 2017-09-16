package org.incava.jagol;

import static org.incava.ijdk.util.IUtil.*;

public class StringOptionTest extends AbstractOptionTestCase {
    StringOption opt = new StringOption("stropt", "this is the description of stropt");

    public StringOptionTest(String name) {
        super(name);
    }

    public void testDefaultNull() {
        assertEquals("stropt", opt.getLongName());
        assertEquals("this is the description of stropt", opt.getDescription());

        assertValue(null, opt);
    }

    public void testDefaultValue() {
        StringOption opt = new StringOption("stropt", "this is the description of stropt", "defval");
        assertValue("defval", opt);
    }

    public void testShortName() {
        opt.setShortName('d');
        assertShortName('d', opt);
    }

    public void testSetStringValue() {
        doSetValueTest("krisiun", opt);
    }

    public void testSetFromArgsListEqual() {
        doSetFromArgsListTest("hecate", opt, "--stropt=hecate");
    }

    public void testSetFromArgsListSeparateString() {
        doSetFromArgsListTest("opeth", opt, "--stropt", 0, list("opeth"));
    }

    public void testSetFromLongerArgsListEqual() {
        doSetFromArgsListTest("vader", opt, "--stropt=vader", 1, list("--anotheropt"));
    }

    public void testSetFromLongerArgsListSeparateString() {
        doSetFromArgsListTest("wham", opt, "--stropt", 1, list("wham", "--anotheropt"));
    }

    public void testSetInvalidValueDanglingEquals() {
        doSetFromArgsListExceptionExpected(opt, "--stropt=", list("--anotheropt"));
    }
}
