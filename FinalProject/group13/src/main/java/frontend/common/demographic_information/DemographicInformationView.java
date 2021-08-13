package frontend.common.demographic_information;

import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.demographic_information.controller.DemographicInformationController;
import backend.demographic_information.controller.DemographicInformationControllerDAO;
import backend.demographic_information.database.DemographicInformationQueryBuilder;
import backend.demographic_information.database.DemographicInformationQueryBuilderDAO;
import backend.demographic_information.exception.DemographicInformationException;
import frontend.print.BloodBookPrinter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class DemographicInformationView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO
      databaseConnection;
  private final DemographicInformationQueryBuilderDAO
      demographicInformationQueryBuilder;

  public DemographicInformationView(final BloodBookPrinter printer,
                                    final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.demographicInformationQueryBuilder =
        DemographicInformationQueryBuilder.getInstance();
  }

  public final void getDemographicInformation() {
    printer.printScreenTitle("Demographic Information");
    printer.printContent("\n");

    printer.printContent("Enter Province Name");
    final String provinceName = scanner.nextLine();

    try {
      final DemographicInformationControllerDAO demographicInformationController =
          new DemographicInformationController(databaseConnection,
              demographicInformationQueryBuilder);

      final HashMap<String, Integer> demographicMap =
          demographicInformationController.retrieveDemographicInformation(provinceName);

      for (Map.Entry<String, Integer> demographicEntry : demographicMap.entrySet()) {
        printer.printContent(demographicEntry.getKey() + ": " + demographicEntry.getValue());
      }
    } catch (DatabaseConnectionException | DemographicInformationException e) {
      printer.printContent(e.toString());
    }
  }
}
