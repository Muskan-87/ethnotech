class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}
class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}
public class ineritance {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.sound(); // inherited from Animal
        d.bark();  // own method
    }
} 
