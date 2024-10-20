package views;

import controller.Method;
import model.CrispyFlour;
import model.Material;
import model.Meat;

public class Main {
    public static void main(String[] args) {
        Material[] materials = new Material[10];
        materials[0] = new CrispyFlour("1",
                "Bột chiên gà giòn Miwon",
                "2024-06-22", 1000, 100);
        materials[1] = new CrispyFlour("2",
                "Bột chiên gà giòn Ottogi",
                "2024-06-12", 1500, 90);
        materials[2] = new CrispyFlour("3",
                "Bột tẩm khô chiên giòn CJ",
                "2024-06-02", 1200, 80);
        materials[3] = new CrispyFlour("4",
                "Bột chiên giòn CJ Foods",
                "2024-06-11", 1300, 70);
        materials[4] = new CrispyFlour("5",
                "Bột chiên gà rán Hàn Quốc Beksul",
                "2024-06-30", 1100, 60);
        materials[5] = new Meat("6", "Thịt lợn", "2024-10-17", 2000, 200);
        materials[6] = new Meat("7", "Thịt bò", "2024-10-17", 1800, 250);
        materials[7] = new Meat("8", "Thịt gà", "2024-10-17", 1600, 220);
        materials[8] = new Meat("9", "Thịt bê", "2024-10-17", 1900, 1800);
        materials[9] = new Meat("10", "Thịt cừu", "2024-10-17", 2200, 150);

        Method.mainMenu(materials);
    }
}
