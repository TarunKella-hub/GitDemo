package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="Sel.stepDefinitions",tags="@ErrorValidations", monochrome=true, plugin= {"html:cucumber/cuc.html"})

public class TestNGRunnerCucmber extends AbstractTestNGCucumberTests{

}
