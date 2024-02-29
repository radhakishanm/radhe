package WU_Assignment.com.westernUnion;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RetriveLinks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.flipkart.com");

		// Find all links on the webpage
		List<WebElement> links = driver.findElements(By.tagName("a"));

		// Print the href attribute of each link
		for (WebElement link : links) {
			System.out.println(link.getAttribute("href"));
		}
		// using stream
		links.stream().map(link -> link.getAttribute("href")).forEach(System.out::println);
		// using parallel stream
		links.parallelStream().map(link -> link.getAttribute("href")).forEach(System.out::println);

		// using lambda expression
		links.forEach(link -> System.out.println(link.getAttribute("href")));

		// Close the WebDriver
		driver.quit();
	}
}
