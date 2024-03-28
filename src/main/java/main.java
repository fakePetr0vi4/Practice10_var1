import java.io.*;
import java.util.Scanner;

public class main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Работа выполнена. Максимов Никита РИБО_05_22");
        System.out.println("Введите количество файлов для склейки:");
        int fileCount = scanner.nextInt();
        scanner.nextLine();

        File[] files = new File[fileCount];
        byte[][] fileContents = new byte[fileCount][];
        int totalLength = 0;

        for (int i = 0; i < fileCount; i++) {
            boolean fileReadSuccessfully = false;
            do {
                System.out.println("Введите путь к файлу " + (i + 1) + ":");
                String filePath = scanner.nextLine();
                files[i] = new File(filePath);

                try {
                    FileInputStream readfile = new FileInputStream(files[i]);
                    fileContents[i] = readfile.readAllBytes();
                    readfile.close();
                    totalLength += fileContents[i].length;
                    fileReadSuccessfully = true;
                } catch (IOException e) {
                    System.out.println("Ошибка при чтении файла, введите путь к файлу заново.");
                }
            } while (!fileReadSuccessfully);
        }

        byte[] concatenatedContent = new byte[totalLength];
        int currentIndex = 0;

        for (byte[] content : fileContents) {
            System.arraycopy(content, 0, concatenatedContent, currentIndex, content.length);
            currentIndex += content.length;
        }

        String outputPath = files[0].getParent() + File.separator + "concatenated_file";
        try {
            FileOutputStream outFile = new FileOutputStream(outputPath);
            outFile.write(concatenatedContent);
            outFile.close();
            System.out.println("Файл склеен и сохранен по пути: " + outputPath);
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи результата в файл");
        }
    }
}

