public class Programmer extends Person{
    private int nCloseSwitchTimes = 0;

    public Programmer(int nID) {
        super(nID);
        super.setProgrammer();
    }

    public void closeSwitch(House house){
        house.setSwitch(false);
        nCloseSwitchTimes++;
    }

    public int getCloseSwitchTimes() {
        return nCloseSwitchTimes;
    }
}

