package TeamAndersenTest;

import automationFramework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.TeamAndersen.PersonalInfo;
import pageObjects.TeamAndersen.TeamAndersenAuthPage;
import pageObjects.TeamAndersen.TeamAndersenPage;

public class TeamAndersenTests extends BaseTest {
    private TeamAndersenAuthPage teamAndersenAuthPage;

    @Test
    public void clientAuth() {
        teamAndersenAuthPage = new TeamAndersenAuthPage(webDriver);
        TeamAndersenPage teamAndersenPage =
                teamAndersenAuthPage.getPage().enterLogin().enterPassword().clickToSubmit();
        Assert.assertEquals("Klymenko Artur", teamAndersenPage.getName());
        Assert.assertEquals("3 O.Dashkovicha Street, Cherkasy, Ukraine", teamAndersenPage.getLocation());
    }

    @Test
    public void checkPersonalInfo() {
        teamAndersenAuthPage = new TeamAndersenAuthPage(webDriver);
        TeamAndersenPage teamAndersenPage =
                teamAndersenAuthPage.getPage().enterLogin().enterPassword().clickToSubmit();
        PersonalInfo personalInfo = teamAndersenPage.clickToAvatar();
        Assert.assertEquals("Cherkasy", personalInfo.getText(personalInfo.getLocation()));
        Assert.assertEquals("QA Engineer", personalInfo.getText(personalInfo.getPosition()));
        Assert.assertEquals("Karpenko Anzhela", personalInfo.getText(personalInfo.getHrInfo()));
        personalInfo.signOut();
    }

    @Test
    public void searchTest() {
        teamAndersenAuthPage = new TeamAndersenAuthPage(webDriver);
        TeamAndersenPage teamAndersenPage =
                teamAndersenAuthPage.getPage().enterLogin().enterPassword().clickToSubmit();
        teamAndersenPage.searchEmployee("Orlov");
        teamAndersenPage.getInitialsOfEmployee().forEach(s -> Assert.assertTrue(s.contains("Orlov")));
    }

    @Test
    public void getWikiPage() {
        teamAndersenAuthPage = new TeamAndersenAuthPage(webDriver);
        TeamAndersenPage teamAndersenPage =
                teamAndersenAuthPage.getPage().enterLogin().enterPassword().clickToSubmit();
        Assert.assertEquals(teamAndersenPage.getUrl(teamAndersenPage.getWebElement("Wiki")),
                "https://wiki.andersenlab.com/login.action?os_destination=%2Fdashboard.action&permissionViolation=true");
    }

    @Test
    public void getJiraLink() {
        teamAndersenAuthPage = new TeamAndersenAuthPage(webDriver);
        TeamAndersenPage teamAndersenPage =
                teamAndersenAuthPage.getPage().enterLogin().enterPassword().clickToSubmit();
        Assert.assertEquals(teamAndersenPage.getUrl(teamAndersenPage.getWebElement("Jira")),
                "https://jira.andersenlab.com/secure/Dashboard.jspa");
    }

    @Test
    public void getSupportLink() {
        teamAndersenAuthPage = new TeamAndersenAuthPage(webDriver);
        TeamAndersenPage teamAndersenPage =
                teamAndersenAuthPage.getPage().enterLogin().enterPassword().clickToSubmit();
        Assert.assertEquals(teamAndersenPage.getUrl(teamAndersenPage.getWebElement("Support")),
                "https://jsupport.andersenlab.com/servicedesk/customer/user/login?destination=portals");
    }

}
