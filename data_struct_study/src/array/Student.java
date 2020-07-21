package array;

public class Student {

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) {
        Array<Student> array = new Array<>();
        array.addLast(new Student("JsonChao", 99));
        array.addLast(new Student("Awesome-Android", 100));
        array.addLast(new Student("Awesome-Android2", 100));
        System.out.println(array);
    }
}
