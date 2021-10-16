package at.study.redmine.db.requests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.Email;
import at.study.redmine.model.User;

import java.util.List;
import java.util.Map;

public class EmailRequests implements Create<Email> {
    User user;

    @Override
    public void create(Email email) {
        String query = "INSERT INTO public.email_addresses\n" +
                "(user_id, address, is_default, \"notify\", created_on, updated_on)\n" +
                "VALUES(?, ?, ?, ?, ?, ?) returning id;\n";

        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query,
                email.getUserId(),
                email.getAddress(),
                email.getIsDefault(),
                email.getNotify(),
                email.getCreatedOn(),
                email.getUpdatedOn()
                );
        Integer emailID = (Integer) result.get(0).get("id");
        email.setId(emailID);
    }


}
