package controller;

import repository.FileDataPersistence;
import model.Laboratory;
import model.Student;
import validator.Validator;

import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;

public class LaboratoriesController {
    private FileDataPersistence studentPersistence = new FileDataPersistence(
            "students.txt");
    private FileDataPersistence laboratoryPersistence = new FileDataPersistence(
            "laboratories.txt");

    public LaboratoriesController(String studentFile, String laboratoryFile) {
    	this.studentPersistence = new FileDataPersistence(studentFile);
    	this.laboratoryPersistence = new FileDataPersistence(laboratoryFile);
    }
    
    public boolean saveStudent(Student student) {
        if (Validator.validateStudent(student)) {
            this.studentPersistence.saveStudent(student);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveLaboratory(Laboratory laboratory) {
        if (Validator.validateLaboratory(laboratory)) {

            this.laboratoryPersistence.saveLaboratory(laboratory);
            return true;
        } else {
            return false;
        }
    }

    public boolean addGrade(int student, String labNumber, float grade)
            throws NumberFormatException, IOException, ParseException {
        System.out.println("In controller: regNr="+String.valueOf(student)+" labNr="+labNumber+" grade=" + String.valueOf(grade));
        if (Validator.validateGrade(grade)) {
            System.out.println("Grade is valid");
            this.laboratoryPersistence.addGrade(student, labNumber, grade);

            return true;
        } else {
            return false;
        }
    }

    public List<Student> passedStudents() throws NumberFormatException,
            IOException, ParseException {
        Map<Integer, List<Laboratory>> laboratoryMap = this.laboratoryPersistence.getLaboratoryMap();
        List<Student> studentsList = studentPersistence.getStudentsList();

        List<Student> passedStudents = new ArrayList<>();
        Entry<Integer, List<Laboratory>> entry;

        Set<Entry<Integer, List<Laboratory>>> entrySet = laboratoryMap.entrySet();
        Iterator<Entry<Integer, List<Laboratory>>> iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            entry = iterator.next();
            float midGrade = entry.getValue().get(0).getGrade();
            for (Laboratory laboratory : entry.getValue()) {
                midGrade = (midGrade + laboratory.getGrade()) / 2;
            }
            System.out.println(midGrade);
            if (midGrade >= 5) {
                Student student = new Student();
                student.setRegNumber(entry.getKey());
                int indexOf = studentsList.indexOf(student);
                passedStudents.add(studentsList.get(indexOf));
            }
        }

        //order by group
        passedStudents.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getGroup()==o2.getGroup())
                    return 0;
                return o1.getGroup() <o2.getGroup() ? -1 : 1;
            }
        });

        return passedStudents;
    }
} 