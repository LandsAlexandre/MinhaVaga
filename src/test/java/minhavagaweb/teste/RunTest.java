package minhavagaweb.teste;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author ISM
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        format = {
            "pretty",
            "html:target/cucumber-html-report",
            "json-pretty:target/cucumber-json-report.json"
        }
)
public class RunTest {
}
