package blood_bank_camp.database;

import backend.blood_bank_camp.database.camp_database.CampQueryBuilder;
import backend.blood_bank_camp.model.Camp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CampQueryBuilderTest {

  @Test
  @DisplayName("Camp insert query test")
  public void insertCampQueryTest() {
    final Camp camp = new Camp("Blood Services",
        1,
        "2021-05-12",
        "16:00",
        120,
        "Alberta Street",
        "Halifax",
        "9023451234");

    final String actualInsertUserQuery = CampQueryBuilder.getInstance().insertCampQuery(camp);
    final String expectedInsertUserQuery = "INSERT INTO camp(organizer_name, blood_bank_id, camp_date, camp_time, available_capacity, venue, city, contact)" +
        "VALUES (\"Blood Services\", \"1\", \"2021-05-12\", \"16:00\", \"120\", \"Alberta Street\", \"Halifax\", \"9023451234\")";
    Assertions.assertEquals(actualInsertUserQuery, expectedInsertUserQuery, "Incorrect insert user query.");
  }
}
