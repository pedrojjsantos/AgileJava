package util.stringer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToStringerTest {
    @Test
    public void testDump() {
        String result = ToStringer.dump(new TestDumpOne(123, "hey"));
        assertEquals((
                "dumpableNum: [123]%n" +
                "dumpableMsg: [hey]").formatted(), result);

        result = ToStringer.dump(new TestDumpPrivate(123, "hey"));
        assertEquals((
                "dumpableNum: [123]%n" +
                "dumpableMsg: [hey]").formatted(), result);
    }

    @Test
    public void testDumpWithOrder() {
        String result = ToStringer.dump(new TestDumpOrdered(123, "hey"));
        assertEquals((
                "msg: [hey]%n" +
                "dumpableNum: [123]%n" +
                "dumpableMsg: [hey]%n" +
                "num: [123]").formatted(), result);
    }

    @Test
    public void testDumpWithQuotes() {
        String result = ToStringer.dump(new TestDumpQuotable("heya"));
        assertEquals((
                "msg1: [\"\"]%n" +
                "msg2: [heya]%n" +
                "msg3: [\"   \"]").formatted(), result);
    }

    @Test
    public void testDumpWithOutputMethod() {
        String result = ToStringer.dump(new TestDumpMethod(123, "hey"));
        assertEquals((
                "dumpableNum: [123]%n" +
                        "msg: [||hey||]%n" +
                        "dumpableMsg: [hey]").formatted(), result);
    }

    @Test
    public void testDumpWithOutputMethodArray() {
        String result = ToStringer.dump(new TestDumpMethodArray(123, "hey"));
        assertEquals((
                "dumpableNum: [123 | 61 | 30]%n" +
                        "msg: [hey ||hey|| ##hey##]%n" +
                        "dumpableMsg: [hey]").formatted(), result);
    }

    static class TestDumpOne {
        int num;
        @Dump
        int dumpableNum;
        String msg;
        @Dump
        String dumpableMsg;

        TestDumpOne(int num, String msg) {
            this.num = this.dumpableNum = num;
            this.msg = this.dumpableMsg = msg;
        }
    }

    static class TestDumpPrivate {
        int num;
        @Dump
        private int dumpableNum;
        String msg;
        @Dump
        private String dumpableMsg;

        TestDumpPrivate(int num, String msg) {
            this.num = this.dumpableNum = num;
            this.msg = this.dumpableMsg = msg;
        }
    }

    static class TestDumpOrdered {
        @Dump()
        int num;
        @Dump(order = 1)
        int dumpableNum;
        @Dump(order = 0)
        String msg;
        @Dump(order = 2)
        String dumpableMsg;

        TestDumpOrdered(int num, String msg) {
            this.num = this.dumpableNum = num;
            this.msg = this.dumpableMsg = msg;
        }
    }

    static class TestDumpQuotable {
        @Dump(quote = true)
        String msg1 = "";
        @Dump(quote = false)
        String msg2;
        @Dump(quote = true)
        String msg3 = "   ";

        TestDumpQuotable(String msg) {
            this.msg2 = msg;
        }
    }

    static class TestDumpMethod {
        int num;
        @Dump
        int dumpableNum;
        @Dump(outputMethod = "dumpMsg")
        String msg;
        @Dump
        String dumpableMsg;

        TestDumpMethod(int num, String msg) {
            this.num = this.dumpableNum = num;
            this.msg = this.dumpableMsg = msg;
        }

        String dumpMsg() {
            return "||" + msg + "||";
        }
    }

    static class TestDumpMethodArray {
        int num;
        @Dump(outputMethod = {"toString", "half", "quarter"}, separator = " | ")
        int dumpableNum;
        @Dump(outputMethod = {"toString", "dumpMsg", "dumpMsg2"})
        String msg;
        @Dump
        String dumpableMsg;

        TestDumpMethodArray(int num, String msg) {
            this.num = this.dumpableNum = num;
            this.msg = this.dumpableMsg = msg;
        }

        String dumpMsg() {
            return "||" + msg + "||";
        }
        String dumpMsg2() {
            return "##" + msg + "##";
        }
        int half() {
            return dumpableNum/2;
        }
        int quarter() {
            return dumpableNum/4;
        }
    }
}
