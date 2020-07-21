package set;

import java.util.ArrayList;

public class Main1 {

    public static void main(String[] args) {

        ArrayList<String> words1 = new ArrayList<>();

        System.out.println("pride-and-prejudice");

        if (FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set = new BSTSet<>();
            for (String item:words1) {
                set.add(item);
            }

            System.out.println("Total different words: " + set.getSize());
        }


        ArrayList<String> words2 = new ArrayList<>();

        System.out.println("a-tale-of-two-cities");

        if (FileOperation.readFile("a-tale-of-two-cities.txt", words2)) {
            System.out.println("Total words: " + words2.size());

            BSTSet<String> set = new BSTSet<>();
            for (String item:words2) {
                set.add(item);
            }

            System.out.println("Total different words: " + set.getSize());
        }
    }
}
