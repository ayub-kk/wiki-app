package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    // 1. Локатор логотипа (более надежный)
    @FindBy(css = "svg.wikipedia-wordmark") // или "div.central-featured"
    private WebElement wikiLogo;

    // 2. Локатор для поиска (если нужно)
    @FindBy(id = "searchInput")
    private WebElement searchInput;

    // 3. Ссылка на русский язык - несколько вариантов на выбор
    @FindBy(xpath = "//a[contains(@href, 'ru.wikipedia.org')]")
    private WebElement russianLanguageLink;

    // Альтернативные локаторы для русского языка (закомментируйте ненужные):
    // @FindBy(xpath = "//strong[text()='Русский']/ancestor::a")
    // @FindBy(css = "a[href='//ru.wikipedia.org/']")
    // @FindBy(xpath = "//a[@title='Русский']")

    // 4. Блоки с языками (для проверки их наличия)
    @FindBy(css = "div.central-featured-lang")
    private List<WebElement> languageBlocks;

    // Методы:

    public void open() {
        driver.get("https://www.wikipedia.org");
        // Ждем загрузки страницы по любому видимому элементу
        wait.until(ExpectedConditions.visibilityOf(searchInput));
    }

    // Исправленный метод проверки логотипа
    public boolean isLogoDisplayed() {
        try {
            // Ждем немного перед проверкой
            Thread.sleep(1000);
            return wikiLogo.isDisplayed();
        } catch (Exception e) {
            System.out.println("Логотип не найден. Попробую альтернативный локатор...");
            // Попытка найти логотип другим способом
            try {
                WebElement altLogo = driver.findElement(By.cssSelector("div.central-featured"));
                return altLogo.isDisplayed();
            } catch (Exception ex) {
                System.out.println("Альтернативный логотип тоже не найден");
                return false;
            }
        }
    }

    public boolean isSearchInputEnabled() {
        return searchInput.isEnabled();
    }

    // Исправленный метод клика по русской ссылке
    public void clickRussianLanguageLink() {
        try {
            // Сначала прокручиваем страницу, чтобы элемент был виден
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", russianLanguageLink);

            // Ждем, пока элемент станет кликабельным
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.elementToBeClickable(russianLanguageLink));

            // Кликаем
            russianLanguageLink.click();
            System.out.println("Успешно кликнули на ссылку русского языка");
        } catch (Exception e) {
            System.out.println("Не удалось кликнуть стандартным способом. Пробую альтернативные локаторы...");

            // Пробуем альтернативные способы найти ссылку
            try {
                // Вариант 1: По тексту "Русский"
                List<WebElement> links = driver.findElements(By.xpath("//a[contains(text(), 'Русский')]"));
                if (!links.isEmpty()) {
                    links.get(0).click();
                    System.out.println("Кликнули по ссылке с текстом 'Русский'");
                    return;
                }

                // Вариант 2: По href
                links = driver.findElements(By.cssSelector("a[href*='ru.wikipedia']"));
                if (!links.isEmpty()) {
                    links.get(0).click();
                    System.out.println("Кликнули по ссылке с href содержащим 'ru.wikipedia'");
                    return;
                }

                // Вариант 3: Найти любой элемент с русским языком
                links = driver.findElements(By.cssSelector("a[class*='link-box']"));
                for (WebElement link : links) {
                    if (link.getText().contains("Русский")) {
                        link.click();
                        System.out.println("Нашли и кликнули по элементу с текстом 'Русский'");
                        return;
                    }
                }

                throw new RuntimeException("Не удалось найти ссылку на русский язык ни одним способом");
            } catch (Exception ex) {
                throw new RuntimeException("Все способы поиска русской ссылки не сработали: " + ex.getMessage());
            }
        }
    }

    public List<String> getFeaturedLanguages() {
        List<String> languages = new ArrayList<>();
        try {
            for (WebElement langBlock : languageBlocks) {
                String text = langBlock.getText();
                if (!text.isEmpty()) {
                    // Берем первую строку текста (обычно название языка)
                    String languageName = text.split("\n")[0].trim();
                    languages.add(languageName);
                }
            }
        } catch (Exception e) {
            System.out.println("Не удалось получить список языков: " + e.getMessage());
        }
        return languages;
    }

    // Дополнительный метод для поиска
    public void searchFor(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
        searchInput.submit(); // Используем submit вместо поиска кнопки
    }
}