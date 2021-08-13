package backend.active_blood_receiver_request.controller;

import backend.active_blood_donor_request.exception.ActiveBloodDonorRequestException;
import backend.active_blood_receiver_request.database.ActiveBloodReceiverRequestDatabaseConstant;
import backend.active_blood_receiver_request.database.ActiveReceiverRequestQueryBuilderDAO;
import backend.active_blood_receiver_request.exception.ActiveReceiverRequestException;
import backend.active_blood_receiver_request.model.ActiveReceiverRequest;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ViewAllActiveReceiverRequestController} implements the
 * {@code ViewAllActiveReceiverRequestControllerDAO} to provide a concrete
 * implementation for viewing all active blood receiver requests.
 */
public class ViewAllActiveReceiverRequestController implements ViewAllActiveReceiverRequestControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Active blood receiver request query builder instance.
  private final ActiveReceiverRequestQueryBuilderDAO
      activeReceiverRequestQueryBuilderDAO;
  public String compatibleBloodGroup = null;

  /**
   * Constructs this {@code ViewAllActiveReceiverRequestController} instance.
   *
   * @param databaseConnectionDAO                database connection
   *                                             instance.
   * @param activeReceiverRequestQueryBuilderDAO active blood
   *                                             receiver request query
   *                                             builder instance.
   */
  public ViewAllActiveReceiverRequestController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final ActiveReceiverRequestQueryBuilderDAO activeReceiverRequestQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.activeReceiverRequestQueryBuilderDAO = activeReceiverRequestQueryBuilderDAO;
  }

  //Convert retrieved data into List
  private List<ActiveReceiverRequest> prepareActiveBloodReceiverRequestList(final ResultSet activeBloodReceiverRequestResultSet) throws SQLException {
    final List<ActiveReceiverRequest> activeBloodReceiverRequestList = new ArrayList<>();
    while (activeBloodReceiverRequestResultSet.next()) {
      final int userId = activeBloodReceiverRequestResultSet.getInt(ActiveBloodReceiverRequestDatabaseConstant.BLOOD_RECEIVER_USER_ID_COLUMN);
      final int requestId = activeBloodReceiverRequestResultSet.getInt(ActiveBloodReceiverRequestDatabaseConstant.BLOOD_RECEIVER_REQUEST_ID_COLUMN);
      final String firstName = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_FIRST_NAME_COLUMN);
      final String lastName = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_LAST_NAME_COLUMN);
      final String dateOfBirth = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_DATE_OF_BIRTH_COLUMN);
      final String bloodGroup = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_BLOOD_GROUP_COLUMN);
      final String email = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_EMAIL_COLUMN);
      final String contactNumber = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_CONTACT_NUMBER_COLUMN);
      final String addressFirstLine = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_ADDRESS_FIRST_LINE_COLUMN);
      final String addressStreet = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_ADDRESS_STREET_COLUMN);
      final String addressCity = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_ADDRESS_CITY_COLUMN);
      final String addressProvince = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_ADDRESS_PROVINCE_COLUMN);
      final String addressZipCode = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_ADDRESS_ZIP_CODE_COLUMN);
      final String addressCountry = activeBloodReceiverRequestResultSet.getString(ActiveBloodReceiverRequestDatabaseConstant.USER_ADDRESS_COUNTRY_COLUMN);
      final String status = ActiveBloodReceiverRequestDatabaseConstant.STATUS_ACTIVE;

      final int age = getAge(dateOfBirth);

      final ActiveReceiverRequest activeReceiverRequest = new ActiveReceiverRequest(userId, requestId, firstName, lastName, dateOfBirth, age, bloodGroup, email, contactNumber, addressFirstLine, addressStreet, addressCity, addressProvince, addressZipCode, addressCountry, status);
      activeBloodReceiverRequestList.add(activeReceiverRequest);
    }
    return activeBloodReceiverRequestList;
  }

  // get the age from date of birth
  private int getAge(String dateOfBirth) {
    final LocalDate currentDate = LocalDate.now();
    LocalDate dob = LocalDate.parse(dateOfBirth);
    Period period = Period.between(dob, currentDate);
    return Math.abs(period.getYears());
  }


  /**
   * @param bloodGroup of this user.
   *
   * @throws ActiveReceiverRequestException if an error occurs while viewing
   *                                        active blood receiver requests.
   */
  private void getCompatibleBloodGroup(String bloodGroup) throws ActiveReceiverRequestException {
    switch (bloodGroup) {
      case "A+":
        compatibleBloodGroup = "A+  AB+";
        break;
      case "A-":
        compatibleBloodGroup = "A+ A- AB+ AB-";
        break;
      case "B+":
        compatibleBloodGroup = "B+ AB+";
        break;
      case "B-":
        compatibleBloodGroup = "B- AB+ B- AB-";
        break;
      case "O+":
        compatibleBloodGroup = "AB+";
        break;
      case "O-":
        compatibleBloodGroup = "AB+ AB-";
        break;
      case "AB+":
        compatibleBloodGroup = "O+ A+ B+ AB+";
        break;
      case "AB-":
        compatibleBloodGroup = "O+ A+ B+ AB+ O- A- B- AB-";
        break;
      default:
        throw new ActiveReceiverRequestException("Invalid blood group passed. ");
    }
  }

  /**
   * @param allActiveBloodReceiverRequestList of all active blood receiver
   *                                          requests.
   * @param location                          of this user.
   *
   * @return List<ActiveReceiverRequest> of filtered active blood receiver
   * request.
   *
   * @throws ActiveReceiverRequestException if an error occurs while viewing
   *                                        active blood receiver requests.
   */
  private List<ActiveReceiverRequest> filterActiveReceiverRequest(
      final List<ActiveReceiverRequest> allActiveBloodReceiverRequestList, String location)
      throws ActiveReceiverRequestException {
    final int allActiveBloodRequestListSize = allActiveBloodReceiverRequestList.size();
    List<ActiveReceiverRequest> filteredActiveBloodReceiverRequestList = new ArrayList<>();
    for (int i = 0; i < allActiveBloodRequestListSize - 1; i++) {
      if (allActiveBloodReceiverRequestList.get(i).getAddressProvince().equals(location)) {
        filteredActiveBloodReceiverRequestList.add(allActiveBloodReceiverRequestList.get(i));
      }
    }

    final int filteredActiveBloodDReceiverRequestListSize = filteredActiveBloodReceiverRequestList.size();
    if (filteredActiveBloodDReceiverRequestListSize == 0) {
      throw new ActiveReceiverRequestException("No active blood receiver request found for the above location. ");
    }

    List<ActiveReceiverRequest> finalActiveBloodReceiverRequestList = new ArrayList<>();
    String[] compatibleBloodGroupArray = compatibleBloodGroup.split(" ");
    final int compatibleBloodGroupArraySize = compatibleBloodGroupArray.length;
    for (int i = 0; i < filteredActiveBloodDReceiverRequestListSize - 1; i++) {
      for (int j = 0; j < compatibleBloodGroupArraySize - 1; j++) {
        if (filteredActiveBloodReceiverRequestList.get(i).getBloodGroup().equals(compatibleBloodGroupArray[j])) {
          finalActiveBloodReceiverRequestList.add(filteredActiveBloodReceiverRequestList.get(i));
        }
      }
    }

    final int finalActiveBloodReceiverRequestListSize = finalActiveBloodReceiverRequestList.size();
    if (finalActiveBloodReceiverRequestListSize == 0) {
      throw new ActiveReceiverRequestException("No active blood receiver request found for the blood group compatible with the above blood group. ");
    }
    return finalActiveBloodReceiverRequestList;
  }

  /**
   * @param bloodGroup of this user.
   * @param location   of this user.
   *
   * @return List<ActiveReceiverRequest> of filtered active blood receiver
   * request.
   *
   * @throws ActiveReceiverRequestException if an error occurs while viewing
   *                                        active blood receiver requests.
   * @throws DatabaseConnectionException    if any error occurs while
   *                                        connecting to the database.
   */
  @Override
  public List<ActiveReceiverRequest> viewActiveReceiverRequest(String bloodGroup, String location)
      throws ActiveReceiverRequestException, DatabaseConnectionException {
    if (bloodGroup == null || bloodGroup.trim().isEmpty()) {
      throw new ActiveReceiverRequestException("Invalid blood group. ");
    } else {
      getCompatibleBloodGroup(bloodGroup);
    }

    if (location == null || location.trim().isEmpty()) {
      throw new ActiveReceiverRequestException("Invalid location. ");
    }

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet activeBloodReceiverRequestResultSet = statement.executeQuery(activeReceiverRequestQueryBuilderDAO.selectActiveBloodReceiverRequestQuery())) {

      final List<ActiveReceiverRequest> allActiveBloodReceiverRequestList = prepareActiveBloodReceiverRequestList(activeBloodReceiverRequestResultSet);
      return filterActiveReceiverRequest(allActiveBloodReceiverRequestList, location);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}