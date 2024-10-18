package views;

import controller.MaterialManagement;
import model.CrispyFlour;
import model.Material;
import model.Meat;
import controller.Method;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Material[] materials = new Material[10];
        materials[0] = new CrispyFlour("0001",
                "Bột chiên gà giòn Miwon",
                LocalDate.now(), 1000, 100);
        materials[1] = new CrispyFlour("0002",
                "Bột chiên gà giòn Ottogi",
                LocalDate.now(), 1500, 90);
        materials[2] = new CrispyFlour("0003",
                "Bột tẩm khô chiên giòn CJ CheilJedang Corporation",
                LocalDate.now(), 1200, 80);
        materials[3] = new CrispyFlour("0004",
                "Bột chiên giòn CJ Foods",
                LocalDate.now(), 1300, 70);
        materials[4] = new CrispyFlour("0005",
                "Bột chiên gà rán Hàn Quốc Beksul",
                LocalDate.now(), 1100, 60);
        materials[5] = new Meat("0006", "Thịt lợn", LocalDate.now(), 2000, 200);
        materials[6] = new Meat("0007", "Thịt bò", LocalDate.now(), 1800, 250);
        materials[7] = new Meat("0008", "Thịt gà", LocalDate.now(), 1600, 220);
        materials[8] = new Meat("0009", "Thịt bê", LocalDate.now(), 1900, 1800);
        materials[9] = new Meat("0010", "Thịt cừu", LocalDate.now(), 2200, 150);

        MaterialManagement.mainMenu();
        switch (MaterialManagement.choice()) {
            case 1:
                System.out.println("The total cost of all 10 materialsl: " + Method.calTotalMoney(materials));
                break;
            case 2:
                System.out.println("Pre-sorted:");
                Method.printMaterialList(materials);

                System.out.println("Sorted by cost:");
                Method.sortByCost(materials);
                break;
            case 3:
                MaterialManagement.addMaterial(materials);
                break;
            case 4:
                System.out.println("Edit Material:");
                System.out.print("Enter id of the material: ");
                int index = scanner.nextInt();
                MaterialManagement.editMaterial(materials, index);
                break;
            case 5:
                Method.printMaterialList(MaterialManagement.removeMaterial(materials));
                break;
            case 6:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
        }

    }
}
