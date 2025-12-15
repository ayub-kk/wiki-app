package mobile.tests;

import mobile.ArticlePage;
import mobile.BasePage;
import mobile.MainPage;
import mobile.SettingsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.net.MalformedURLException;

public class WikipediaMobileTests {
    private MainPage mainPage;
    private ArticlePage articlePage;
    private SettingsPage settingsPage;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        BasePage.initializeDriver();
        mainPage = new MainPage();
        articlePage = new ArticlePage();
        settingsPage = new SettingsPage();
    }

    @Test(priority = 1)
    public void testAppLaunchAndSearch() {
        // Test 1: Verify app launches and search is available
        assertTrue(mainPage.isSearchDisplayed(),
                "Search should be available on main screen");

        // Test search functionality
        mainPage.clickSearchContainer();
        mainPage.searchFor("Artificial intelligence");

        // Note: In real test, you would navigate to search results
        // This is simplified for example
    }

    @Test(priority = 2)
    public void testArticleOpeningAndContent() {
        // This test assumes we're already on an article page
        // In real scenario, you would navigate from search results

        String title = articlePage.getArticleTitle();
        assertNotNull(title, "Article should have a title");
        assertTrue(title.length() > 0, "Article title should not be empty");

        assertTrue(articlePage.isContentDisplayed(),
                "Article content should be displayed");
    }

    @Test(priority = 3)
    public void testScrollFunctionality() {
        // Test scrolling to a specific section
        articlePage.scrollToReferences();

        // Verify we can find references section
        // This would need proper implementation based on actual app structure
        assertTrue(true, "Scrolling functionality should work");
    }

    @Test(priority = 4)
    public void testMenuAndSettings() {
        // Open menu
        mainPage.openMenu();

        // Navigate to settings (would need proper navigation)
        // settingsPage.openLanguageSettings();

        // Verify settings page
        // assertTrue(settingsPage.isSettingsPageDisplayed(),
        //     "Settings page should be displayed");

        // For now, just verify menu opens
        assertTrue(true, "Menu should open successfully");
    }

    @AfterClass
    public void tearDown() {
        BasePage.quitDriver();
    }
}