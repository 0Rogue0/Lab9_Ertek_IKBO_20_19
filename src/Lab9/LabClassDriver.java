package Lab9;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import static java.lang.System.err;
import static java.lang.System.out;

public class LabClassDriver {
    LabClass labClass;
    String FILE = "Lab9_Ertek_IKBO_20_19/src/Lab9/list.txt";

    LabClassDriver(LabClass labClass) {
        this.labClass = labClass;
    }

    public void createLabClass() {
        out.print("Введите имя и оценку студента, чтобы добавить его ('exit' - выйти из цикла): ");
        String name;
        int grade;
        Scanner in = new Scanner(System.in);
        do {
            name = (in.next()).toLowerCase();
            if (!name.equals("exit")) {
                grade = in.nextInt();
                labClass.add(new Student(name, grade));
            }
        } while (!name.equals("exit"));
    }

    public void saveLabCLass() {

        try {
            FileWriter writer = new FileWriter(FILE);

            while (!labClass.isEmpty()) {
                writer.write(labClass.remove().toString() + "\n");
            }
            writer.flush();
        } catch (Exception e) {
            err.println("Такого файла нет!");
        }

    }

    public void fillLabClass() {
        try {
            FileReader reader = new FileReader(FILE);
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                String string = in.nextLine();
                int FI = string.indexOf("Имя='") + 6;
                int LI = string.lastIndexOf('\'');
                String name = string.substring(FI, LI);

                FI = string.indexOf("Оценка=") + 6;
                LI = string.lastIndexOf('}');
                int grade = Integer.parseInt(string.substring(FI, LI));

                labClass.add(new Student(name, grade));
            }
        } catch (FileNotFoundException e) {
            err.println("Такого файла нет!");
        }

    }
}
