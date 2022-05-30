package sis.studentinfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HonorsGradingStrategyTest {
    @Test
    public void testGetGradePoints() {
        HonorsGradingStrategy strategy = new HonorsGradingStrategy();
        assertEquals(5, strategy.getGradePointsFor(Student.Grade.A));
        assertEquals(4, strategy.getGradePointsFor(Student.Grade.B));
        assertEquals(3, strategy.getGradePointsFor(Student.Grade.C));
        assertEquals(2, strategy.getGradePointsFor(Student.Grade.D));
        assertEquals(0, strategy.getGradePointsFor(Student.Grade.F));
    }
}
