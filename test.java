import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;



class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;

    // Constructors, getters, and setters

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter and Setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}







public class test {

    public static void main(String[] args) {
        // Write objects to a file using serialization (append mode)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("persons.ser", true))) {
            for (int i = 0; i < 5; i++) {
                Person person = new Person("Person " + (i + 1), 25 + i);
                writeObjectToFile(oos, person);
            }
                    System.out.println("First write to file");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read objects from the file using deserialization
        System.out.println("First read from file");
        readObjectsFromFile();

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("persons.ser", true))) {
            for (int i = 10; i < 15; i++) {
                Person person = new Person("Person " + (i + 1), 25 + i);
                writeObjectToFile(oos, person);
            }
                    System.out.println("Second write to file");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Second read from file");
        
        readObjectsFromFile();

    }

    private static void writeObjectToFile(ObjectOutputStream oos, Person person) {
        try {
            // Write the object to the file
            oos.writeObject(person);
            System.out.println("Object has been written to the file: " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readObjectsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persons.ser"))) {
            // Read objects from the file in a loop until the end of the file
            while (true) {
                try {
                    Person person = (Person) ois.readObject();
                    System.out.println("Read object from file: " + person);
                } catch (EOFException e) {
                    System.out.println("End of file reached");
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}