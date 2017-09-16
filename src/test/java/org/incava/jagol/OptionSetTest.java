package org.incava.jagol;

import java.io.File;
import java.util.List;
import junit.framework.TestCase;
import org.incava.ijdk.io.IO;
import static org.incava.ijdk.util.IUtil.*;

public class OptionSetTest extends TestCase {
    OptionSet     optSet     = new OptionSet("app",            "this application does wonderful things");
    IntegerOption intOpt     = new IntegerOption("intopt",     "this option takes an integer argument");
    StringOption  stringOpt  = new StringOption("stringopt",   "this option takes a string argument");
    FloatOption   floatOpt   = new FloatOption("floatopt",     "this option takes a float argument");
    DoubleOption  doubleOpt  = new DoubleOption("doubleopt",   "this option takes a double argument");
    BooleanOption booleanOpt = new BooleanOption("booleanopt", "this option takes a boolean argument");

    public OptionSetTest(String name) {
        super(name);
        tr.Ace.log("name", name);
    }

    public void setUp() {
        floatOpt.setShortName('f');
        
        optSet.addOption(intOpt);
        optSet.addOption(stringOpt);
        optSet.addOption(floatOpt);
        optSet.addOption(doubleOpt);
        optSet.addOption(booleanOpt);
    }

    public void testCommandLine() {
        tr.Ace.log("testing command line");

        tr.Ace.log("done adding");
        
        List<String> args = list("--intopt", "1", "--stringopt=two", "-f", "3.1415", "--no-booleanopt", "--doubleopt", "4.14");
        
        args = optSet.process(args);

        assertEquals(new Integer(1),     intOpt.getValue());
        assertEquals("two",              stringOpt.getValue());
        assertEquals(new Float(3.1415F), floatOpt.getValue());
        assertEquals(new Double(4.14),   doubleOpt.getValue());
        assertEquals(Boolean.FALSE,      booleanOpt.getValue());
    }

    public void testCommandLineRemainingArgs() {
        tr.Ace.log("testing command line");

        tr.Ace.log("done adding");
        
        List<String> args = list("--intopt", "1",
                                 "--stringopt=two",
                                 "-f", "3.1415",
                                 "--no-booleanopt",
                                 "--doubleopt", "4.14",
                                 "foo",
                                 "bar", 
                                 "baz");
        
        args = optSet.process(args);

        assertEquals(new Integer(1),     intOpt.getValue());
        assertEquals("two",              stringOpt.getValue());
        assertEquals(new Float(3.1415F), floatOpt.getValue());
        assertEquals(new Double(4.14),   doubleOpt.getValue());
        assertEquals(Boolean.FALSE,      booleanOpt.getValue());

        assertEquals(3, args.size());
        assertEquals("foo", args.get(0));
        assertEquals("bar", args.get(1));
        assertEquals("baz", args.get(2));
    }

    public void testUsage() {
        List<String> args = list("--help");
        optSet.addRunControlFile("/etc/OptionSetTest.conf");
        optSet.addRunControlFile("~/.OptionSetTest");
        
        optSet.process(args);
    }

    public void testConfig() {
        List<String> args = list("--help - config");
        optSet.addRunControlFile("/etc/OptionSetTest.conf");
        optSet.addRunControlFile("~/.OptionSetTest");
        
        optSet.process(args);
    }

    public void testRunControlFile() {
        String userHome = System.getProperty("user.home");
        String rcFileName = userHome + "/.OptionSetTest";
        File   rcFile = new File(rcFileName);

        List<String> lines = list("intopt:     999", 
                                  "stringopt:  april",
                                  "floatopt:   8.41",
                                  "booleanopt: false",
                                  "doubleopt:  66.938432");
            
        IO.printLines(rcFileName, lines);
            
        optSet.addRunControlFile("~/.OptionSetTest");
            
        List<String> args = list("app");
        
        args = optSet.process(args);
            
        assertEquals(new Integer(999),      intOpt.getValue());
        assertEquals("april",               stringOpt.getValue());
        assertEquals(new Float(8.41F),      floatOpt.getValue());
        assertEquals(new Double(66.938432), doubleOpt.getValue());
        assertEquals(Boolean.FALSE,         booleanOpt.getValue());
            
        rcFile.delete();
    }

    public void testRunControlFileAndCommandLine() {
        String userHome = System.getProperty("user.home");
        String rcFileName = userHome + "/.OptionSetTest";
        File   rcFile = new File(rcFileName);
            
        List<String> lines = list("intopt:     999",
                                  "stringopt:  april",
                                  "floatopt:   8.41",
                                  "booleanopt: false",
                                  "doubleopt:  66.938432");
            
        IO.printLines(rcFileName, lines);
            
        optSet.addRunControlFile("~/.OptionSetTest");
            
        List<String> args = list("--doubleopt=4.38", "--booleanopt");
        
        args = optSet.process(args);
            
        assertEquals(new Integer(999), intOpt.getValue());
        assertEquals("april",          stringOpt.getValue());
        assertEquals(new Float(8.41F), floatOpt.getValue());
        assertEquals(new Double(4.38), doubleOpt.getValue());
        assertEquals(Boolean.TRUE,     booleanOpt.getValue());
            
        rcFile.delete();
    }
}
