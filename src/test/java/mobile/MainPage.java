package mobile;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/search_container")
    private WebElement searchContainer;

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchInput;

    @AndroidFindBy(id = "org.wikipedia:id/menu_icon")
    private WebElement menuButton;

    @AndroidFindBy(id = "org.wikipedia:id/nav_featured_article_card_title")
    private WebElement featuredArticleTitle;

    public void clickSearchContainer() {
        searchContainer.click();
    }

    public void searchFor(String query) {
        searchInput.sendKeys(query);
    }

    public void clearSearch() {
        searchInput.clear();
    }

    public void openMenu() {
        menuButton.click();
    }

    public String getFeaturedArticleTitle() {
        return featuredArticleTitle.getText();
    }

    public boolean isSearchDisplayed() {
        return searchInput.isDisplayed();
    }
}