import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(15, 120, 3, 105.4);
        GameProgress game2 = new GameProgress(28, 300, 5, 506.45);
        GameProgress game3 = new GameProgress(74, 650, 8, 656.11);
        saveGame("D://Games//savegames//game1.dat", game1);
        saveGame("D://Games//savegames//game2.dat", game2);
        saveGame("D://Games//savegames//game3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("D://Games//savegames//game1.dat");
        arrayList.add("D://Games//savegames//game2.dat");
        arrayList.add("D://Games//savegames//game3.dat");
        zipFiles("D://Games//savegames//zip.zip", arrayList);
        File game1Dat = new File("D://Games//savegames//game1.dat");
        File game2Dat = new File("D://Games//savegames//game2.dat");
        File game3Dat = new File("D://Games//savegames//game3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удалён");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удалён");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удалён");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
