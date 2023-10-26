package students;

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
