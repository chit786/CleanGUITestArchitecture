package com.neoranga55.cleanguitestarchitecture.cucumber.steps;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.neoranga55.cleanguitestarchitecture.LoginActivity;
import com.neoranga55.cleanguitestarchitecture.cucumber.pages.BasePage;
import com.neoranga55.cleanguitestarchitecture.cucumber.pages.LoginPage;
import com.neoranga55.cleanguitestarchitecture.cucumber.pages.WelcomePage;
import com.neoranga55.cleanguitestarchitecture.util.ActivityFinisher;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * This defines all the translations from Gherkin (semi-English) sentences to Java
 */
public class StepDefinitions extends ActivityInstrumentationTestCase2<LoginActivity> {

    public static final String TAG = StepDefinitions.class.getSimpleName();
    private Context mInstrumentationContext;
    private Context mAppContext;
    private BasePage mCurrentPage;
    private Activity mActivity;

    public StepDefinitions() {
        super(LoginActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mInstrumentationContext = getInstrumentation().getContext();
        mAppContext = getInstrumentation().getTargetContext();
        mActivity = getActivity(); // Start Activity before each test scenario
        assertNotNull(mActivity);
    }

    @After
    public void tearDown() throws Exception {
        ActivityFinisher.finishOpenActivities(); // Required for testing App with multiple activities
        // All the clean up of application's data and state after each scenario must happen here
        super.tearDown(); // This step scrubs everything in this class so always call it last
    }

    @Given("^I see the login page$")
    public void i_see_the_login_page() {
        mCurrentPage = new LoginPage();
    }

    @When("^I login with user name \"(.+)\" and password \"(.+)\"$")
    public void i_login_with_username_and_password(final String userName, final String password) {
        mCurrentPage = mCurrentPage.is(LoginPage.class).doLogin(userName, password);
    }

    @Then("^I see the welcome page$")
    public void i_see_the_welcome_page() {
        mCurrentPage.is(WelcomePage.class);
    }

    @And("^the title is \"(.+)\"$")
    public void the_title_is(final String title) {
        mCurrentPage.is(WelcomePage.class).checkTitle(title);
    }
}
