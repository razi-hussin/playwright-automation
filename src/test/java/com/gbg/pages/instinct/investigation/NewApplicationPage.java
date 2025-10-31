package com.gbg.pages.instinct.investigation;

import com.gbg.pages.base.Base;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class NewApplicationPage extends Base {

    private final Locator demandeId;
    private final Locator demandeIdUpdate;
    private final Locator demandeDate;
    private final Locator emprunteurIdentifiantClient;
    private final Locator emprunteurNom;
    private final ThreadLocal<String> counter = ThreadLocal.withInitial(() -> null);
    private final ThreadLocal<String> demandeIdValue = ThreadLocal.withInitial(() -> null);

    public NewApplicationPage(Page page) {
        super(page);
        this.demandeId = ByRoles("ROW", "ID (TBD)", "TEXTBOX");
        this.demandeIdUpdate = page.locator("//div[contains(.,'ID (TBD)')]/ancestor::tr//input[@type='text']");
        this.demandeDate = ByRoles("ROW", "Date demande", "TEXTBOX");
        this.emprunteurIdentifiantClient = ByRoles("ROW", "Identifiant client", "TEXTBOX");
        this.emprunteurNom = ByRoles("ROW", "Nom", "TEXTBOX");
    }

    public void setDemandeId(String value) {
        setDemandeIdValue(value);
        demandeId.fill(value);
    }

    public void setDemandeIdUpdate(String value) {
        demandeIdUpdate.fill(value);
    }

    public void setDemandeDate(String value) {
        demandeDate.fill(value);
    }

    public void setEmprunteurIdentifiantClient(String value) {
        emprunteurIdentifiantClient.fill(value);
    }

    public void setEmprunteurNom(String value) {
        emprunteurNom.fill(value);
    }


    public void setCounter(String value) {
        counter.set(value);
    }

    public String getCounter() {
        return counter.get();
    }

    public void setDemandeIdValue(String value) {
        demandeIdValue.set(value);
    }

    public String getDemandeIdValue() {
        return demandeIdValue.get();
    }


}
