package controller;

import model.CrispyFlour;
import model.Material;
import model.Meat;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Method {
    static Scanner scanner = new Scanner(System.in);
//  Các  menu
    public static void mainMenu(Material[] materialList) {
        System.out.println("Manage");
        System.out.println("1. Show material list");
        System.out.println("2. Calculate the total cost of all materials");
        System.out.println("3. Calculate the total discount of all materials");
        System.out.println("4. Material list sorted by cost ascending");
        System.out.println("5. Add Material");
        System.out.println("6. Edit Material");
        System.out.println("7. Remove Material");
        System.out.println("8. Exit");
        switchCaseForMainMenu(materialList);
    }

    public static void continueOrExit(Material[] materialList) {
        System.out.println("Continue?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        switch (choice()) {
            case 1:
                mainMenu(materialList);
                break;
            case 2:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                continueOrExit(materialList);
        }
    }

    public static void switchCaseForMainMenu(Material[] materialList) {
        switch (choice()) {
            case 1:
                printMaterialList(materialList);
                break;
            case 2:
                System.out.print("The total cost of all materials: ");
                calculateTotalAmount(materialList);
                break;
            case 3:
                System.out.print("Total discount amount: ");
                calculateTotalDiscount(materialList);
                break;
            case 4:
                sortByCost(materialList);
                break;
            case 5:
                materialList = addMaterial(materialList);

                break;
            case 6:
                System.out.println("Edit Material:");
                editMaterial(materialList);

                break;
            case 7:
                materialList = removeMaterial(materialList);
                break;
            case 8:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                mainMenu(materialList);
        }
        continueOrExit(materialList);
    }
//  Danh sách các chức năng
    public static int choice() {
        System.out.print("Choice: ");
        return scanner.nextInt();
    }

    public static void printMaterialList(Material[] materialList) {
        for (Material m : materialList) {
            System.out.println(m);
        }
    }

    public static void calculateTotalAmount(Material[] materialList) {
        double totalAmout = 0;
        for (Material material : materialList) {
            totalAmout += material.getAmount();
        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        String formattedSum = numberFormat.format(totalAmout);
        System.out.println(formattedSum);
    }

    public static void calculateTotalDiscount(Material[] materialList) {
        int totalDiscount = 0;
        for (Material material : materialList) {
            if (material instanceof CrispyFlour) {
                CrispyFlour crispyFlour = (CrispyFlour) material;
                totalDiscount += (int) (crispyFlour.getCost() * crispyFlour.getQuantity() - crispyFlour.getAmount());
            } else if (material instanceof Meat) {
                Meat meat = (Meat) material;
                totalDiscount += (int) (meat.getCost() * meat.getWeight() - meat.getAmount());
            }

        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        String formattedSum = numberFormat.format(totalDiscount);
        System.out.println(formattedSum);
    }

    public static void sortByCost(Material[] materialList) {
        for (int i = 0; i < materialList.length; i++) {
            int index = i;
            for (int j = i + 1; j < materialList.length; j++) {
                if (materialList[i].getCost() > materialList[j].getCost()) {
                    index = j;
                }
            }
            Material temp = materialList[index];
            materialList[index] = materialList[i];
            materialList[i] = temp;
        }
        System.out.println("Sorted by cost:");
        printMaterialList(materialList);
    }

    public static Material[] addMaterial(Material[] materialList) {
        Material[] newMaterialList = new Material[materialList.length + 1];
        chooseMaterialType(materialList, newMaterialList);
        return newMaterialList;
    }

    public static void chooseMaterialType(Material[] materialList, Material [] newMaterialList) {
        System.out.println("1. Crispy Flour");
        System.out.println("2. Meat");
        System.out.println("3. Back");
        Material newMaterial;
        switch (choice()) {
            case 1:
                newMaterial = new CrispyFlour();
                addNewMaterialToList(materialList, newMaterialList, newMaterial);
                break;
            case 2:
                newMaterial = new Meat();
                addNewMaterialToList(materialList, newMaterialList, newMaterial);
                break;
            case 3:
                mainMenu(materialList);
                break;
            default:
                System.out.println("Not a valid choice");
                chooseMaterialType(materialList, newMaterialList);
        }
    }

    public static void addNewMaterialToList(Material[] materialList,
                                            Material[] newMaterialList,
                                            Material newMaterial) {
        scanner.nextLine();
        inputMaterialInformation(newMaterial);
        for (int i = 0; i < materialList.length; i++) {
            newMaterialList[i] = materialList[i];
        }
        newMaterialList[materialList.length] = newMaterial;
    }

    public static void editMaterial(Material[] materialList) {
        scanner.nextLine();
        System.out.print("Enter id of the material want to edit: ");
        int materialIndex = -1;
        materialIndex = getMaterialIndex(materialList, materialIndex);
        System.out.println("Editing Material:");
        System.out.println(materialList[materialIndex]);
        inputMaterialInformation(materialList[materialIndex]);
    }

    private static void inputMaterialInformation(Material material) {
        System.out.print("Enter id: ");
        material.setId(scanner.nextLine());
        System.out.print("Enter name: ");
        material.setName(scanner.nextLine());
        System.out.print("Enter manufacturing date (YYYY-MM-DD): ");
        String date = (scanner.nextLine());
        material.setManufacturingDate(LocalDate.parse(date));
        System.out.print("Enter cost: ");
        material.setCost(Integer.parseInt(scanner.nextLine()));
        if (material instanceof CrispyFlour) {
            CrispyFlour crispyFlour = (CrispyFlour) material;
            System.out.print("Enter quantity: ");
            crispyFlour.setQuantity(Integer.parseInt(scanner.nextLine()));
        } else if (material instanceof Meat) {
            Meat meat = (Meat) material;
            System.out.print("Enter weight: ");
            meat.setWeight(Double.parseDouble(scanner.nextLine()));
        }
    }

    public static Material[] removeMaterial(Material[] materialList) {
        scanner.nextLine();
        int listSize = materialList.length;
        int materialIndex = -1;
        Material[] newMaterialsList = new Material[listSize - 1];
        System.out.print("Enter the id of the material want to remove: ");
        materialIndex = getMaterialIndex(materialList, materialIndex);
        System.out.println("Removed Material:");
        System.out.println(materialList[materialIndex]);
        for (int i = 0; i < newMaterialsList.length; i++) {
            newMaterialsList[i] = materialList[i];
            if (i >= materialIndex) newMaterialsList[i] = materialList[i + 1];
        }
        return newMaterialsList;
    }

    private static int getMaterialIndex(Material[] materialList, int materialIndex) {
        do {
            String materialId = (scanner.nextLine());
            for (int i = 0; i < materialList.length; i++) {
                if (materialList[i].getId().equals(materialId)) {
                    materialIndex = i;
                    break;
                }
            }
            if (materialIndex == -1) {
                System.out.println("Material with id " + materialId + " not found.");
                System.out.print("Please enter a valid id: ");
            }
        } while(materialIndex == -1);
        return materialIndex;
    }
}
