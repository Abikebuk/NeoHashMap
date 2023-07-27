package NeoHashMap.Nodes;

import java.util.ArrayList;

public class SingleChainedValueNode<V>{
    /**
     * Data of the node
     */
    private V data;

    private boolean canOverflow;
    /**
     * Next element in the single chained list
     */
    private SingleChainedValueNode<V> next;

    /**
     * Constructors
     */

    /**
     * Constructor
     * @param data data of the node
     * @param canOverflow defines if the node can overflow (can have a next)
     */
    public SingleChainedValueNode(V data, boolean canOverflow) {
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
    public SingleChainedValueNode(V data, SingleChainedValueNode<V> next){
        this.data = data;
        this.canOverflow = true;
        this.next = next;
    }

    /**
     * Functions
     */

    /**
     * Return all the values in the node tree left first.
     * @return all the values in the value node (can be multiple if there is overflow)
     */
    public ArrayList<V> getValues(){
        ArrayList<V> result = new ArrayList<V>();
        result.add(data);
        result.addAll(next.getValues());
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
}
