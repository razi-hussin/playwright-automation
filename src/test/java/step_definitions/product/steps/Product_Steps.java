package step_definitions.product.steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import com.gbg.product.page.domain.ProductSummary;
import fixtures.PlaywrightCucumberFixtures;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import com.gbg.product.page.pages.NavBar;
import com.gbg.product.page.pages.ProductList;
import com.gbg.product.page.pages.SearchComponent;

import java.util.List;
import java.util.Map;

public class Product_Steps {

    Page page;
    NavBar navBar;
    SearchComponent searchComponent;
    ProductList productList;

    @Step("Setup page objects for Product feature interactions")
    @Before(order = 1)
    public void setupPageObjects() {
        page = PlaywrightCucumberFixtures.getPage();
        navBar = new NavBar(page);
        searchComponent = new SearchComponent(page);
        productList = new ProductList(page);
    }

    @Given("Sally is on the homepage")
    public void sallyIsOnTheHomepage() {
        navBar.openHomePage();
    }

    @When("she searches for {string}")
    public void sheSearchesFor(String keyword) {
        searchComponent.searchBy(keyword);
    }

    @Then("the {string} product should be displayed")
    public void theProductShouldBeDisplayed(String productName) {
        var matchingProducts = productList.getProductNames();
        Assertions.assertThat(matchingProducts).contains(productName);
    }

    @Then("the following product should be displayed")
    public void theFollowingProductShouldBeDisplayed(List<String> expectedProducts) {
        var matchingProducts = productList.getProductNames();
        Assertions.assertThat(matchingProducts).containsAll(expectedProducts);
    }

    @DataTableType
    public ProductSummary productSummaryRow(Map<String, String> productData) {
        return new ProductSummary(productData.get("Product"), productData.get("Price"));
    }

    @Then("the following product table should be displayed")
    public void theFollowingProductTableShouldBeDisplayed(List<ProductSummary> expectedProductSummaries) {
        List<ProductSummary> matchingProducts = productList.getProductSummaries();
        Assertions.assertThat(matchingProducts).containsExactlyInAnyOrderElementsOf(expectedProductSummaries);
    }

    @Then("no product should be displayed")
    public void noProductShouldBeDisplayed() {
        List<ProductSummary> matchingProducts = productList.getProductSummaries();
        Assertions.assertThat(matchingProducts).isEmpty();
    }

    @And("{string} should be displayed")
    public void shouldBeDisplayed(String messageText) {
        String completionMessage = productList.getSearchCompletedMessage();
        Assertions.assertThat(completionMessage).isEqualTo(messageText);
    }

    @And("she filters by {string}")
    public void sheFiltersBy(String filterName) {
        searchComponent.filterBy(filterName);
    }

    @When("she sorts by {string}")
    public void sheSortsBy(String sortFilter) {
        searchComponent.sortBy(sortFilter);
    }

    @Then("the first product displayed should be {string}")
    public void theFirstProductDisplayedShouldBe(String firstProductName) {
        List<String> productNames = productList.getProductNames();
        Assertions.assertThat(productNames.get(0)).startsWith(firstProductName);
    }
}
