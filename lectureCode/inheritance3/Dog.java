import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    public String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    /** It is imbedded within the object itself, and it defines the natural ordering of a type. */
    @Override
    public int compareTo(Dog uddaDog) {
        //assume nobody is messing up and giving us
        //something that isn't a dog.
        return size - uddaDog.size;
    }

    /**
     public interface Comparator<T> {
     int compare(T o1, T o2);
     }
     */

    /** Since there's only room for one compareTo method, if we want multiple ways to compare, we must turn to Comparator. */
    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }
}