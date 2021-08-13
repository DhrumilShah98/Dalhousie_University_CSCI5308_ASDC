package frontend.user.features.vaccination;

import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.vaccination.controller.VaccinationController;
import backend.vaccination.controller.VaccinationControllerDAO;
import backend.vaccination.database.VaccinationQueryBuilder;
import backend.vaccination.database.VaccinationQueryBuilderDAO;
import backend.vaccination.exception.VaccineException;
import backend.vaccination.model.VaccineModel;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.util.Scanner;

public final class VaccinationView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO
      databaseConnection;
  private final VaccinationQueryBuilderDAO
      vaccinationQueryBuilder;

  public VaccinationView(final BloodBookPrinter printer,
                         final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.vaccinationQueryBuilder =
        VaccinationQueryBuilder.getInstance();
  }

  public final void scheduleVaccination() {
    printer.printScreenTitle("Vaccination");
    printer.printContent("\n");

    final VaccinationControllerDAO vaccinationController =
        new VaccinationController(databaseConnection, vaccinationQueryBuilder);

    try {
      final VaccineModel vaccineModel =
          vaccinationController.scheduleVaccination(Session.USER);

      if (vaccineModel.isRegisteredFirstTime()) {
        printer.printContent("You have been registered successfully.");
        printer.printContent("Vaccine Type: " + vaccineModel.getVaccineType());
        printer.printContent("Registration Date: " + vaccineModel.getRegistrationDate());
        printer.printContent("First Dose Date Scheduled: " + vaccineModel.getFirstDoseDate());
        printer.printContent("Second Dose Date Scheduled: " + vaccineModel.getSecondDoseDate());
        printer.printContent("\n");
      } else {
        printer.printContent("You have already registered for vaccination.");
        printer.printContent("Vaccine Type: " + vaccineModel.getVaccineType());
        printer.printContent("Registration Date: " + vaccineModel.getRegistrationDate());
        printer.printContent("First Dose Date Scheduled: " + vaccineModel.getFirstDoseDate());
        printer.printContent("Second Dose Date Scheduled: " + vaccineModel.getSecondDoseDate());
        printer.printContent("\n");
      }
    } catch (VaccineException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }
}