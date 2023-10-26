package students;

class PersonNameDataGroup {
    // внутренние поля для хранения данных
    MyHashMap<String, Person> personMap = new MyHashMap<>();
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
