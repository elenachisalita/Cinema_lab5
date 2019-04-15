package java.Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClientValidator implements IValidator<Client>{

    public void validate (Client client) {

        String errors="";

        if(client.getCNP().length() != 13) {
            errors+="CNP must have 14 characters";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            format.parse(client.getDateOfBirth());

        } catch (ParseException pe) {
            throw new RuntimeException("The date of birth is not in a correct form!");
        }

        try {
            format.parse (client.getDateOfRegistration());
        } catch (ParseException pe) {
            throw new RuntimeException("The date of registration is not in a correct form!");
        }
    }

}
