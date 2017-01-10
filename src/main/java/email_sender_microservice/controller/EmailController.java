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


    public String status(Request request, Response response) {
        return "ok";
    }

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

    public boolean register(Request request, Response response, Dao<Client, String> clientDao) {
        return true;
    }

}
