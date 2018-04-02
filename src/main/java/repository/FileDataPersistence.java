package repository;

import model.Laboratory;
import model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDataPersistence {
    private String file;

    public FileDataPersistence(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void saveStudent(Student student) {
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(student.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // No check if laboratory is unique
    public void saveLaboratory(Laboratory laboratory) {
        BufferedWriter writter;
        try {
            writter = new BufferedWriter(new FileWriter(file, true));
            writter.write(laboratory.toString() + "\n");
            writter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Ambiguous what field of "Student" should be passed
    public void addGrade(int student, String labNumber, float grade)
            throws IOException, NumberFormatException, ParseException {
        File fileA = new File(file);
        File fileB = new File("temp");

        BufferedReader reader = new BufferedReader(new FileReader(fileA));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileB));

        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println("Reading: "+line);
            String[] temp = line.split(" ");
            String fileLabNumber = temp[0];
            int fileStudentNumber = Integer.valueOf(temp[4]);
            if (fileLabNumber.equals(labNumber) &&( fileStudentNumber==student)) {
                Laboratory laboratory = new Laboratory(
                        Integer.valueOf(temp[0]), temp[1],
                        Integer.valueOf(temp[2]), Integer.valueOf(temp[4]));
                laboratory.setGrade(grade);
                writer.write(laboratory.toString() + "\n");
                System.out.println("Writing: "+ laboratory.toString());
            } else {
                System.out.println("Writing: "+ line.toString());
                writer.write(line + "\n");
            }
        }
        writer.close();
        reader.close();

        fileA.delete();
        fileB.renameTo(fileA);
    }

    public Map<Integer, List<Laboratory>> getLaboratoryMap()
            throws NumberFormatException, IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Map<Integer, List<Laboratory>> laboratoryMap = new HashMap<Integer, List<Laboratory>>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            Laboratory laboratory = new Laboratory(Integer.valueOf(temp[0]),
                    temp[1], Integer.valueOf(temp[2]), Float.valueOf(temp[3]),
                    Integer.valueOf(temp[4]));
            if (laboratoryMap.get(laboratory.getStudentRegNumber()) == null) {
                List<Laboratory> laboratoryList = new ArrayList<Laboratory>();
                laboratoryList.add(laboratory);
                laboratoryMap.put(laboratory.getStudentRegNumber(),
                        laboratoryList);
            } else {
                laboratoryMap.get(laboratory.getStudentRegNumber()).add(
                        laboratory);
            }
        }
        reader.close();
        return laboratoryMap;
    }

    public List<Student> getStudentsList() throws NumberFormatException,
            IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<Student> allStudentsList = new ArrayList<Student>();

        String line = null;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            Student student = new Student(Integer.valueOf(temp[0]), temp[1] + temp[2], Integer.valueOf(temp[3]));
            allStudentsList.add(student);
        }
        reader.close();
        return allStudentsList;
    }
} 