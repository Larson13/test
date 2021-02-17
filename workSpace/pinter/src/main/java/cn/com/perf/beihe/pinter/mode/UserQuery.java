package cn.com.perf.beihe.pinter.mode;

public class UserQuery {
    private String phoneNum;

    private String optCode;

    private String timestamp;

    private String sign;

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getOptCode() {
        return this.optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
