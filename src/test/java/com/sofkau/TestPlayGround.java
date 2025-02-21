package com.sofkau;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestPlayGround {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Usuario\\Desktop\\SOFKA\\Drivers\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-running-insecure-content");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.uitestingplayground.com/home");

    }

    @Test
    public void testSampleApp() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement SampleApp = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/sampleapp']"))));
        SampleApp.click();
        WebElement userName = driver.findElement(By.xpath("//input[contains(@type,'text')]"));
        userName.sendKeys("juan");
        WebElement password = driver.findElement(By.xpath("//input[contains(@type,'password')]"));
        password.sendKeys("pwd");
        WebElement login = driver.findElement(By.xpath("//button[@id='login']"));
        login.click();
    }
    
    @Test
    public void testClick() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement botonClick  = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/click']"))));
        botonClick.click();
        WebElement botonEvent = driver.findElement(By.xpath("//button[@id='badButton']"));
        botonEvent.click();
        assertEquals("btn btn-success",botonEvent.getAttribute("class"));
    }

    @Test
    public void testDynamicId() {
        WebElement DynamicId = driver.findElement(By.xpath("//a[@href='/dynamicid']"));
        DynamicId.click();
        WebElement botonDynamic = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        botonDynamic.click();
    }

    @Test
    public void testAjaxData() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement AjaxData = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/ajax']"))));
        AjaxData.click();
        WebElement botonRequest = driver.findElement(By.xpath("//*[@id=\"ajaxButton\"]"));
        botonRequest.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Data loaded with AJAX get request.']")));
        assertTrue(driver.findElement(By.xpath("//p[text()='Data loaded with AJAX get request.']")).isDisplayed());
    }

    @Test
    public void testClientSideDelay() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement ClientSideDelay = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/clientdelay']"))));
        ClientSideDelay.click();
        WebElement botonClient = driver.findElement(By.xpath("//button[contains(@id,'ajaxButton')]"));
        botonClient.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Data calculated on the client side.']")));
        assertTrue(driver.findElement(By.xpath("//p[text()='Data calculated on the client side.']")).isDisplayed());
    }

    @Test
    public void testTextInput() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement TextInput = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/textinput']"))));
        TextInput.click();
        WebElement NewName = driver.findElement(By.xpath("//input[contains(@id,'newButtonName')]"));
        NewName.sendKeys("Ramiro");
        WebElement botonText = driver.findElement(By.xpath("//button[@id='updatingButton']"));
        botonText.click();
        assertEquals("Ramiro",botonText.getText());
    }

    @Test
    public void testScrollbars() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement Scrollbars = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/scrollbars']"))));
        Scrollbars.click();
        WebElement botonHiding = driver.findElement(By.xpath("//button[@id='hidingButton']"));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(arguments[0], arguments[1]);", botonHiding.getLocation().getX(), botonHiding.getLocation().getY());
        wait.until(ExpectedConditions.elementToBeClickable(botonHiding));
        botonHiding.click();
    }

    @Test
    public void testProgressBar() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement ProgressBarBoton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/progressbar']"))));
        ProgressBarBoton.click();
        WebElement botonProgress = driver.findElement(By.xpath("//button[@id='startButton']"));
        botonProgress.click();
        WebElement progressBar = driver.findElement(By.xpath("//div[@class='progress']"));
        int progressBarValue = 0;
        while (progressBarValue < 75) {
            String progressBarText = progressBar.getText().replace("%", "");
            progressBarValue = Integer.parseInt(progressBarText);
            System.out.println(progressBarValue);
        }
        WebElement botonStop = driver.findElement(By.xpath("//button[@id='stopButton']"));
        botonStop.click();
        String finalProgressText = progressBar.getText().replace("%", "");
        int finalProgressValue = Integer.parseInt(finalProgressText);

        System.out.println("Progreso detenido en: " + finalProgressValue + "%");
        System.out.println("Diferencia con 75%: " + Math.abs(75 - finalProgressValue));
    }

    @Test
    public void testClassAttribute() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement ClassAttribute = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/classattr']"))));
        ClassAttribute.click();
        WebElement primaryButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(concat(' ', normalize-space(@class), ' '), ' btn-primary ')]")));
        primaryButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        System.out.println("Botón identificado y alerta aceptada correctamente.");
    }

    @Test
    public void testHiddenLayerButton() {
            WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
            WebElement HiddenLayer = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/hiddenlayers']"))));
            HiddenLayer.click();
            WebElement greenButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"greenButton\"]")));
            greenButton.click();
        try {
            greenButton.click();
            System.out.println("Se pudo presionar dos veces.");
        } catch (Exception e) {
            System.out.println("No se puede presionar dos veces");
        }
    }
    @Test
    public void testLoanDelays() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement LoanDelays = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/loaddelay']"))));
        LoanDelays.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary'][contains(.,'Button Appearing After Delay')]")));
        assertTrue(driver.findElement(By.xpath("//button[@class='btn btn-primary'][contains(.,'Button Appearing After Delay')]")).isDisplayed());
    }

    @Test
    public void testVisibility(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement Visibility = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/visibility']"))));
        Visibility.click();
        WebElement botonVisibility = driver.findElement(By.xpath("//button[@id='hideButton']"));
        botonVisibility.click();
        WebElement otherButton = driver.findElement(By.xpath("//button[contains(@id,'overlappedButton')]"));
        boolean isVisible = otherButton.isDisplayed();

        System.out.println("El botón 'otherButton' está visible: " + isVisible);
    }

    @Test
    public void testAutoWait() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement AutoWait = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/autowait']"))));
        AutoWait.click();
        WebElement comboBox = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"element-type\"]"))));
        Select comboBoxSelect = new Select(comboBox);
        comboBoxSelect.selectByVisibleText("Button");
        WebElement checkBox2 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='enabled']"))));
        WebElement checkBox4 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ontop']"))));
        checkBox2.click();
        checkBox4.click();
        WebElement botonApply3 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='applyButton3']"))));
        botonApply3.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='target']")));
        assertTrue(driver.findElement(By.xpath("//button[@id='target']")).isDisplayed());
        WebElement botonPlay = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='target']"))));
        botonPlay.click();
    }

    @Test
    public void testAutoWaitCase2() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement AutoWait = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/autowait']"))));
        AutoWait.click();
        WebElement comboBox = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"element-type\"]"))));
        Select comboBoxSelect = new Select(comboBox);
        comboBoxSelect.selectByVisibleText("Textarea");
        WebElement checkBox1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='visible']"))));
        WebElement checkBox3 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='editable']"))));
        checkBox1.click();
        checkBox3.click();
        WebElement botonApply5 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='applyButton5']"))));
        botonApply5.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='target']")));
        assertTrue(driver.findElement(By.xpath("//textarea[@id='target']")).isDisplayed());
        WebElement TextClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//textarea[@id='target']"))));
        TextClick.click();
        WebElement TextArea = driver.findElement(By.xpath("//textarea[@id='target']"));
        TextArea.sendKeys("Esto es un texto de prueba");
    }

    @Test
    public void testAutoWaitCase3() {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(16));
        WebElement AutoWait = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='/autowait']"))));
        AutoWait.click();
        WebElement comboBox = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"element-type\"]"))));
        Select comboBoxSelect = new Select(comboBox);
        comboBoxSelect.selectByVisibleText("Select");
        WebElement checkBox1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='visible']"))));
        WebElement checkBox2 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='enabled']"))));
        WebElement checkBox3 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='editable']"))));
        WebElement checkBox4 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ontop']"))));
        checkBox1.click();
        checkBox2.click();
        checkBox3.click();
        checkBox4.click();
        WebElement botonApply10 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='applyButton10']"))));
        botonApply10.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='target']")));
        assertTrue(driver.findElement(By.xpath("//select[@id='target']")).isDisplayed());
        WebElement TextClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//select[@id='target']"))));
        TextClick.click();
        WebElement comboBoxTwo = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//select[@id='target']"))));
        Select comboBoxTarget = new Select(comboBoxTwo);
        comboBoxTarget.selectByVisibleText("Item 3");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
