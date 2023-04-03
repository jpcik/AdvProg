package school;

public class Teacher {
  private String name;
  private int age;

  public Teacher(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public int getAge(){
    return this.age;
  }

  public String whoIsThis() {
    return "I am "+this.name;
  }
}
