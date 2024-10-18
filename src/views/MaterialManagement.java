package views;

import model.Material;

import java.util.Arrays;
import java.util.Scanner;

public class MaterialManagement {
//    public static void main(String[] args) {
//
//    }
//
//    public static void mainMenu () {
//        System.out.println("Manage");
//        System.out.println("1. Add Material");
//        System.out.println("2. Edit Material");
//        System.out.println("3. Delete Material");
//        System.out.println("4. Exit");
//    }
//
//    public static void choice () {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Choose: ");
//        int choice = scanner.nextInt();
//        switch (choice) {
//            case 1:
//
//                System.out.print("Enter name: ");
//
//        }
//    }

    public static void addMaterial(Material[] materials, Material newMaterial) {
        // Tạo một mảng mới có kích thước lớn hơn 1 so với mảng gốc
        Arrays.copyOf(materials, materials.length + 1);

        // Thêm phần tử mới vào vị trí cuối cùng của mảng mới
        materials[materials.length - 1] = newMaterial;

    }

    public static void removeMaterial(Material[] materials, int materialIndex) {
        for (int i = materialIndex; i < materials.length - 1; i++) {
            materials[i] = materials[i + 1];
        }
        Arrays.copyOf(materials, materials.length - 1);
    }
}
