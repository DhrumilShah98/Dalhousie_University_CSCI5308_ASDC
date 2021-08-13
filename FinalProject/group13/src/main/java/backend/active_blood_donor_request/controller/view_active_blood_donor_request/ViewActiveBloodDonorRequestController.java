package backend.active_blood_donor_request.controller.view_active_blood_donor_request;

import backend.active_blood_donor_request.database.ActiveBloodDonorRequestDatabaseConstant;
import backend.active_blood_donor_request.database.view_active_blood_donor_request.ViewActiveBloodDonorRequestQueryBuilderDAO;
import backend.active_blood_donor_request.exception.ActiveBloodDonorRequestException;
import backend.active_blood_donor_request.model.ActiveBloodDonorRequest;
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
 * {@code ViewActiveBloodDonorRequestController} implements the
 * {@code ViewActiveBloodDonorRequestControllerDAO} to provide a concrete
 * implementation for viewing all active blood donation requests.
 */
public class ViewActiveBloodDonorRequestController
    implements ViewActiveBloodDonorRequestControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // View active blood donation request query builder instance.
  private final ViewActiveBloodDonorRequestQueryBuilderDAO
      viewActiveBloodDonorRequestQueryBuilderDAO;

  // String compatible blood type.
  public String compatibleBloodGroup = null;

  /**
   * Constructs this {@code ViewActiveBloodDonorRequestController} instance.
   *
   * @param databaseConnectionDAO                      database connection
   *                                                   instance.
   * @param viewActiveBloodDonorRequestQueryBuilderDAO view active blood
   *                                                   donation request query
   *                                                   builder instance.
   */
  public ViewActiveBloodDonorRequestController(
      final DatabaseConnectionDAO
          databaseConnectionDAO,
      final ViewActiveBloodDonorRequestQueryBuilderDAO
          viewActiveBloodDonorRequestQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.viewActiveBloodDonorRequestQueryBuilderDAO =
        viewActiveBloodDonorRequestQueryBuilderDAO;
  }

  /**
   * @param activeBloodDonorRequestResultSet active blood donation result set.
   *
   * @return List<ActiveBloodDonorRequest> of active blood donation requests.
   *
   * @throws SQLException if an error occurs while viewing active blood
   *                      donation requests.
   */
  private List<ActiveBloodDonorRequest> prepareActiveBloodDonorRequestList(
      final ResultSet activeBloodDonorRequestResultSet)
      throws SQLException {
    final List<ActiveBloodDonorRequest> activeBloodDonorRequestList =
        new ArrayList<>();
    while (activeBloodDonorRequestResultSet.next()) {
      final int userId =
          activeBloodDonorRequestResultSet.getInt(ActiveBloodDonorRequestDatabaseConstant.BLOOD_DONATION_USER_ID_COLUMN);
      final int requestId =
          activeBloodDonorRequestResultSet.getInt(ActiveBloodDonorRequestDatabaseConstant.BLOOD_DONATION_REQUEST_ID_COLUMN);
      final String firstName =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_FIRST_NAME_COLUMN);
      final String lastName =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_LAST_NAME_COLUMN);
      final String dateOfBirth =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_DATE_OF_BIRTH_COLUMN);
      final String bloodGroup =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_BLOOD_GROUP_COLUMN);
      final String email =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_EMAIL_COLUMN);
      final String contactNumber =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_CONTACT_NUMBER_COLUMN);
      final String addressFirstLine =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_ADDRESS_FIRST_LINE_COLUMN);
      final String addressStreet =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_ADDRESS_STREET_COLUMN);
      final String addressCity =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_ADDRESS_CITY_COLUMN);
      final String addressProvince =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_ADDRESS_PROVINCE_COLUMN);
      final String addressZipCode =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_ADDRESS_ZIP_CODE_COLUMN);
      final String addressCountry =
          activeBloodDonorRequestResultSet.getString(ActiveBloodDonorRequestDatabaseConstant.USER_ADDRESS_COUNTRY_COLUMN);
      final String status =
          ActiveBloodDonorRequestDatabaseConstant.STATUS_ACTIVE;
      final int age =
          getUserAge(dateOfBirth);
      final ActiveBloodDonorRequest activeBloodDonorRequest =
          new ActiveBloodDonorRequest(
              userId,
              requestId,
              firstName,
              lastName,
              dateOfBirth,
              age,
              bloodGroup,
              email,
              contactNumber,
              addressFirstLine,
              addressStreet,
              addressCity,
              addressProvince,
              addressZipCode,
              addressCountry,
              status);
      activeBloodDonorRequestList.add(activeBloodDonorRequest);
    }
    return activeBloodDonorRequestList;
  }

  /**
   * @param dateOfBirth of this user.
   *
   * @return int age of this user.
   */
  private int getUserAge(final String dateOfBirth) {
    final LocalDate currentDate = LocalDate.now();
    LocalDate dob = LocalDate.parse(dateOfBirth);
    Period period = Period.between(dob, currentDate);
    return Math.abs(period.getYears());
  }

  /**
   * @param bloodGroup of this user.
   *
   * @throws ActiveBloodDonorRequestException if an error occurs while viewing
   *                                          active blood donation requests.
   */
  private void getCompatibleBloodGroup(final String bloodGroup)
      throws ActiveBloodDonorRequestException {
    switch (bloodGroup) {
      case "A+":
        compatibleBloodGroup = "A+ A- O+ O-";
        break;
      case "A-":
        compatibleBloodGroup = "A- O-";
        break;
      case "B+":
        compatibleBloodGroup = "B+ B- O+ O-";
        break;
      case "B-":
        compatibleBloodGroup = "B- O-";
        break;
      case "O+":
        compatibleBloodGroup = "O+ O-";
        break;
      case "O-":
        compatibleBloodGroup = "O-";
        break;
      case "AB+":
        compatibleBloodGroup = "A+ A- B+ B- AB+ AB- O+ O-";
        break;
      case "AB-":
        compatibleBloodGroup = "AB- A- B- O-";
        break;
      default:
        throw new ActiveBloodDonorRequestException("Invalid blood group passed. ");
    }
  }

  /**
   * @param allActiveBloodDonorRequestList of all active blood donation
   *                                       requests.
   * @param location                       of this user.
   *
   * @return List<ActiveBloodDonorRequest> of filtered active blood donation
   * request.
   *
   * @throws ActiveBloodDonorRequestException if an error occurs while viewing
   *                                          active blood donation requests.
   */
  private List<ActiveBloodDonorRequest> filterActiveBloodDonorRequest(
      final List<ActiveBloodDonorRequest> allActiveBloodDonorRequestList,
      final String location)
      throws ActiveBloodDonorRequestException {
    final int allActiveBloodRequestListSize =
        allActiveBloodDonorRequestList.size();
    List<ActiveBloodDonorRequest> filteredActiveBloodDonorRequestList =
        new ArrayList<>();
    for (int i = 0; i < allActiveBloodRequestListSize - 1; i++) {
      if (allActiveBloodDonorRequestList.get(i).getAddressProvince().equalsIgnoreCase(location)) {
        filteredActiveBloodDonorRequestList.add(allActiveBloodDonorRequestList.get(i));
      }
    }

    final int filteredActiveBloodDonorRequestListSize =
        filteredActiveBloodDonorRequestList.size();
    if (filteredActiveBloodDonorRequestListSize == 0) {
      throw new ActiveBloodDonorRequestException("No active blood donor request found for the above location. ");
    }

    List<ActiveBloodDonorRequest> finalActiveBloodDonorRequestList =
        new ArrayList<>();
    String[] compatibleBloodGroupArray =
        compatibleBloodGroup.split(" ");
    final int compatibleBloodGroupArraySize =
        compatibleBloodGroupArray.length;
    for (int i = 0; i < filteredActiveBloodDonorRequestListSize - 1; i++) {
      for (int j = 0; j < compatibleBloodGroupArraySize - 1; j++) {
        if (filteredActiveBloodDonorRequestList.get(i).getBloodGroup().equalsIgnoreCase(compatibleBloodGroupArray[j])) {
          finalActiveBloodDonorRequestList.add(filteredActiveBloodDonorRequestList.get(i));
        }
      }
    }

    final int finalActiveBloodDonorRequestListSize =
        finalActiveBloodDonorRequestList.size();
    if (finalActiveBloodDonorRequestListSize == 0) {
      throw new ActiveBloodDonorRequestException("No active blood donor request found for the blood group compatible with the above blood group. ");
    }
    return finalActiveBloodDonorRequestList;
  }

  /**
   * @param bloodGroup of this user.
   * @param location   of this user.
   *
   * @return List<ActiveBloodDonorRequest> of filtered active blood donation
   * request.
   *
   * @throws ActiveBloodDonorRequestException if an error occurs while viewing
   *                                          active blood donation requests.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  @Override
  public List<ActiveBloodDonorRequest> viewActiveDonorRequest(
      final String bloodGroup,
      final String location)
      throws ActiveBloodDonorRequestException,
      DatabaseConnectionException {
    if (bloodGroup == null || bloodGroup.trim().isEmpty()) {
      throw new ActiveBloodDonorRequestException("Invalid blood group. ");
    } else {
      getCompatibleBloodGroup(bloodGroup);
    }

    if (location == null || location.trim().isEmpty()) {
      throw new ActiveBloodDonorRequestException("Invalid location. ");
    }

    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement();
         final ResultSet activeBloodDonorRequestResultSet =
             statement.executeQuery(viewActiveBloodDonorRequestQueryBuilderDAO.selectActiveBloodDonorRequestQuery())) {

      final List<ActiveBloodDonorRequest> allActiveBloodDonorRequestList =
          prepareActiveBloodDonorRequestList(activeBloodDonorRequestResultSet);
      return filterActiveBloodDonorRequest(allActiveBloodDonorRequestList, location);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
