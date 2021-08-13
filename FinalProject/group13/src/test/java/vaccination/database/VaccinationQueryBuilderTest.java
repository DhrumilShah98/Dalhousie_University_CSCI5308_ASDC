package vaccination.database;

import backend.vaccination.database.VaccinationQueryBuilder;
import backend.vaccination.database.VaccinationQueryBuilderDAO;
import backend.vaccination.model.VaccineModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Vaccination Query Builder test suite")
public class VaccinationQueryBuilderTest {

    @Test
    @DisplayName("User's information of first dose select Query")
    public void getInformationOfFirstDose() {
        final String expectedFirstDoseQuery = "SELECT first_dose_date FROM vaccine WHERE user_id = 9;";
        final VaccinationQueryBuilder vaccinationQueryBuilder = VaccinationQueryBuilder.getInstance();
        final String actualFirstDoseQuery = vaccinationQueryBuilder.getInformationOfFirstDose(9);
        Assertions.assertEquals(actualFirstDoseQuery, expectedFirstDoseQuery, "Incorrect select first dose information query.");
    }
    @Test
    @DisplayName("User's vaccination information insert Query")
    public void setVaccinationInformation() {
        final String expectedFirstDoseQuery = "INSERT INTO vaccine(user_id,vaccine_type,registration_date,first_dose_date,second_dose_date)" +
                " VALUES ( 9,'Moderna','1998-12-12','1998-12-12','1998-12-12');";
        final VaccinationQueryBuilder vaccinationQueryBuilder = VaccinationQueryBuilder.getInstance();
        VaccineModel vaccineModel = new VaccineModel(
            9,
            "Moderna",
            "1998-12-12",
            "1998-12-12",
            "1998-12-12",
            false
        );
        final String actualFirstDoseQuery = vaccinationQueryBuilder.setVaccinationInformation(vaccineModel);
        Assertions.assertEquals(actualFirstDoseQuery, expectedFirstDoseQuery, "Incorrect select first dose information query.");
    }
    @Test
    @DisplayName("User's vaccination information insert Query")
    public void getInformationOfDoseDate() {
        final String expectedFirstDoseQuery = "SELECT vaccine_id,user_id,vaccine_type,registration_date,first_dose_date,second_dose_date FROM vaccine WHERE user_id = 9;";
        final VaccinationQueryBuilder vaccinationQueryBuilder = VaccinationQueryBuilder.getInstance();
        final String actualFirstDoseQuery = vaccinationQueryBuilder.getInformationOfDoseDate(9);
        Assertions.assertEquals(actualFirstDoseQuery, expectedFirstDoseQuery, "Incorrect user's vaccine data.");
    }
}
