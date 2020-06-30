package machine;

import java.util.Scanner;

import static machine.CoffeeConstants.*;
import static machine.MenuState.*;

public class CoffeeMachine {
    static Scanner sc = new Scanner(System.in);
    MenuState menuState;
    int water;
    int milk;
    int beans;
    int disposableCups;
    int money;

    public CoffeeMachine() {
        water = 400;
        milk = 540;
        beans = 120;
        disposableCups = 9;
        money = 550;
        menuState = MAINMENU;
    } //end constructor

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (!coffeeMachine.menuState.equals(OFF)) {
            coffeeMachine.getInput();
        } //end while loop

    } //end main()

    public void getInput() {

        switch (menuState) {
            case MAINMENU:
                System.out.print(menuState.menuOutput);
                setState(sc.nextLine());
                break;
            case BUY:
                System.out.print(menuState.menuOutput);
                buy(sc.nextLine());
                break;
            case FILL:
                fill();
                break;
            case REMAINING:
                ingredients();
                break;
            case TAKE:
                take();
                break;
            case OFF:
                break;
        }
    }

    public void setState(String input) {
        switch (input) {
            case "buy":
                menuState = BUY;
                break;
            case "fill":
                menuState = FILL;
                break;
            case "take":
                menuState = TAKE;
                break;
            case "remaining":
                menuState = REMAINING;
                break;
            case "exit":
                menuState = OFF;
                break;
            default:
                System.out.print("Please enter valid action: ");
        }
    }

    public void ingredients() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(disposableCups + " of disposable cups");
        System.out.println(money + " of money\n");
        menuState = MAINMENU;
    } //end ingredients()

    public void buy(String drink) {
        //System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3- cappuccino, back - to main menu: ");

        switch(drink) {
            case "1":
                coffeeSelection(ESPRESSO);
                break;
            case "2":
                coffeeSelection(LATTE);
                break;
            case "3":
                coffeeSelection(CAPPUCCINO);
                break;
            case "back":
                menuState = MAINMENU;
                break;
            default:
                System.out.println("invalid selection");
        } //end switch;
    } //end buy()

    public void fill() {
        System.out.print("Write how many ml of water do you want to add: ");
        water += Integer.parseInt(sc.nextLine());
        System.out.print("Write how many ml of milk do you want to add: ");
        milk += Integer.parseInt(sc.nextLine());
        System.out.print("Write how many grams of coffee beans do you want to add: ");
        beans += Integer.parseInt(sc.nextLine());
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        disposableCups += Integer.parseInt(sc.nextLine());
        menuState = MAINMENU;
    } //end fill()

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
        menuState = MAINMENU;
    } //end take()

    public void coffeeSelection(CoffeeConstants selection) {
        if (checkResources(selection.getWater(), selection.getMilk(), selection.getBeans(), selection.getDisposableCups())) {
            water -= selection.getWater();
            milk -= selection.getMilk();
            beans -= selection.getBeans();
            money += selection.cost;
            disposableCups -= selection.getDisposableCups();
        }
        menuState = MAINMENU;
    }

    public boolean checkResources(int water, int milk, int beans, int disposableCups) {

        if (this.water >= water
                && this.milk >= milk
                && this.beans >= beans
                && this.disposableCups >= disposableCups) {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        } else {
            System.out.println("Sorry, not enough resources.");
            return false;
        } //end if else
    } //end checkResources()
} //end CoffeeMachine class



