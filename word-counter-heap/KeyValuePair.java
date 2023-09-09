/*
Luhang Sun
CS 231 project 8
KeyValuePair.java
*/

public class KeyValuePair <Key, Value>{
    private Key key;
    private Value value;

    public KeyValuePair(Key k, Value v){
        this.key = k;
        this.value = v;
    }

    public Key getKey(){
        return this.key;
    }

    public Value getValue(){
        return this.value;
    }

    public void setValue (Value v){
        this.value = v;
    }

    public String toString(){
        String s = this.key + " " + this.value;
        return s;
    }

    public static void main(String[] args){
        KeyValuePair<String, Integer> k = new KeyValuePair<String,Integer>("yes", 99);
        System.out.println(k.getKey());
        System.out.println(k.getValue());
        k.setValue(10);
        System.out.println(k);
    }
}