package automationtests;

import config.WebDriverWrapper;
import org.testng.annotations.Test;

/**
 * Created by Steve on 6/26/2019.
 */
public class firstTestClass extends WebDriverWrapper {

    @Test
    public void mainTest() {
        ops.navOps.navigateToURL("https://login.stage.transloc.com/login/?next=https://architect.stage.transloc.com/");
        ops.navOps.login("qa_user_3", "n4km@XrhP4MA");

    }
}
