public class Person {
    private int nID;
    private boolean isOut = false; //最初大家都是没放出来的
    private boolean isOpenSwitch = false; //最初大家都是没开过开关的
    private boolean isProgrammer = false;

    public Person(int nID){
        this.nID = nID;
    }

    public void Out(){
        this.isOut = true;
    }

    public void openSwitch(House house){
        house.setSwitch(true);
        isOpenSwitch = true;
    }

    public boolean isOut() {
        return isOut;
    }


    public int getID() {
        return nID;
    }

    public boolean isProgrammer() {
        return isProgrammer;
    }

    void setProgrammer() {
        isProgrammer = true;
    }

    public boolean isOpenSwitch() {
        return isOpenSwitch;
    }
}

