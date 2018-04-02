package validator;

import model.Laboratory;
import model.Student;

import java.util.Date;

public class Validator {

    public static boolean validateStudent(Student student) {
       // if(!student.getRegNumber().matches("[a-zA-Z]{4}[\\d]{4}")){
        if(student.getRegNumber()<0 || student.getRegNumber()> Integer.MAX_VALUE){
                return false;
        }
        if (!student.getName().matches("[a-zA-Z]+[\\s]?[a-zA-Z]+")) {
            return false;
        }
        if(student.getGroup() > 900 || student.getGroup() < 100){
            return false;
        }
        return true;
    }

    public static boolean validateLaboratory(Laboratory laboratory) {
        if(laboratory.getNumber() < 1) {
            return false;
        }
        if(laboratory.getProblemNumber() > 10 || laboratory.getProblemNumber() < 1) {
            return false;
        }
        Date date = new Date();
        if(date.after(laboratory.getDate())) {
            return false;
        }
        return true;
    }

    public static boolean validateGrade(float grade) {
        if(grade >= 1 && grade <= 10) {
            return true;
        }
        return false;
    }
} 