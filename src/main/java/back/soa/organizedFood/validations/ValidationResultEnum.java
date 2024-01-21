package back.soa.organizedFood.validations;

public enum ValidationResultEnum {
    VALID_RESULT(new ValidationResult()),
    REQUEST_NOT_VALIDATE(new ValidationResult("RNV", "Los datos de la request no son validos")),
    GENERIC_ERROR(new ValidationResult("GEE", "Ha ocurrido un Error"));

    private ValidationResult validationResult;

    private ValidationResultEnum(ValidationResult validationResult){
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }
}