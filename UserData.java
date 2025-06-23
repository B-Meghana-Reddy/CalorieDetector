public class UserData {
    private int age;
    private double weight;
    private double height;
    private String gender;
    private String activityLevel;

    public UserData(int age, double weight, double height, String gender, String activityLevel) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.activityLevel = activityLevel;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public String getActivityLevel() {
        return activityLevel;
    }
}
