package dto;

public class PassengerDTO {
    private String name;
    private String nationality;
    private String gender;
    private int age;

    public PassengerDTO() {}

    public PassengerDTO(String name, String nationality, String gender, int age) {
        this.name = name;
        this.nationality = nationality;
        this.gender = gender;
        this.age = age;
    }

    public String getName() { return name; }
    public String getNationality() { return nationality; }
    public String getGender() { return gender; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return name + " / " + nationality + " / " + gender + " / " + age + " жашта";
    }
}
