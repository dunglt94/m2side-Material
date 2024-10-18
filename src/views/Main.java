package views;

import controller.Method;
import model.CrispyFlour;
import model.Material;
import model.Meat;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Material[] materials = new Material[10];
        materials[0] = new CrispyFlour("0001",
                "Bột chiên gà giòn Miwon",
                "2024-06-22", 1000, 100);
        materials[1] = new CrispyFlour("0002",
                "Bột chiên gà giòn Ottogi",
                "2024-06-12", 1500, 90);
        materials[2] = new CrispyFlour("0003",
                "Bột tẩm khô chiên giòn CJ CheilJedang Corporation",
                "2024-06-02", 1200, 80);
        materials[3] = new CrispyFlour("0004",
                "Bột chiên giòn CJ Foods",
                "2024-06-11", 1300, 70);
        materials[4] = new CrispyFlour("0005",
                "Bột chiên gà rán Hàn Quốc Beksul",
                "2024-06-30", 1100, 60);
        materials[5] = new Meat("0006", "Thịt lợn", "2024-10-17", 2000, 200);
        materials[6] = new Meat("0007", "Thịt bò", "2024-10-17", 1800, 250);
        materials[7] = new Meat("0008", "Thịt gà", "2024-10-17", 1600, 220);
        materials[8] = new Meat("0009", "Thịt bê", "2024-10-17", 1900, 1800);
        materials[9] = new Meat("0010", "Thịt cừu", "2024-10-17", 2200, 150);

        Method.mainMenu(materials);


    }
}
