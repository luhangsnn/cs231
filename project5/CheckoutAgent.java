/*
Luhang Sun
CS231 Project 5
CheckoutAgent.java
*/
import java.awt.*;

public class CheckoutAgent {
    private int x;
    private int y;
    private MyQueue <Customer> q;

    public CheckoutAgent(int x, int y){
        this.x = x;
        this.y = y;
        this.q = new MyQueue <Customer> ();
    }

    public void addCustomerToQueue (Customer c){
        this.q.offer(c);
    }

    public int getNumInQueue(){
        return q.size;
    }

    public int getTotalItem(){
        int total_item = 0;
        for (Customer c: q){
            total_item += c.getNumItems();
        }
        return total_item;
    }

    public void draw (Graphics g){
        int N = this.getNumInQueue();
        g.setColor(new Color (153, 204, 255));
        g.fillRect(this.x, this.y - 10*N, 10, 10*N);

        g.setColor(Color.black);
        g.drawRect(this.x-15, this.y, 40, 30);
        g.drawString(Integer.toString(this.getNumInQueue()), this.x, this.y+15);
    }

    //add customers to the finishedlist of Landscape
    public void updateState (Landscape scape) {
        //loop through the customers and increment time
        for (Customer c: q){
            c.incrementTime();
        }

        //look at the first customer
        Customer one = q.peek();
        if (one == null){
            return;
        }  
        else { 
           one.giveUpItem(); 
        }   
        //remove the customer from the queue if no item
        if (one.getNumItems() == 0){ 
            scape.addFinishedCustomers(q.poll());
        }
    }

    //add the finished customer to the finished_categorized list of Landscape
    public void updateStateCategorized (Landscape scape) {
        for (Customer c: q){
            c.incrementTime();
        }

        Customer one = q.peek();
        if (one == null){
            return;
        }  
        else {
           one.giveUpItem(); 
        }
            
        if (one.getNumItems() == 0) {
            scape.addToCategorizedList(q.poll()); //add to the categorized list
        }  
    }
}