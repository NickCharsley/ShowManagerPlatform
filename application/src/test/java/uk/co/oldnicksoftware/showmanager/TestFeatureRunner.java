package uk.co.oldnicksoftware.showmanager;

import cucumber.api.jelly.Cucumber;
import cucumber.api.jelly.Cucumber.Jelly;
import cucumber.api.jelly.Cucumber.Jelly.Level;
import cucumber.api.junit.Cucumber.Options;
import org.junit.runner.RunWith;

/**
 *
 * @author nick
 */
@RunWith(Cucumber.class)
/**/
@Options(
        format = { "pretty", "html:target/cucumber" }
       ,glue={"uk.co.oldnicksoftware.showmanager.steps"}
       //,monochrome = true
       ,tags="@wip" // Only run wip features 
       //,tags={"~@tbc","~@wip"} //Only run compleated features
)
/**/
@Jelly(
        gui=true,
        failOnMessage=Level.WARNING,
        failOnException=Level.INFO,
        enableClasspathModules=true, 
        clusters=".*"
)
/**/
public class TestFeatureRunner {
    
}