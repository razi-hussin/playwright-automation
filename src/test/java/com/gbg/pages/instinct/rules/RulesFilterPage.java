package com.gbg.pages.instinct.rules;

import com.gbg.pages.base.Base;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RulesFilterPage extends Base {

    private final Locator ruleCode;
    private final Locator ruleDescription;
    private final Locator ruleScore;
    private final Locator ruleSets;

    public RulesFilterPage(Page page) {
        super(page);
        this.ruleCode = ByRole("TEXTBOX").first();
        this.ruleDescription = ByRole("TEXTBOX").nth(1);
        this.ruleScore = ByRole("TEXTBOX").nth(2);
        this.ruleSets = ByRole("TEXTBOX").nth(3);
    }

    public void setRuleCode(String value) {
        ruleCode.fill(value);
    }

    public void setRuleDescription(String value) {
        ruleDescription.fill(value);
    }

    public void setRuleScore(String value) {
        ruleScore.fill(value);
    }

    public void setRuleSets(String value) {
        ruleSets.fill(value);
    }

}
