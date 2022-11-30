public class RollenRender {

    //Attribute
    private boolean requirementsEngineer;
    private boolean testManager;
    private boolean testfallErsteller;
    private boolean tester;

    //Konstruktoren
    public RollenRender(){}

    //Methoden
    public void render(Benutzer benutzer){
        try {
            if (benutzer.getRolle().equals("Requirements Engineer")){
                this.requirementsEngineer = true;
                this.testfallErsteller = false;
                this.testManager = false;
                this.tester= false;
            }else if(benutzer.getRolle().equals("Testfallersteller")){
                this.requirementsEngineer = false;
                this.testfallErsteller = true;
                this.testManager = false;
                this.tester= false;
            }else if (benutzer.getRolle().equals("Testmanager")){
                this.requirementsEngineer = false;
                this.testfallErsteller = false;
                this.testManager = true;
                this.tester= false;
        }else if (benutzer.getRolle().equals("Tester")){
                this.requirementsEngineer = false;
                this.testfallErsteller = false;
                this.testManager = false;
                this.tester= true;
        }
        }catch(Exception e){
            System.out.println("der Benutzer wurde nicht korrekt gesetzt");
        }
    }

    //Getter and Setter
    public boolean isRequirementsEngineer() {
        return requirementsEngineer;
    }
    public void setRequirementsEngineer(boolean requirementsEngineer) {
        this.requirementsEngineer = requirementsEngineer;
    }

    public boolean isTestManager() {
        return testManager;
    }
    public void setTestManager(boolean testManager) {
        this.testManager = testManager;
    }

    public boolean isTestfallErsteller() {
        return testfallErsteller;
    }
    public void setTestfallErsteller(boolean testfallErsteller) {
        this.testfallErsteller = testfallErsteller;
    }

    public boolean isTester() {
        return tester;
    }
    public void setTester(boolean tester) {
        this.tester = tester;
    }
}
