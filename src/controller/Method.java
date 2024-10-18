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
//    Main
    public static void printMaterialList(Material[] material) {
        for (Material m : material) {
            System.out.println(m);
            System.out.println();
        }
    }

    public static void calTotalMoney(Material[] material) {
        double sum = 0;
        for (Material m : material) {
            sum += m.getAmount();
        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        String formattedSum = numberFormat.format(sum);
        System.out.println(formattedSum);
    }

    public static void sortByCost(Material[] material) {
        for (int i = 0; i < material.length; i++) {
            int index = i;
            for (int j = i + 1; j < material.length; j++) {
                if (material[i].getCost() > material[j].getCost()) {
                    index = j;
                }
            }
            Material temp = material[index];
            material[index] = material[i];
            material[i] = temp;
            System.out.println(material[i].toString());
            System.out.println();
        }
    }

//    Management
    public static void mainMenu(Material[] material) {
        System.out.println("Manage");
        System.out.println("1. Calculate the total cost of all 10 materials");
        System.out.println("2. Sort by cost ascending");
        System.out.println("3. Add Material");
        System.out.println("4. Edit Material");
        System.out.println("5. Remove Material");
        System.out.println("6. Exit");
        switchCase(material);
    }

    public static void continueOrExit(Material[] material) {
        System.out.println("Continue?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                mainMenu(material);
                break;
            case 2:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                continueOrExit(material);
        }
    }

    public static int choice() {
        System.out.print("Choice: ");
        return scanner.nextInt();
    }

    public static void switchCase(Material[] material) {
        switch (Method.choice()) {
            case 1:
                System.out.print("The total cost of all 10 materialsl: ");
                Method.calTotalMoney(material);
                continueOrExit(material);
                break;
            case 2:
                System.out.println("Pre-sorted:");
                Method.printMaterialList(material);

                System.out.println("Sorted by cost:");
                Method.sortByCost(material);
                continueOrExit(material);
                break;
            case 3:
                Method.addMaterial(material);
                continueOrExit(material);
                break;
            case 4:
                System.out.println("Edit Material:");
                System.out.print("Enter id of the material: ");
                int index = scanner.nextInt();
                Method.editMaterial(material, index);
                continueOrExit(material);
                break;
            case 5:
                Method.printMaterialList(Method.removeMaterial(material));
                continueOrExit(material);
                break;
            case 6:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                mainMenu(material);
        }
    }

    public static void addMaterial(Material[] materials) {
        System.out.println("Add Material:");
        System.out.println("1. Crispy Flour");
        System.out.println("2. Meat");
        System.out.println("3. Exit");
        Material newMaterial;
        switch (choice()) {
            case 1:
                newMaterial = new CrispyFlour();
                printMaterialList(newMats(materials, newMaterial));
                break;
            case 2:
                newMaterial = new Meat();
                printMaterialList(newMats(materials, newMaterial));
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                addMaterial(materials);
        }
    }

    public static Material[] newMats(Material[] materials, Material newMaterial) {
        System.out.print("Enter id: ");
        newMaterial.setId(scanner.next());
        System.out.print("Enter name: ");
        newMaterial.setName(scanner.next());
        scanner.nextLine();
        System.out.print("Nhập khoảng cách số ngày từ ngày sản xuất đến ngày hiện tại: ");
        int days = scanner.nextInt();
        newMaterial.setManufacturingDate(LocalDate.now().minusDays(days));
        System.out.print("Enter cost: ");
        newMaterial.setCost(Integer.parseInt(scanner.next()));
        if (newMaterial instanceof CrispyFlour) {
            CrispyFlour crispyFlour = (CrispyFlour) newMaterial;
            System.out.print("Enter quantity: ");
            crispyFlour.setQuantity(Integer.parseInt(scanner.next()));
        } else if (newMaterial instanceof Meat) {
            Meat meat = (Meat) newMaterial;
            System.out.print("Enter weight: ");
            meat.setWeight(Double.parseDouble(scanner.next()));
        }

        Material[] newMaterials = new Material[materials.length + 1];
        for (int i = 0; i < materials.length; i++) {
            newMaterials[i] = materials[i];
            newMaterials[materials.length] = newMaterial;
        }
        return newMaterials;
    }

    public static void editMaterial(Material[] materials, int index) {
        index--;
        System.out.println("Editing Material:");
        System.out.println(materials[index]);
        System.out.print("Enter id: ");
        materials[index].setId(scanner.next());
        System.out.print("Enter name: ");
        materials[index].setName(scanner.next());
        scanner.nextLine();
        System.out.print("Nhập khoảng cách số ngày từ ngày sản xuất đến ngày hiện tại: ");
        int days = scanner.nextInt();
        scanner.nextLine();
        materials[index].setManufacturingDate(LocalDate.now().minusDays(days));
        System.out.print("Enter cost: ");
        materials[index].setCost(Integer.parseInt(scanner.next()));
        if (materials[index] instanceof CrispyFlour) {
            CrispyFlour crispyFlour = (CrispyFlour) materials[index];
            System.out.print("Enter quantity: ");
            crispyFlour.setQuantity(Integer.parseInt(scanner.next()));
        } else if (materials[index] instanceof Meat) {
            Meat meat = (Meat) materials[index];
            System.out.print("Enter weight: ");
            meat.setWeight(Double.parseDouble(scanner.next()));
        }
        Method.printMaterialList(materials);
    }

    public static Material[] removeMaterial(Material[] materials) {
        System.out.print("Enter id of material: ");
        int id = scanner.nextInt();
        System.out.println("Remove Material:");
        System.out.println(materials[id -1]);
        System.out.println();
        System.out.println("After removed:");
        Material[] newMaterials = new Material[materials.length - 1];
        for (int i = 0; i < materials.length; i++) {
            if (i == id - 1) continue;
            newMaterials[i] = materials[i];
        }
        return newMaterials;
    }
}
