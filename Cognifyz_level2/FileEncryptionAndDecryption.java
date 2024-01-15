package Cognifyz_level2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptionAndDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file name or path: ");
        String fileName = scanner.nextLine();

        System.out.print("Do you want to encrypt or decrypt the file? (e/d): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("e")) {
            encryptFile(fileName);
        } else if (choice.equalsIgnoreCase("d")) {
            decryptFile(fileName);
        } else {
            System.out.println("Invalid choice. Please enter 'e' for encryption or 'd' for decryption.");
        }

        scanner.close();
    }

    private static void encryptFile(String fileName) {
        processFile(fileName, "encrypted_" + fileName, 5, true);
        System.out.println("File encrypted successfully.");
    }

    private static void decryptFile(String fileName) {
        processFile(fileName, "decrypted_" + fileName, 5, false);
        System.out.println("File decrypted successfully.");
    }

    private static void processFile(String inputFileName, String outputFileName, int key, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            int data;
            while ((data = reader.read()) != -1) {
                char originalChar = (char) data;
                char processedChar;

                if (Character.isLetter(originalChar)) {
                    int shift = encrypt ? key : -key;
                    processedChar = shiftCharacter(originalChar, shift);
                } else {
                    processedChar = originalChar;
                }

                writer.write(processedChar);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static char shiftCharacter(char ch, int shift) {
        char base = Character.isUpperCase(ch) ? 'A' : 'a';
        return (char) (((ch - base + shift) % 26 + 26) % 26 + base);
    }
}
