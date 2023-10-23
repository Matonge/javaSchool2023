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

class Person{
    String secondName;
    String firstName;
    int age, group, physics, mathematics, rus, literature, geometry, informatics;

    Person(String secondName, String firstName, int age, int group, int physics, int mathematics, int rus,
           int literature, int geometry, int informatics){
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.group = group;
        this.physics = physics;
        this.mathematics = mathematics;
        this.rus = rus;
        this.literature = literature;
        this.geometry = geometry;
        this.informatics = informatics;
    }

    Person(String[] data){
        this.secondName = data[0];
        this.firstName = data[1];
        this.age = Integer.parseInt(data[2]);
        this.group = Integer.parseInt(data[3]);
        this.physics = Integer.parseInt(data[4]);
        this.mathematics = Integer.parseInt(data[5]);
        this.rus = Integer.parseInt(data[6]);
        this.literature = Integer.parseInt(data[7]);
        this.geometry = Integer.parseInt(data[8]);
        this.informatics = Integer.parseInt(data[9]);
    }
}


class ClassroomDataGroups {
    // внутренние поля для хранения данных
    MyHashMap personMap = new MyHashMap<>();
    // алгоритм добавления студента в нужную группу согласно критерию
    public void addPerson(Person person) {
        //personMap.put(person.group, person.firstName + " " + person.secondName);
        personMap.newPut(person.group, person);
    }

    // возвращает студентов конкретной группы
    public Person[] getPersons(int groupNum) {
        //Вывод результатов группировки по группе
        //System.out.println("Фамилия и имя, группировка по группе " + groupNum +":");
        return personMap.getAllValues(groupNum);

    }
}

class PersonAgeDataGroups {
    // внутренние поля для хранения данных
    MyHashMap personMap = new MyHashMap<>();
    // алгоритм добавления студента в нужную группу согласно критерию
    public void addPerson(Person person) {
        personMap.newPut(person.age, person);
    }
    // возвращает студентов указанного адреса
    public Person[] getPersons(int age) {
        //System.out.println("Фамилия и имя, группировка по возрасту " + groupNum +":");
        return personMap.getAllValues(age);
    }
}


class PersonNameDataGroup {
    // внутренние поля для хранения данных
    MyHashMap personMap = new MyHashMap<>();
    // алгоритм добавления студента в нужную группу согласно критерию
    public void addPerson(Person person) {
        String firstLetter = person.secondName.substring(0, 1);
        personMap.newPut(firstLetter, person);
        //personMap.newPut(person.secondName, person);
    }
    // возвращает студентов с фамилией, начинающейся на указанную букву
    public Person[] getPersons(String secondName) {
        String firstLetter = secondName.substring(0, 1);
        return personMap.getAllValues(firstLetter);
        //return personMap.newGet(secondName);
    }
}