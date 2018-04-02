import controller.LaboratoriesController;
import junit.framework.TestCase;
import model.Student;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ControllerTest extends TestCase {
    LaboratoriesController controller;

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        controller=new LaboratoriesController("student_test","lab_test");
    }
    /*General case*/
    @Test
    public void testAddNewStudent() throws IOException {

        boolean res=controller.saveStudent(new Student(12,"Test Test",123));
        assertEquals(true,res);
    }

    /*Tests for registration number:*/
    @Test
    public void testAddNewStudentRegNrLessThanBoundary()  {

        boolean res=controller.saveStudent(new Student(-1,"Test Test",123));
        assertEquals(false,res);
    }
    @Test
    public void testAddNewStudentRegNrEqualToMinBoundary()  {

        boolean res=controller.saveStudent(new Student(0,"Test Test",123));
        assertEquals(true,res);
    }
    @Test
    public void testAddNewStudentRegNrGreaterThanMinBoundary()  {

        boolean res=controller.saveStudent(new Student(1,"Test",123));
        assertEquals(true,res);
    }
    @Test
    public void testAddNewStudentRegNrLessThanMaxBoundary()  {

        boolean res=controller.saveStudent(new Student(Integer.MAX_VALUE-1,"Test",123));
        assertEquals(true,res);
    }
    @Test
    public void testAddNewStudentRegNrEqualToMaxBoundary()  {

        boolean res=controller.saveStudent(new Student(Integer.MAX_VALUE,"Test",123));
        assertEquals(true,res);
    }
    @Test
    public void testAddNewStudentRegNrGreaterThanMaxBoundary()  {

        boolean res=controller.saveStudent(new Student(Integer.MAX_VALUE+1,"Test",123));
        assertEquals(false,res);
    }

    /*Tests for name*/
    @Test
    public void testAddNewStudentNameMatchesRegex()  {

        boolean res=controller.saveStudent(new Student(11,"aa",123));
        assertEquals(true,res);
    }

    @Test
    public void testAddNewStudentNameContainsNumber()  {

        boolean res=controller.saveStudent(new Student(12,"a1",123));
        assertEquals(false,res);
    }
    @Test
    public void testAddNewStudentNameContainsCharacter()  {

        boolean res=controller.saveStudent(new Student(13,"a;",123));
        assertEquals(false,res);
    }

    @Test
    public void testAddNewStudentNameIsEmpty()  {

        boolean res=controller.saveStudent(new Student(14,"",123));
        assertEquals(false,res);
    }


    /*Test for group*/
    @Test
    public void testAddNewStudentGroupLessThanMinBoundary()  {

        boolean res=controller.saveStudent(new Student(15,"Test",99));
        assertEquals(false,res);
    }
    @Test
    public void testAddNewStudentGroupEqualMinBoundary()  {

        boolean res=controller.saveStudent(new Student(16,"Test",100));
        assertEquals(true,res);
    }

    @Test
    public void testAddNewStudentGroupGreaterThanMinBoundary()  {

        boolean res=controller.saveStudent(new Student(17,"Test",101));
        assertEquals(true,res);
    }
    @Test
    public void testAddNewStudentGroupLessThanMaxBoundary()  {

        boolean res=controller.saveStudent(new Student(18,"Test",899));
        assertEquals(true,res);
    }
    @Test
    public void testAddNewStudentGroupEqualToMaxBoundary()  {

        boolean res=controller.saveStudent(new Student(19,"Test",900));
        assertEquals(true,res);
    }
    @Test
    public void testAddNewStudentGroupGreaterThanMaxBoundary()  {

        boolean res=controller.saveStudent(new Student(20,"Test",901));
        assertEquals(false,res);
    }
}
