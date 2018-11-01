package version;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {AppConfig.class})
@RunWith(SpringRunner.class)
public class VersionTest {

    @Autowired
    private Version version;

    @Test
    public void testVersionIsInSection() {
        TestCase.assertEquals(version.versionIsInSection(950, 930, 1000),
                VersionUpdate.betweenEqual("9.5.0", "9.3.0", "10.0.0"));

        TestCase.assertEquals(version.versionIsInSection(930, 950, 1000),
                VersionUpdate.betweenEqual("9.3.0", "9.5.0", "10.0.0"));

        TestCase.assertEquals(version.versionIsInSection(950, 930, 1000),
                VersionUpdate.betweenEqual("9.5", "9.3.0", "10.0.0"));

        TestCase.assertEquals(version.versionIsInSection(930, 950, 1000),
                VersionUpdate.betweenEqual("9.3", "9.5.0", "10.0.0"));

        TestCase.assertEquals(version.versionIsInSection(950, 950, 1000),
                VersionUpdate.betweenEqual("9.5", "9.5.0", "10.0.0"));

        TestCase.assertEquals(version.versionIsInSection(950, 950, 1000),
                VersionUpdate.betweenEqual("9.5.0", "9.5.0", "10.0.0"));
    }

    @Test
    public void testCheckAppVersion() {
        TestCase.assertEquals(version.checkAppVersion("9.5.0", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.5.0", "9.4.0"));

        TestCase.assertEquals(version.checkAppVersion("9.4.0", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.4.0", "9.4.0"));

        TestCase.assertEquals(version.checkAppVersion("9.5", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.5", "9.4.0"));

        TestCase.assertEquals(version.checkAppVersion("9.4", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.4", "9.4.0"));

        TestCase.assertEquals(version.checkAppVersion("9.4.100", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.4.100", "9.4.0"));

        TestCase.assertEquals(version.checkAppVersion("10.1.0", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("10.1.0", "9.4.0"));
    }


    @Test
    public void testCompare() {
        TestCase.assertEquals(version.compare("9.5.0", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.5.0", "9.4.0"));

        TestCase.assertEquals(version.compare("9.3.0", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.3.0", "9.4.0"));

        TestCase.assertEquals(version.compare("9.4.0", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.4.0", "9.4.0"));

        TestCase.assertEquals(version.compare("9.4", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.4", "9.4.0"));

        TestCase.assertEquals(version.compare("9.5.20", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("9.5.20", "9.4.0"));

        TestCase.assertEquals(version.compare("10.1.0", "9.4.0"),
                VersionUpdate.greaterThanOrEqual("10.1.0", "9.4.0"));
    }

    @Test
    public void testParseVersion2Int() {
        TestCase.assertEquals(version.parseVersion2Int("2.8.0") < 270,
                VersionUpdate.lessThan("2.8.0", "2.7.0"));

        TestCase.assertEquals(version.parseVersion2Int("2.7.0") < 270,
                VersionUpdate.lessThan("2.7.0", "2.7.0"));

        TestCase.assertEquals(version.parseVersion2Int("2.8.100") < 270,
                VersionUpdate.lessThan("2.8.100", "2.7.0"));

        TestCase.assertEquals(version.parseVersion2Int("2.6.0") < 270,
                VersionUpdate.lessThan("2.6.0", "2.7.0"));

        TestCase.assertEquals(version.parseVersion2Int("2.7") < 270,
                VersionUpdate.lessThan("2.7", "2.7.0"));
    }

    @Test
    public void testGreater() {
        TestCase.assertEquals(version.parseVersion2Int("9.5") >= 950,
                VersionUpdate.greaterThanOrEqual("9.5", "9.5.0"));

        TestCase.assertEquals(version.parseVersion2Int("9.5.0") >= 950,
                VersionUpdate.greaterThanOrEqual("9.5.0", "9.5.0"));

        TestCase.assertEquals(version.parseVersion2Int("9.8.0") >= 950,
                VersionUpdate.greaterThanOrEqual("9.8.0", "9.5.0"));

        TestCase.assertEquals(version.parseVersion2Int("9.5.11") >= 950,
                VersionUpdate.greaterThanOrEqual("9.5.11", "9.5.0"));

        TestCase.assertEquals(version.parseVersion2Int("9.4") >= 950,
                VersionUpdate.greaterThanOrEqual("9.4", "9.5.0"));

        TestCase.assertEquals(version.parseVersion2Int("9.4.4") >= 950,
                VersionUpdate.greaterThanOrEqual("9.4.4", "9.5.0"));

    }

    @Test
    public void testCheck840() {
        TestCase.assertEquals(version.check840("9.5"),
                VersionUpdate.greaterThanOrEqual("9.5", "8.4.0"));

        TestCase.assertEquals(version.check840("9.5.0"),
                VersionUpdate.greaterThanOrEqual("9.5.0", "8.4.0"));

        TestCase.assertEquals(version.check840("8.4"),
                VersionUpdate.greaterThanOrEqual("8.4", "8.4.0"));

        TestCase.assertEquals(version.check840("8.4.0"),
                VersionUpdate.greaterThanOrEqual("8.4.0", "8.4.0"));

        TestCase.assertEquals(version.check840("8.3"),
                VersionUpdate.greaterThanOrEqual("8.3", "8.4.0"));

        TestCase.assertEquals(version.check840("8.2.0"),
                VersionUpdate.greaterThanOrEqual("8.2.0", "8.4.0"));

        TestCase.assertEquals(version.check840("9.5.12"),
                VersionUpdate.greaterThanOrEqual("9.5.12", "8.4.0"));

        TestCase.assertEquals(version.check840("10.0.1"),
                VersionUpdate.greaterThanOrEqual("10.0.1", "8.4.0"));
    }

    @Test
    public void testGetDPVersion() {
        TestCase.assertEquals(version.getDPVersion("9.0.8") < 908,
                VersionUpdate.lessThan("9.0.8", "9.0.8"));

        TestCase.assertEquals(version.getDPVersion("9.0.7") < 908,
                VersionUpdate.lessThan("9.0.7", "9.0.8"));

        TestCase.assertEquals(version.getDPVersion("10.0.1") < 908,
                VersionUpdate.lessThan("10.0.1", "9.0.8"));

        TestCase.assertEquals(version.getDPVersion("8.9.0") < 908,
                VersionUpdate.lessThan("8.9.0", "9.0.8"));

        TestCase.assertEquals(version.getDPVersion("9.0.10") < 908,
                VersionUpdate.lessThan("9.0.10", "9.0.8"));
    }
}
