package admin.database;

import backend.admin.login.database.login.AdminQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AdminQueryBuilder class test suite")
public class AdminQueryBuilderTest {

    @Test
    @DisplayName("selectAdminByEmailQuery() test")
    public void selectAdminByEmailQuery() {
        final String email = "johndoe@gmail.com";
        final AdminQueryBuilder adminQueryBuilder = AdminQueryBuilder.getInstance();
        final String actualAdminByEmailQuery = adminQueryBuilder.selectAdminByEmailQuery(email);
        final String expectedAdminByEmailQuery = "SELECT admin_id, email, password FROM admin WHERE email=\"johndoe@gmail.com\";";
        Assertions.assertEquals(expectedAdminByEmailQuery, actualAdminByEmailQuery, "Incorrect method implementation: selectAdminByEmailQuery()");
    }
}
