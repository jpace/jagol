package org.incava.jagol;

public class BooleanOptionTest extends AbstractOptionTestCase {
    private BooleanOption opt = new BooleanOption("boolopt", "this is the description of boolopt");

    public BooleanOptionTest(String name) {
        super(name);
    }

    public void testDefaultNull() {
        assertEquals("boolopt", opt.getLongName());
        assertEquals("this is the description of boolopt", opt.getDescription());

        assertValue(null, opt);
    }

    public void testDefaultValue() {
        BooleanOption opt = new BooleanOption("boolopt", "this is the description of boolopt", Boolean.TRUE);
        assertValue(Boolean.TRUE, opt);
    }

    public void testShortName() {
        opt.setShortName('n');
        assertShortName('n', opt);
    }

    public void testSetBooleanValue() {
        doSetValueTest(Boolean.TRUE, opt);
        doSetValueTest(Boolean.FALSE, opt);
    }

    public void testSetFromArgsListPositive() {
        doSetFromArgsListTest(true, opt, "--boolopt");
    }

    public void testSetFromArgsListNegativeDash() {
        doSetFromArgsListTest(false, opt, "--noboolopt");
    }

    public void testSetFromArgsListNegativeNoDash() {
        doSetFromArgsListTest(false, opt, "--noboolopt");
    }
}
