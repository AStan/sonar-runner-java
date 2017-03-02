import org.junit.*;

/**
 * Created by astan on 3/2/2017.
 */
public class SonarRunnerTest {

    @Test
    public void testInit(){
        SonarRunner sr = new SonarRunner();

        System.out.println(sr.projectName);
        System.out.println(sr.repositoryURL);
        System.out.println(sr.repositoryType);
        System.out.println(sr.sonarRunnerHome);

        sr.initHashMap();

    }
}
