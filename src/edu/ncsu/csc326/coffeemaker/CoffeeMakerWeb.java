package edu.ncsu.csc326.coffeemaker;

import static spark.Spark.*;

public class CoffeeMakerWeb {
    public static void main(String[] args) {
        CoffeeMaker cm = new CoffeeMaker();

        port(8080); // container port
        get("/", (req, res) -> "Welcome to CoffeeMaker! Current inventory: " + cm.checkInventory());
        get("/recipes", (req, res) -> {
            StringBuilder sb = new StringBuilder();
            for (var r : cm.getRecipes()) {
                if (r != null) sb.append(r.getName()).append("<br>");
            }
            return sb.toString();
        });
    }
}
