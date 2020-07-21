package array;

/**
 * 数组最大的优点：快速查询
 * 数组最好应用于"索引有语义"的情况（但并非所有有语义的索引都适用于数组，例如身份证号码）
 * 我们需要额外处理索引有语义的情况
 * 数组的容量：capacity，数组的大小：size，初始为0
 */
public class Main {

    public static void main(String[] args) {

        Array<Integer> array = new Array<>(10);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }

        System.out.println(array);

        array.addLast(66);
        System.out.println(array);

        array.add(1, -100);
        System.out.println(array);

        boolean contains = array.contains(30);
        System.out.println(contains);

        boolean contains1 = array.contains(66);
        System.out.println(contains1);

        array.delete(3);
        System.out.println(array);

        array.deleteLast();
        System.out.println(array);

        array.deleteFirst();
        System.out.println(array);

        boolean b = array.deleteElement(10);
        System.out.println(b);

        boolean b1 = array.deleteElement(-100);
        System.out.println(array);
        System.out.println(b1);

    }


}
