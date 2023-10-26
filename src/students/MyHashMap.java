package students;

import static java.lang.Math.abs;

public class MyHashMap<K, V> {
    //данные в бакет
    private class Entry<K, V>{
        private K key; //ключ
        private V value; //значение
        private Entry<K, V> next; //след. элемент в бакете

        //конструктор
        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }
        public void setValue(V value){
            this.value = value;
        }

    }

    private final int size = 32;
    private Entry<K, V> table[];
    //конструктор. выделяем место под массив с бакетами
    public MyHashMap(){
        table = new Entry[size];
    }

    public void put(K key, V value){
        //не забыть проверить на NULL!!!
        int hash = key.hashCode() % size; //получаем хэш ключа и кладем в нужную сыночку корзиночку
        Entry<K, V> e = table[hash]; //получаем все, что есть в бакете с номером, полученным выше

        //если изначально в бакете пусто
        if (e == null){
            table[hash] = new Entry<K, V>(key, value); //добавляем в бакет новый элемент
        }
        //если возникла коллизия (мб два случая: 1. ключ сущестует; 2. ключ не существует)
        else {
            //проходимся по всем элементам в бакете
            while(e.next != null){
                //если ключ существует, обновляем значение
                if (e.getKey() == key){
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }
            //проверяем последний элемент в корзине. Вынесено отдельно (и в while находится е.некст), потому что иначе не было бы указателя. Поэтому нам ещё нужна ссылка на последний элемент
            if (e.getKey() == key){
                e.setValue(value);
                return;
            }
            //создаем новый элемент в бакете; обновляем "ссылку"
            e.next = new Entry<K, V>(key, value);
        }
    }


    //аналог пут, но не удаляет значения с одинаковыми ключами
    public void newPut(K key, V value){
        //не забыть проверить на NULL!!!
        int hash = abs(key.hashCode()) % size; //получаем хэш ключа и кладем в нужную сыночку корзиночку
        Entry<K, V> e = table[hash]; //получаем все, что есть в бакете с номером, полученным выше

        //если изначально в бакете пусто
        if (e == null){
            table[hash] = new Entry<K, V>(key, value); //добавляем в бакет новый элемент
        }
        //если возникла коллизия (мб два случая: 1. ключ сущестует; 2. ключ не существует)
        else {
            //проходимся по всем элементам в бакете
            while(e.next != null){
                e = e.next;
            }
            //создаем новый элемент в бакете; обновляем "ссылку"
            e.next = new Entry<K, V>(key, value);
        }
    }


    public V get(K key){
        //не забыть проверить на NULL!!!
        int hash = key.hashCode() % size;
        Entry<K, V> e = table[hash];

        if (e == null){
            return null;
        }

        while (e != null){
            if (e.getKey().equals(key)){
                return e.getValue();
            }
            e = e.next;
        }
        return null;
    }


    //аналог гет, но возврает массив Person
    public Person[] getAllValues(K key){
        //не забыть проверить на NULL!!!
        int hash = abs(key.hashCode()) % size;
        Entry<K, V> e = table[hash];

        if (e == null){
            return null;
        }

        Person[] elem = new Person[1000];
        int iter = 0;
        while (e != null) {
            if (e.getKey().equals(key)) {
                while (iter >= elem.length){
                    //увеличение длины массива при заполнении с сохранением информации исходного массива
                    int newLength = (int)(elem.length * 1.5);
                    Person[] newPerson = new Person[newLength];
                    System.arraycopy(elem, 0, newPerson, 0, elem.length);
                    elem = newPerson;
                }
                elem[iter] = (Person)e.getValue();
                iter++;
            }
            e = e.next;
            //iter++;
        }
        if (iter == 0) return new Person[1]; //отладка - удалить
        return elem;
    }


    public boolean containsKey(K key){
        if (get(key) == null)
            return false;
        return true;
    }
}
