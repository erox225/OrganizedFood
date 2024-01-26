package back.soa.organizedFood.validations;

import org.springframework.http.HttpStatus;

public enum ValidationResultEnum {

    VALID_RESULT("TOK","Todo Ok",HttpStatus.OK),
    REQUEST_NOT_VALIDATE("RNV", "Los datos de la request no son validos", HttpStatus.BAD_REQUEST),
    GENERIC_ERROR("GEE", "Ha ocurrido un Error", HttpStatus.INTERNAL_SERVER_ERROR);

    private String code;
    private String value;
    private HttpStatus httpStatus;

    private ValidationResultEnum(String code,String value, HttpStatus httpStatus){
        this.code = code;
        this.value = value;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ValidationResultEnum getValidationResult(){
        return VALID_RESULT;
    }
}