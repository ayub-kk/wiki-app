package mobile;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/view_page_title_text")
    private WebElement articleTitle;

    @AndroidFindBy(id = "org.wikipedia:id/page_web_view")
    private WebElement articleContent;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"References\"))")
    private WebElement referencesSection;

    @AndroidFindBy(id = "org.wikipedia:id/view_page_subtitle_text")
    private WebElement articleSubtitle;

    @AndroidFindBy(className = "android.widget.TextView")
    private List<WebElement> textElements;

    public String getArticleTitle() {
        return articleTitle.getText();
    }

    public String getArticleSubtitle() {
        return articleSubtitle.getText();
    }

    public boolean isContentDisplayed() {
        return articleContent.isDisplayed();
    }

    public void scrollToReferences() {
        // Scrolling is handled by the locator strategy
    }

    public boolean isReferencesSectionVisible() {
        return referencesSection.isDisplayed();
    }

    public void scrollDown() {
        // You can implement scrolling logic here
        // For example using TouchAction or AndroidUIAutomator
    }
}