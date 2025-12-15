package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(id = "firstHeading")
    private WebElement pageTitle;

    @FindBy(css = ".mw-search-results li")
    private List<WebElement> searchResults;

    @FindBy(css = ".suggestions-results a")
    private List<WebElement> suggestionResults;

    public String getArticleTitle() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public int getSearchResultsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        return searchResults.size();
    }

    public void clickFirstResult() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        if (!searchResults.isEmpty()) {
            searchResults.get(0).click();
        }
    }

    public boolean areSuggestionsDisplayed() {
        return !suggestionResults.isEmpty();
    }
}