package email_sender_microservice.controller;


import com.j256.ormlite.dao.Dao;
import email_sender_microservice.model.Client;
import email_sender_microservice.model.Email;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static email_sender_microservice.model.Email.isValidEmailAddress;

public class EmailController {

    /**
     * Returns a simple string, this is only used to check if the request/response is working as intended.
     *
     * @param request
     * @param response
     * @return A string.
     */
    public String status(Request request, Response response) {
        return "ok";
    }

    /**
     * Gets a request then it creates a JSONObject from the request's body.
     * <p>
     * Then parse this object for four keys: "to", "from", "subject", "message".
     * <p>
     * If the request contains no information of empty, returns false.
     * Uses isValidEmailAddress to validate the "to" and "from" emails.
     * If the emails are valid and none of the keys are empty, the request's body gets saved to the database.
     *
     * @param request JSON object from request's body.
     * @param response
     * @param emailDao
     * @return Boolean.
     */
    public boolean createEmail(Request request, Response response, Dao<Email, String> emailDao) {
        JSONObject emailDetail = new JSONObject(request.body());
        ArrayList<String> keyList = new ArrayList<>(Arrays.asList("to", "from", "subject", "message"));
        for (String key : keyList) if (emailDetail.isNull(key)) return false;

        if (isValidEmailAddress(emailDetail.getString("to")) && isValidEmailAddress(emailDetail.getString("from"))) {
            Email email = new Email(emailDetail.getString("to"),
                                    emailDetail.getString("from"),
                                    emailDetail.getString("subject"),
                                    emailDetail.getString("message"));

            try {
                emailDao.create(email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * This method still needs to be implemented. No further comments required until it is done.
     *
     * @param request
     * @param response
     * @param clientDao
     * @return Boolean.
     */
    public boolean register(Request request, Response response, Dao<Client, String> clientDao) {
        return true;
    }

}
