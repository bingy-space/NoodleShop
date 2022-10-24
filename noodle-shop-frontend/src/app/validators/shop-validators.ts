import { FormControl, ValidationErrors } from "@angular/forms";

export class ShopValidators {
    // Whitespace validation
    static notOnlyWhiteSpace(control: FormControl) : ValidationErrors{
        // Check if string only contains whitespace
        if((control.value != null) && (control.value.trim().length === 0)){
            // Invalid, return error object
            return { 'notOnlyWhitespace': true};
        }else{
            // Valid, return null
            return null;
        }
    }
}
