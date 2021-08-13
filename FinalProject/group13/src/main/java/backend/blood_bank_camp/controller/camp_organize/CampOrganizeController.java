package backend.blood_bank_camp.controller.camp_organize;

import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.blood_bank_camp.database.camp_database.CampQueryBuilderDAO;
import backend.blood_bank_camp.exception.CampRegistrationException;
import backend.blood_bank_camp.model.Camp;
import backend.blood_bank_camp.util.CampRegistrationValidation;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code CampOrganizeController} implements the
 * {@code CampOrganizeControllerDAO} to provide a concrete
 * implementation for the camp registration.
 */
public final class CampOrganizeController implements CampOrganizeControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // Camp query builder instance.
  private final CampQueryBuilderDAO campQueryBuilderDAO;


  /**
   * Constructs this {@code CampOrganizeController} instance.
   *
   * @param databaseConnectionDAO database connection instance.
   * @param campQueryBuilderDAO   camp registration
   *                              query builder instance.
   */
  public CampOrganizeController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final CampQueryBuilderDAO campQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.campQueryBuilderDAO = campQueryBuilderDAO;
  }

  /**
   * Validates camp fields.
   *
   * @param camp camp instance.
   */
  private void validateCampFields(Camp camp) throws CampRegistrationException {

    // Camp organizer name validation
    final boolean isOrganizerNameValid = (camp.getOrganizerName() != null) &&
        (!camp.getOrganizerName().trim().isEmpty()) &&
        (CampRegistrationValidation.isOrganizerNameValid(camp.getOrganizerName().trim()));
    if (!isOrganizerNameValid) {
      throw new CampRegistrationException("Invalid organizer name.");
    }

    // Camp date validation
    final boolean isCampDateValid = (camp.getDate() != null) &&
        (!camp.getDate().trim().isEmpty()) &&
        (CampRegistrationValidation.isDateFormatValid(camp.getDate().trim()));
    if (!isCampDateValid) {
      throw new CampRegistrationException("Invalid organizer name.");
    }

    // Camp Time format validation
    final boolean isCampTime = (camp.getTime() != null) &&
        (!camp.getTime().trim().isEmpty()) &&
        (CampRegistrationValidation.isTimeValid(camp.getTime().trim()));
    if (!isCampTime) {
      throw new CampRegistrationException("Invalid Time Format.");
    }

    // Camp validation
    final boolean isCampContactNumberValid = (camp.getContactNumber() != null) &&
        (!camp.getContactNumber().trim().isEmpty()) &&
        (CampRegistrationValidation.isContactNumberValid(camp.getContactNumber().trim()));
    if (!isCampContactNumberValid) {
      throw new CampRegistrationException("Invalid Contact Number.");
    }

    // Camp venue format validation
    final boolean isCampVenueValid = (camp.getVenue() != null) &&
        (!camp.getVenue().trim().isEmpty()) &&
        (CampRegistrationValidation.isVenueNameValid(camp.getVenue().trim()));
    if (!isCampVenueValid) {
      throw new CampRegistrationException("Invalid Venue name.");
    }

    // Camp address city validation
    final boolean isCampAddressCityValid = (camp.getCity() != null) &&
        (!camp.getCity().trim().isEmpty());
    if (!isCampAddressCityValid) {
      throw new CampRegistrationException("Invalid address city.");
    }
  }

  /**
   * @param camp camp insert in to the table.
   *
   * @return {@code true} if camp registered successfully otherwise
   * {@code false}.
   *
   * @throws CampRegistrationException   if any error occurs while
   *                                     camp registration.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public boolean register(Camp camp) throws CampRegistrationException, DatabaseConnectionException {
    if (camp == null) {
      throw new CampRegistrationException("Null camp details.");
    }
    //validate camp fields
    validateCampFields(camp);

    // trim extra spaces from camp
    camp.setOrganizerName(camp.getOrganizerName().trim());
    camp.setBloodBankId(camp.getBloodBankId());
    camp.setDate(camp.getDate().trim());
    camp.setTime(camp.getTime().trim());
    camp.setAvailableCapacity(camp.getAvailableCapacity());
    camp.setVenue(camp.getVenue().trim());
    camp.setCity(camp.getCity().trim());
    camp.setContactNumber(camp.getContactNumber().trim());
    ResultSet generatedKeysResultSet;

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement()) {
      final String insertCampQuery = campQueryBuilderDAO.insertCampQuery(camp);
      final int campRowInserted = statement.executeUpdate(insertCampQuery, Statement.RETURN_GENERATED_KEYS);

      if (campRowInserted > 0) {
        generatedKeysResultSet = statement.getGeneratedKeys();
        if (generatedKeysResultSet.next()) {
          return true;
        }
        generatedKeysResultSet.close();
      }
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
    return false;
  }
}