package backend.demographic_information.controller;

import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import database.DatabaseConnectionException;
import backend.demographic_information.exception.DemographicInformationException;

import java.util.HashMap;

/**
 * {@code DemographicInformationControllerDAO} provides a contract for the
 * fetching information of active users and bloodbanks from a province.
 */
public interface DemographicInformationControllerDAO {

  /**
   * Performs fetching information of active users and bloodbanks from a
   * province.
   *
   * @param provinceName province name for fetching information.
   *
   * @return hashmap instance with value of active users and bloodbanks.
   *
   * @throws DemographicInformationException if any error occurs while
   *                                         fetching information.
   * @throws DatabaseConnectionException     if any error occurs while
   *                                         connecting to the database.
   */
  HashMap<String, Integer> retrieveDemographicInformation(String provinceName)
      throws DemographicInformationException, DatabaseConnectionException;
}
