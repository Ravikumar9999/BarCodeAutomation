package com.qa.barcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeTest {
	
	
	@Test
	public void barCodeTest() throws IOException, NotFoundException, InterruptedException {
		//Chrome:
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		
		//driver.get("https://barcode.tec-it.com/en");
		driver.get("https://barcode.tec-it.com/en/?data=Hi%20This%20is%20Ravi%20Kumar");
		
		WebElement textBox = driver.findElement(By.id("Data"));
		textBox.clear();
		textBox.sendKeys("Hi This is Ravi Kumar");
		
		Thread.sleep(2000);
		
		
		String barCodeURL = driver.findElement(By.tagName("img")).getAttribute("src");
		System.out.println(barCodeURL);
		
		URL url = new URL(barCodeURL);
		BufferedImage bufferImage = ImageIO.read(url);
		
		LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferImage);
		
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		
		Result result = new MultiFormatReader().decode(binaryBitmap);
		System.out.println(result.getText());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
