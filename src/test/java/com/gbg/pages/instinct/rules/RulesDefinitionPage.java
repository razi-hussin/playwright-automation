package com.gbg.pages.instinct.rules;

import com.gbg.pages.base.Base;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RulesDefinitionPage extends Base {

    private final Locator ruleCode;
    private final Locator ruleDescription;
    private final Locator ruleScore;
    private final Locator assignmentValue;
    private final ThreadLocal<String> ruleCodeValue = ThreadLocal.withInitial(() -> null);

    public RulesDefinitionPage(Page page) {
        super(page);
        this.ruleCode = page.locator("//input[@formcontrolname='ruleCode']");
        this.ruleDescription = page.locator("//input[@formcontrolname='description']");
        this.ruleScore = page.locator("//input[@formcontrolname='ruleScore']");
        this.assignmentValue = page.locator("//textarea[@formcontrolname='assignmentValue']");
    }

    public void setRuleCode(String value) {
        setRuleCodeValue(value);
        ruleCode.fill(value);
    }

    public void setRuleDescription(String value) {
        ruleDescription.fill(value);
    }

    public void setRuleScore(String value) {
        ruleScore.fill(value);
    }

    public void setAssignmentValue(String value) {
        assignmentValue.fill(value);
    }


    public void setRuleCodeValue(String value) {
        ruleCodeValue.set(value);
    }

    public String getRuleCodeValue() {
        return ruleCodeValue.get();
    }

}
