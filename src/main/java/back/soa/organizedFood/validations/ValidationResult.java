package back.soa.organizedFood.validations;

public class ValidationResult {

    private String code;
    private String msg;
    private boolean isValid;

    public ValidationResult(){
        this.isValid = true;
    }

    public ValidationResult(String code, String msg){
        this.code = code;
        this.msg = msg;
        this.isValid = false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String createMsg(){
        return this.code + " - " + this.msg;
    }

    public boolean isValid() {
        return isValid;
    }
}
