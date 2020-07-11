package Selenium_Assignment.Vis;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestRunner {

	WebDriver driver;
	String email = UUID.randomUUID().toString() + "@gmail.com";
	String password = "Aa9_4_5_3.";

	@BeforeMethod()
	public void openUrl() {

		
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",path+"\\Source\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.navigate().to("http://automationpractice.com/index.php");

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")));

	}

	@AfterMethod()
	public void closeSession() {
		driver.close();
	}

	@Test(priority = 1)
	public void signup_scenario() {

		WebDriverWait wait = new WebDriverWait(driver, 5);

		WebElement signIn = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));
		signIn.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));

		WebElement authentication = driver.findElement(By.id("email_create"));
		authentication.clear();
		authentication.sendKeys(email);

		WebElement createAccountButton = driver.findElement(By.id("SubmitCreate"));
		createAccountButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_firstname")));

		WebElement firstName = driver.findElement(By.id("customer_firstname"));
		firstName.sendKeys("Mohamed");

		WebElement lastName = driver.findElement(By.id("customer_lastname"));
		lastName.sendKeys("Omar");

		WebElement Password = driver.findElement(By.id("passwd"));
		Password.clear();
		Password.sendKeys(password);

		WebElement adress1 = driver.findElement(By.id("address1"));
		adress1.clear();
		adress1.sendKeys("NasrCity");

		WebElement city = driver.findElement(By.id("city"));
		city.clear();
		city.sendKeys("Cairo");

		WebElement drobDownListCountry = driver.findElement(By.id("id_country"));
		Select country = new Select(drobDownListCountry);
		country.selectByVisibleText("United States");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-id_state")));

		WebElement drobDownListState = driver.findElement(By.id("id_state"));
		Select select = new Select(drobDownListState);
		select.selectByValue("1");

		WebElement postCode = driver.findElement(By.id("postcode"));
		postCode.clear();
		postCode.sendKeys("11757");

		WebElement phoneNumber = driver.findElement(By.id("phone_mobile"));
		phoneNumber.clear();
		phoneNumber.sendKeys("01128976547");

		WebElement register = driver.findElement(By.id("submitAccount"));
		register.click();

		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://automationpractice.com/index.php?controller=my-account");

	}

	@Test(priority = 2)
	public void login_scenario() {

		WebDriverWait wait = new WebDriverWait(driver, 5);

		WebElement signIn = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));
		signIn.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

		WebElement authentication = driver.findElement(By.id("email"));
		authentication.clear();
		authentication.sendKeys(email);

		WebElement Password = driver.findElement(By.id("passwd"));
		Password.clear();
		Password.sendKeys(password);

		WebElement submitLogin = driver.findElement(By.id("SubmitLogin"));
		submitLogin.click();

		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://automationpractice.com/index.php?controller=my-account");

	}

	@Test(priority = 3)
	public void checkout_scenario() {

		login_scenario();

		WebDriverWait wait = new WebDriverWait(driver, 5);

		WebElement women = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));
		women.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"subcategories\"]/ul/li[1]/div[1]/a")));
		WebElement tops = driver.findElement(By.xpath("//*[@id=\"subcategories\"]/ul/li[1]/div[1]/a"));
		tops.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"subcategories\"]/ul/li[2]/div[1]/a")));
		WebElement blouses = driver.findElement(By.xpath("//*[@id=\"subcategories\"]/ul/li[2]/div[1]/a"));
		blouses.click();

		//List<WebElement> products = driver.findElements(By.className("product_img_link"));
		//Assert.assertTrue(products.size() >= 1);
		//products.get(0).click();
		
		WebElement product = driver.findElement(By.className("product_img_link"));
		product.click();

		WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button"));
		addToCart.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")));
		WebElement proceedToCheckout = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
		proceedToCheckout.click();

		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://automationpractice.com/index.php?controller=order");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")));
		WebElement goToAddress = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));
		goToAddress.click();

		URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://automationpractice.com/index.php?controller=order&step=1");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/form/p/button")));
		WebElement processAddress = driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button"));
		processAddress.click();

		WebElement privacyPolicy = driver.findElement(By.name("cgv"));
		privacyPolicy.click();

		WebElement processCarrier = driver.findElement(By.name("processCarrier"));
		processCarrier.click();

		WebElement bankWire = driver
				.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a[@class=\"bankwire\"]"));
		bankWire.click();

		WebElement confirmOrder = driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button[@type=\"submit\"]"));
		confirmOrder.click();

		String orderConfirmationText = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText();
		Assert.assertTrue(orderConfirmationText.contains("ORDER CONFIRMATION"));

	}

	@Test(priority = 4)
	public void order_history_scenario() {

		login_scenario();

		WebDriverWait wait = new WebDriverWait(driver, 5);

		WebElement orderHistory = driver
				.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a[@title=\"Orders\"]"));
		orderHistory.click();

		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://automationpractice.com/index.php?controller=history");

		WebElement order = driver.findElement(By.xpath("//*[@id=\"order-list\"]/tbody/tr[1]"));
		Assert.assertTrue(null != order);

	}
}