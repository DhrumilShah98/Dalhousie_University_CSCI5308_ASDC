package frontend.blood_bank.features.blood_stock_management;

import backend.blood_stock_management.controller.blood_stock_quantity.ManageBloodStockQuantityController;
import backend.blood_stock_management.controller.blood_stock_quantity.ManageBloodStockQuantityControllerDAO;
import backend.blood_stock_management.controller.blood_stock_threshold.ManageBloodStockThresholdController;
import backend.blood_stock_management.controller.blood_stock_threshold.ManageBloodStockThresholdControllerDAO;
import backend.blood_stock_management.controller.blood_stock_unit_price.ManageBloodStockUnitPriceController;
import backend.blood_stock_management.controller.blood_stock_unit_price.ManageBloodStockUnitPriceControllerDAO;
import backend.blood_stock_management.controller.view_blood_stock.ViewBloodStockController;
import backend.blood_stock_management.controller.view_blood_stock.ViewBloodStockControllerDAO;
import backend.blood_stock_management.database.blood_stock_quantity.ManageBloodStockQuantityQueryBuilder;
import backend.blood_stock_management.database.blood_stock_quantity.ManageBloodStockQuantityQueryBuilderDAO;
import backend.blood_stock_management.database.blood_stock_threshold.ManageBloodStockThresholdQueryBuilder;
import backend.blood_stock_management.database.blood_stock_threshold.ManageBloodStockThresholdQueryBuilderDAO;
import backend.blood_stock_management.database.blood_stock_unit_price.ManageBloodStockUnitPriceQueryBuilder;
import backend.blood_stock_management.database.blood_stock_unit_price.ManageBloodStockUnitPriceQueryBuilderDAO;
import backend.blood_stock_management.database.view_blood_stock.ViewBloodStockQueryBuilder;
import backend.blood_stock_management.database.view_blood_stock.ViewBloodStockQueryBuilderDAO;
import backend.blood_stock_management.exception.BloodStockException;
import backend.blood_stock_management.model.BloodStock;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.util.List;
import java.util.Scanner;

