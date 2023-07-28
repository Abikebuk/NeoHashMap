package NeoHashMap.Nodes;

import java.util.ArrayList;

/**
 * Single chained value node class
 * @param <V> the type of the data
 */
public class SingleChainedDataNode<V>{
    /**
     * Data of the node
     */
    private V data;

    private boolean canOverflow;
    /**
     * Next element in the single chained list
     */
    private SingleChainedDataNode<V> next;

    /**
     * Constructors
     */

    /**
     * Constructor
     * @param data data of the node
     * @param canOverflow defines if the node can overflow (can have a next)
     */
    public SingleChainedDataNode(V data, boolean canOverflow) {
        this.data = data;
        this.canOverflow = true;
        this.next = null;

    }

    /**
     * Constructor
     * canOverflow is true if there is a next.
     * @param data data of the node
     * @param next next data node
     */
    public SingleChainedDataNode(V data, SingleChainedDataNode<V> next){
        this.data = data;
        this.canOverflow = true;
        this.next = next;
    }

    /**
     * Methods
     */

    /**
     * Prints the content of the list
     * @param prefix string appended to the start of each lines
     */
    public void print(String prefix){
        System.out.format("%s    |- $ [DATA] Value : %s\n", prefix, data.toString());
        if(next != null) next.print(prefix);
    }
    /**
     * Add data to the chained list
     * Overwrites the data instead if cannot overflow
     * @param value
     */
    public void add(V value){
        if(canOverflow){
            if(next == null)
                next = new SingleChainedDataNode<V>(value, canOverflow);
            else
                next.add(value);
        }
        else // if it cannot overflow
            data = value;
    }

    /**
     * Getter & Setters
     */

    /**
     * Return all the values in the node tree left first.
     * @return all the values in the value node (can be multiple if there is overflow)
     */
    public ArrayList<V> getValues(){
        ArrayList<V> result = new ArrayList<V>();
        result.add(data);
        if(next != null) result.addAll(next.getValues());
        return result;
    }

    /**
     * Common Getters & Setters
     */

    /**
     * Returns the data
     * @return data
     */
    public V getData() {
        return data;
    }

    /**
     * Sets the data
     * @param data
     */
    public void setData(V data){
        this.data = data;
    }

    public SingleChainedDataNode getNext(){
        return next;
    }
}
