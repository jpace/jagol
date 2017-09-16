package org.incava.jagol;

import java.util.List;

public class OptionTest extends AbstractOptionTestCase {    
    public OptionTest(String name) {
        super(name);
    }

    public void testDefault() {
        Option<Object> opt = new Option<Object>("name", "this is the description of name") {
                public boolean set(String arg, List<String> args) throws OptionException {
                    return false; 
                }
                
                public void setValueFromString(String value) throws InvalidTypeException {
                }
            };
        assertEquals("name", opt.getLongName());
        assertEquals("this is the description of name", opt.getDescription());

        opt.setShortName('n');
        assertShortName('n', opt);
    }
}
