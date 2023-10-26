package students;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Person[] person = new Person[1000];
        ClassroomDataGroups personGroups = new ClassroomDataGroups();
        PersonAgeDataGroups personAge = new PersonAgeDataGroups();
        PersonNameDataGroup personName = new PersonNameDataGroup();

        String csvFile = "students - students.csv"; // путь к файлу csv
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            int iter = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                while (iter >= person.length){
                    person = arrayExtension(person);
                }
                person[iter] = new Person(data);
                personGroups.addPerson(person[iter]);
                personAge.addPerson(person[iter]);
                personName.addPerson(person[iter]);
                iter++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // <<  Вычисление средней оценки в старших классах (10 и 11)  >>
        // Производится с использованием класса, который сортирует по группам.
        // Т.к. достаточно обратиться по ключу (номер группы), чтобы получить список учеников
        Person[] class10 = personGroups.getPersons(10);
        Person[] class11 = personGroups.getPersons(11);

        double summScore = 0; //сумма оценок по всем предметам
        for (Person p : class10){
            if (p != null) //есть пустые записи, т.к. массив статичен и расширяется в 1.5 раза при заполнении
                summScore += (p.geometry + p.physics + p.mathematics + p.informatics + p.literature + p.rus);
        }
        System.out.println("Средняя оценка в 10 классе по всем предметам: " + summScore / (6 * getLength(class10)));

        summScore = 0;
        for (Person p : class11){
            if (p != null) //есть пустые записи, т.к. массив статичен и расширяется в 1.5 раза при заполнении
                summScore += (p.geometry + p.physics + p.mathematics + p.informatics + p.literature + p.rus);
        }
        System.out.println("Средняя оценка в 11 классе по всем предметам: " + summScore / (6 * getLength(class11)));


        //Поиск всех отличников, старше 14 лет
        // Производится с использованием класса, который сортирует по возрасту.
        // Т.к. достаточно обратиться по ключу (возраст), чтобы получить список учеников согласно требуемому возрасту
        Person[] students14YO = personAge.getPersons(14);
        Person[] students15YO = personAge.getPersons(15);
        Person[] students16YO = personAge.getPersons(16);
        Person[] students17YO = personAge.getPersons(17);

        System.out.println("Отличники старше 14 лет: ");
        for(Person p : students14YO){
            if (p != null && p.geometry + p.physics + p.mathematics + p.informatics + p.literature + p.rus == 30)
                System.out.println("Возраст: " + p.age + ". ФИ: " + p.secondName + " " + p.firstName);
        }
        for(Person p : students15YO){
            if (p != null && p.geometry + p.physics + p.mathematics + p.informatics + p.literature + p.rus == 30)
                System.out.println("Возраст: " + p.age + ". ФИ: " + p.secondName + " " + p.firstName);
        }
        for(Person p : students16YO){
            if (p != null && p.geometry + p.physics + p.mathematics + p.informatics + p.literature + p.rus == 30)
                System.out.println("Возраст: " + p.age + ". ФИ: " + p.secondName + " " + p.firstName);
        }
        for(Person p : students17YO){
            if (p != null && p.geometry + p.physics + p.mathematics + p.informatics + p.literature + p.rus == 30)
                System.out.println("Возраст: " + p.age + ". ФИ: " + p.secondName + " "  + p.firstName);
        }


        // Поиск ученика по фамили (фамилия ученика задается через консоль)
        // Производится с использованием третьего класса, который сортирует учеников по первой букве фамилии
        // Т.к. сокращается поиск по ученикам, у которых фамилия начинается на ту же букву
        Scanner in = new Scanner(System.in);
        System.out.println("Введите фамилию ученика: ");
        String checkSecondName = in.nextLine();
        in.close();


        Person[] studentCheck = personName.getPersons(checkSecondName);
        System.out.println("Ученики с фамилией: " + checkSecondName);
        for(Person p : studentCheck){
            if (p != null && p.secondName.equals(checkSecondName))
                System.out.println(p.secondName + " " + p.firstName);
        }
    }



    //длина массива (считает не null строки)
    public static int getLength(Person[] persons){
        int count = 0;
        for(Person p : persons)
            if (p != null)
                ++count;
        return count;
    }

    public static Person[] arrayExtension(Person[] person){
        int newLength = (int)(person.length * 1.5);
        Person[] newPerson = new Person[newLength];
        System.arraycopy(person, 0, newPerson, 0, person.length);
        return newPerson;
    }
}