public final class BloodStockManagementView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final ViewBloodStockQueryBuilderDAO viewBloodStockQueryBuilder;
  private final ManageBloodStockQuantityQueryBuilderDAO
      manageBloodStockQuantityQueryBuilder;
  private final ManageBloodStockThresholdQueryBuilderDAO
      manageBloodStockThresholdQueryBuilder;
  private final ManageBloodStockUnitPriceQueryBuilderDAO
      manageBloodStockUnitPriceQueryBuilder;

  public BloodStockManagementView(final BloodBookPrinter printer,
                                  final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.viewBloodStockQueryBuilder =
        ViewBloodStockQueryBuilder.getInstance();
    this.manageBloodStockQuantityQueryBuilder =
        ManageBloodStockQuantityQueryBuilder.getInstance();
    this.manageBloodStockThresholdQueryBuilder =
        ManageBloodStockThresholdQueryBuilder.getInstance();
    this.manageBloodStockUnitPriceQueryBuilder =
        ManageBloodStockUnitPriceQueryBuilder.getInstance();
  }

  private void viewBloodStock() {
    final ViewBloodStockControllerDAO bloodStockControllerDAO =
        new ViewBloodStockController(databaseConnection, viewBloodStockQueryBuilder);
    try {
      final List<BloodStock> bloodStockList =
          bloodStockControllerDAO.viewBloodStock(Session.BLOOD_BANK.getBloodBankId());
      printer.printScreenTitle("Blood Stock Management\nView All Stock");
      printer.printContent("Name: " + Session.BLOOD_BANK.getName());
      printer.printContent("\n");
      printer.printBeautifyContent("%-15s%-22s%-10s%-10s%n",
          "Blood Group",
          "Units Available(Bags)",
          "Threshold",
          "Unit Price");
      for (final BloodStock bloodStock : bloodStockList) {
        printer.printBeautifyContent("%-15s%-22s%-10s%-10s%n",
            bloodStock.getBloodGroup(),
            bloodStock.getQuantity(),
            bloodStock.getThreshold(),
            bloodStock.getUnitPrice());
      }
    } catch (BloodStockException | DatabaseConnectionException e) {
      System.out.println(e.getMessage());
    }
  }

  private void manageBloodStockUnits() {
    printer.printContent("========== Units ==========");
    printer.printContent("1. Increment blood stock units.");
    printer.printContent("2. Decrement blood stock units.");
    printer.printContent("3. Overwrite blood stock units.");
    printer.printContent("4. Exit.");
    printer.printContent("Select an option:");
    final String inputUnitsOption = scanner.nextLine();
    switch (inputUnitsOption) {
      case "1":
        printer.printContent("Enter your increment string: (A+ 100)");
        final String incrementString = scanner.nextLine();
        try {
          final ManageBloodStockQuantityControllerDAO
              manageBloodStockQuantityController =
              new ManageBloodStockQuantityController(databaseConnection,
                  manageBloodStockQuantityQueryBuilder);
          final int updatedQuantity =
              manageBloodStockQuantityController
                  .incrementBloodStockQuantity(incrementString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock quantity: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.toString());
        }
        break;
      case "2":
        printer.printContent("Enter your decrement string: (A+ 100)");
        final String decrementString = scanner.nextLine();
        try {
          final ManageBloodStockQuantityControllerDAO
              manageBloodStockQuantityController =
              new ManageBloodStockQuantityController(databaseConnection,
                  manageBloodStockQuantityQueryBuilder);
          final int updatedQuantity =
              manageBloodStockQuantityController
                  .decrementBloodStockQuantity(decrementString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock quantity: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.toString());
        }
        break;
      case "3":
        printer.printContent("Enter your overwrite string: (A+ 100)");
        final String overwriteString = scanner.nextLine();
        try {
          final ManageBloodStockQuantityControllerDAO
              manageBloodStockQuantityController =
              new ManageBloodStockQuantityController(databaseConnection,
                  manageBloodStockQuantityQueryBuilder);
          final int updatedQuantity =
              manageBloodStockQuantityController
                  .overwriteBloodStockQuantity(overwriteString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock quantity: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.getMessage());
        }
        break;
    }
  }

  private void manageBloodStockThreshold() {
    printer.printContent("========== Threshold ==========");
    printer.printContent("1. Increment blood stock threshold.");
    printer.printContent("2. Decrement blood stock threshold.");
    printer.printContent("3. Overwrite blood stock threshold.");
    printer.printContent("4. Exit.");
    printer.printContent("Select an option:");
    final String inputUnitsOption = scanner.nextLine();
    switch (inputUnitsOption) {
      case "1":
        printer.printContent("Enter your increment string: (A+ 100)");
        final String incrementString = scanner.nextLine();
        try {
          final ManageBloodStockThresholdControllerDAO
              manageBloodStockThresholdController =
              new ManageBloodStockThresholdController(databaseConnection,
                  manageBloodStockThresholdQueryBuilder);
          final int updatedQuantity =
              manageBloodStockThresholdController
                  .incrementBloodStockThreshold(incrementString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock threshold: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.getMessage());
        }
        break;
      case "2":
        printer.printContent("Enter your decrement string: (A+ 100)");
        final String decrementUnitsString = scanner.nextLine();
        try {
          final ManageBloodStockThresholdControllerDAO
              manageBloodStockThresholdController =
              new ManageBloodStockThresholdController(databaseConnection,
                  manageBloodStockThresholdQueryBuilder);
          final int updatedQuantity =
              manageBloodStockThresholdController
                  .decrementBloodStockThreshold(decrementUnitsString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock quantity: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.getMessage());
        }
        break;
      case "3":
        printer.printContent("Enter your overwrite string: (A+ 100)");
        final String overwriteUnitsString = scanner.nextLine();
        try {
          final ManageBloodStockThresholdControllerDAO
              manageBloodStockThresholdController =
              new ManageBloodStockThresholdController(databaseConnection,
                  manageBloodStockThresholdQueryBuilder);
          final int updatedQuantity =
              manageBloodStockThresholdController
                  .overwriteBloodStockThreshold(overwriteUnitsString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock quantity: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.getMessage());
        }
        break;
    }
  }

  private void manageBloodStockUnitPrice() {
    printer.printContent("========== Unit Price ==========");
    printer.printContent("1. Increment blood stock unit price.");
    printer.printContent("2. Decrement blood stock unit price.");
    printer.printContent("3. Overwrite blood stock unit price.");
    printer.printContent("4. Exit.");
    printer.printContent("Select an option:");
    final String inputUnitsOption = scanner.nextLine();
    switch (inputUnitsOption) {
      case "1":
        printer.printContent("Enter your increment string: (A+ 100)");
        final String incrementString = scanner.nextLine();
        try {
          final ManageBloodStockUnitPriceControllerDAO
              manageBloodStockUnitPriceController =
              new ManageBloodStockUnitPriceController(databaseConnection,
                  manageBloodStockUnitPriceQueryBuilder);
          final double updatedQuantity =
              manageBloodStockUnitPriceController
                  .incrementBloodStockUnitPrice(incrementString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock unit price: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.getMessage());
        }
        break;
      case "2":
        printer.printContent("Enter your decrement string: (A+ 100)");
        final String decrementString = scanner.nextLine();
        try {
          final ManageBloodStockUnitPriceControllerDAO
              manageBloodStockUnitPriceController =
              new ManageBloodStockUnitPriceController(databaseConnection,
                  manageBloodStockUnitPriceQueryBuilder);
          final double updatedQuantity =
              manageBloodStockUnitPriceController
                  .decrementBloodStockUnitPrice(decrementString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock unit price: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.getMessage());
        }
        break;
      case "3":
        printer.printContent("Enter your overwrite string: (A+ 100)");
        final String overwriteString = scanner.nextLine();
        try {
          final ManageBloodStockUnitPriceControllerDAO
              manageBloodStockUnitPriceController =
              new ManageBloodStockUnitPriceController(databaseConnection,
                  manageBloodStockUnitPriceQueryBuilder);
          final double updatedQuantity =
              manageBloodStockUnitPriceController
                  .overwriteBloodStockUnitPrice(overwriteString,
                      Session.BLOOD_BANK.getBloodBankId());
          printer.printContent("New blood stock unit price: " + updatedQuantity);
        } catch (BloodStockException | DatabaseConnectionException e) {
          System.out.println(e.getMessage());
        }
        break;
    }
  }

  private void manageStock() {
    printer.printScreenTitle("Blood Stock Management\nManage Stock");
    while (true) {
      printer.printContent("What you want to update?");
      printer.printContent("1. Units.");
      printer.printContent("2. Threshold.");
      printer.printContent("3. Unit Price.");
      printer.printContent("4. Exit.");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          manageBloodStockUnits();
          break;
        case "2":
          manageBloodStockThreshold();
          break;
        case "3":
          manageBloodStockUnitPrice();
          break;
        case "4":
          return;
        default:
          break;
      }
    }
  }

  public final void manageBloodStock() {
    while (true) {
      printer.printContent("1. View all blood stock");
      printer.printContent("2. Manage stock");
      printer.printContent("3. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          viewBloodStock();
          break;
        case "2":
          manageStock();
          break;
        case "3":
          return;
        default:
          break;
      }
    }
  }
}