package webBeanExtra;

public class time {
    private final String time0;
    private final String time1;
    private final String time2;
    private final String time3;
    private final int tablenum;

    public time(String time0, String time1, String time2, String time3, int tablenum) {
        this.time0 = time0;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.tablenum = tablenum;
    }

    public String getTime0() {
        return time0;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public String getTime3() {
        return time3;
    }

    public int getTablenum() {
        return tablenum;
    }

}
