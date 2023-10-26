package students;

class PersonAgeDataGroups {
    // внутренние поля для хранения данных
    MyHashMap<Integer, Person> personMap = new MyHashMap<>();
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