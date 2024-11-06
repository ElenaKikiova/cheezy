package cheezy;

public class Main {
    public static void main(String[] args) {
        Cheese cheese = new Cheese(
                "1", "AB", "Artisan", 40.5, "Creamy", "Soft texture", true,
                "Fresh Cheese", "Cow", "Pasteurized", "Edible", "Cheddar", "Higher fat"
        );


        System.out.println(cheese.getCheeseName());
    }
}