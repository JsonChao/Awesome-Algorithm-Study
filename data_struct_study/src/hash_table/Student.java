package hash_table;

public class Student {

    int grade;
    int cls;
    String firstName;
    String lastName;

    Student(int grade, int cls, String firstName, String lastName){
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode(){

        int hash = 0;
        int B = 32;
        hash = hash * B + ((Integer) grade).hashCode();
        hash = hash * B + ((Integer) cls).hashCode();
        hash = hash * B + firstName.toUpperCase().hashCode();
        hash = hash * B + lastName.toUpperCase().hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object o){

        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;
        return this.grade == student.grade &&
                this.cls == student.cls &&
                this.firstName.toUpperCase().equals(student.firstName.toUpperCase()) &&
                this.lastName.toUpperCase().equals(student.lastName.toUpperCase());
    }
}
