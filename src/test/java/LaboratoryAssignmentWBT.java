import controller.LaboratoriesController;
import junit.framework.TestCase;
import model.Laboratory;
import model.Student;
import org.junit.Before;
import org.junit.Test;
import repository.FileDataPersistence;

import java.io.IOException;
import java.text.ParseException;

public class LaboratoryAssignmentWBT extends TestCase{
    private FileDataPersistence laboratoryPersistence ;

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        laboratoryPersistence = new FileDataPersistence(
                "laboratoriesTest.txt");
    }

    @Test
    public void testLabNrAndStudentExist()  {
        boolean res=false;
        try {
            res = laboratoryPersistence.addGrade(1111, 1, 7);
        }catch (Exception ex)
        {}
        assertEquals(true,res);
    }

    @Test
    public void testLabNrDoesntExist()  {
        boolean res=false;
        try {
            res = laboratoryPersistence.addGrade(1111, 12, 7);
        }catch (Exception ex)
        {}
        assertEquals(false,res);
    }

    @Test
    public void testStudentNrDoesntExist()  {
        boolean res=false;
        try {
            res = laboratoryPersistence.addGrade(1119, 1, 7);
        }catch (Exception ex)
        {}
        assertEquals(false,res);
    }
}
