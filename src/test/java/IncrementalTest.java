
import controller.LaboratoriesController;
import junit.framework.TestCase;
import model.Laboratory;
import model.Student;
import org.junit.Before;
import org.junit.Test;
import repository.FileDataPersistence;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class IncrementalTest extends TestCase{
    private LaboratoriesController testController;

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        this.testController = new LaboratoriesController("student_test.txt", "laboratoriesTest.txt");
    }

    @Test
    public void testA()  {
        //-----------test add
        Student student = new Student(4444, "Test Student", 225 );
        boolean res = testController.saveStudent(student);
        assertEquals(true,res);
    }

    @Test
    public void testAB() {
        Student student = new Student(4445, "Test Student", 226 );
        boolean added = testController.saveStudent(student);

        //--------------- test add lab & assign grade
        boolean res=false,success=false;
        Laboratory lab;
        try {
            lab = new Laboratory(2, "07/06/2018", 1, 4445);
            res = testController.saveLaboratory(lab);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(true,res);

        try {
             success = testController.addGrade(4445, 2, 10);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals(success, true);

    }

    @Test
    public void testABC()  {
        Student student = new Student(4446, "Test Student", 227 );
        boolean added = testController.saveStudent(student);

        boolean res=false,success=false;
        Laboratory lab;
        try {
            lab = new Laboratory(2, "07/06/2018", 1, 4446);
            res = testController.saveLaboratory(lab);
            success = testController.addGrade(4446, 2, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //-------------test if the added student is on passed list
        boolean exists=false;
        try {
            List<Student> passingStudents = testController.passedStudents();
            exists=passingStudents.contains(student);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(true,exists);
    }


}
