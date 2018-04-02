package model;

public class Student {
    private int regNumber;
    private String name;
    private int group;

    public Student() {
    }

    public Student(int regNumber, String name, int group) {
        this.regNumber = regNumber;
        this.name = name;
        this.group = group;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return regNumber + " " + name + " " + group;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((regNumber *prime));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (regNumber !=((Student) obj).regNumber) {

            return false;
        }
        return true;
    }

} 