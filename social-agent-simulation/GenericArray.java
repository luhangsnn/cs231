/*
Luhang Sun
CS231 Project 4
GenericArray.java
*/

public class GenericArray <T> {
    private Object list[]; //use a generic array to hold objects(agents)
    private int N; // size of the list or the index of the next free space in the list

    public GenericArray (int size){
        this.list = new Object [size];
        this.N =0;
    }

    //empty the list    
    public void clear(){
        this.N=0;
    }

    public int size(){
        return this.N;
    }

    //adding an item at the end of the list
    public void addLast(T item){
        //if the array is full, double its size
        if (this.N == list.length){
            Object [] newList = new Object [list.length * 2];

            for (int i=0; i<this.N; i++){
                newList[i] = this.list[i];
            }
            this.list = newList;
        }
        this.list[this.N] = item;
        this.N++;
    }

    //adding an item at the beginning of the list
    public void addFirst (T item){        
        if (this.N == list.length){ //if the array is full, double its size
            Object [] newList = new Object [list.length * 2];

            //shifting the elements down by 1 to make space for adding an element at the beginning
            for (int i=0; i<this.N; i++){ 
                newList[i+1] = this.list[i];
            }
            this.list = newList;
        }
        else{
            for (int i = this.N; i>0; i--){
                this.list[i] = this.list[i-1];
            }
        }
        this.list[0] = item;        
        this.N++;
    }

    //add an element at index position in the list
    public void add(int index, T item){
        if (index < 0 || index > N-1){ //check if the index is valid
            System.out.println("Index out of bound");
            return;
        }

        if (this.N == list.length){ //if the array is full, double its size
            Object [] newList = new Object [list.length * 2];
            for (int i=0; i<index; i++){ //copying everything before the index position
                newList[i] = this.list[i];
            }
            for (int i = index; i<N; i++){ //copy everything after the index position
                newList[i+1] = this.list[i];
            }
            this.list = newList;
        }
        else{
            for (int i = this.N; i>index; i--) { //shift everything after the index down
                this.list[i] = this.list[i-1];
            } 
        }
        this.list[index] = item;
        this.N++;
    }

    //return the element at index
    @SuppressWarnings("unchecked")
    public T get (int index){
        if( index < 0 || index >= N ) {
			System.out.println("Index out of bound");
			return null;
		}
		return (T) this.list[index];
    }

    //return and remove the element at index
    @SuppressWarnings("unchecked")
    public T remove (int index){
        if( index<0 || index>=this.N ) {
			System.out.println("Index out of bound");
			return null;
        }
        T thing = (T) this.list[index];

        //moving the elements after index up by 1
        for (int i=index; i<this.N-1; i++){
            this.list[i] = this.list[i+1];
        }
        this.N --;
        return thing;
    }

    //print out the list
    public String toString(){
        String s = "";
        for (int i=0; i<N; i++){
            s += this.list[i] + "|";
        }
        return s;
    }

    public static void main(String [] args){
        GenericArray<Integer> list = new GenericArray<Integer> (1);

        list.addFirst(1);
        System.out.println(list);

        list.addFirst(2);
        list.addFirst(3);
        list.addLast(4);
        System.out.println(list);

        list.add(3, 5);
        System.out.println(list);

        System.out.println("removed " + list.remove(3));
        System.out.println(list);

        list.clear();
        System.out.println(list.size());
    }
}