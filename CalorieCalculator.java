public class CalorieCalculator {

    public static double calculateBMR(UserData user) {
        if (user.getGender().equalsIgnoreCase("Male")) {
            return 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            return 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }
    }

    public static double getActivityMultiplier(String activityLevel) {
        return switch (activityLevel.toLowerCase()) {
            case "sedentary" -> 1.2;
            case "lightly active" -> 1.375;
            case "moderately active" -> 1.55;
            case "very active" -> 1.725;
            case "super active" -> 1.9;
            default -> 1.2;
        };
    }

    public static double calculateTDEE(double bmr, String activityLevel) {
        return bmr * getActivityMultiplier(activityLevel);
    }
}
