package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DebugTest extends BaseTest {

    @Test
    public void printPageSource() {
        System.out.println("=== Current URL: " + driver.getCurrentUrl());
        System.out.println("=== Page Title: " + driver.getTitle());

        // Print all input fields found on the page
        System.out.println("\n=== All INPUT elements on page ===");
        try {
            var inputs = driver.findElements(By.tagName("input"));
            System.out.println("Total inputs found: " + inputs.size());

            for (int i = 0; i < inputs.size(); i++) {
                WebElement input = inputs.get(i);
                String type = input.getAttribute("type");
                String name = input.getAttribute("name");
                String id = input.getAttribute("id");
                String placeholder = input.getAttribute("placeholder");

                System.out.println("Input " + (i+1) + ":");
                System.out.println("  type=" + type);
                System.out.println("  name=" + name);
                System.out.println("  id=" + id);
                System.out.println("  placeholder=" + placeholder);
            }
        } catch (Exception e) {
            System.out.println("Error finding inputs: " + e.getMessage());
        }

        // Print all buttons
        System.out.println("\n=== All BUTTON elements on page ===");
        try {
            var buttons = driver.findElements(By.tagName("button"));
            System.out.println("Total buttons found: " + buttons.size());

            for (int i = 0; i < buttons.size(); i++) {
                WebElement button = buttons.get(i);
                String text = button.getText();
                String type = button.getAttribute("type");

                System.out.println("Button " + (i+1) + ":");
                System.out.println("  text=" + text);
                System.out.println("  type=" + type);
            }
        } catch (Exception e) {
            System.out.println("Error finding buttons: " + e.getMessage());
        }
    }
}
