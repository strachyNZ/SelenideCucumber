package stepdefs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import java.time.Duration;
import java.time.Instant;
import org.apache.commons.configuration2.PropertiesConfiguration;
import static com.codeborne.selenide.Selenide.open;


public class CucumberHooks {
    final String env_property_file = "./src/main/resources/environment.properties";

    private static Category log = Logger.getLogger(CucumberHooks.class);
    private Instant startTime;

    @Before
    public void beforeScenario(Scenario scenario){

        startTime = Instant.now();
        try {
            FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                    .configure(new Parameters().properties()
                    .setFileName(env_property_file)
                    .setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
                    .setIncludesAllowed(false));
            PropertiesConfiguration config = builder.getConfiguration();

            Configuration.browser = config.getString("browser");
            System.setProperty("webdriver.chrome.driver", config.getString("chromeDriver"));
            Configuration.baseUrl = config.getString("baseUrl");
            Configuration.browserSize = config.getString("browserSize");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        open("/");
        log.info(String.format("Opening browser at base URL '%s' for scenario '%s'", Configuration.baseUrl, scenario.getName()));
    }

    @After
    public void afterScenario(Scenario scenario){
        log.info(String.format("Completed scenario '%s' in %s seconds", scenario.getName(), Duration.between(startTime, Instant.now()).getSeconds()));
        WebDriverRunner.getWebDriver().close();
    }

}
