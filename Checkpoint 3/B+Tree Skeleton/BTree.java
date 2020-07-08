/**
 * Do NOT modify.
 * This is the class with the main function
 */

import java.util.ArrayList;
import java.util.List;

/**
 * B+Tree Structure
 * Key - StudentId
 * Leaf Node should contain [ key,recordId ]
 */
class BTree {

    /**
     * Pointer to the root node.
     */
    private BTreeNode root;
    /**
     * Number of key-value pairs allowed in the tree/the minimum degree of B+Tree
     **/
    private int t;

    BTree(int t) {
        this.root = null;
        this.t = t;
    }

    long search(long studentId) {
        
        if(root==null) {
            return -1;
        }   

        BTreeNode current = root;
        boolean searching = true;
        while(searching) {
            for(int i = 0;i<current.n;i++) {
                //Find the first key that is bigger than studentId
                if(current.keys[i]>studentId) {
                    //Check if we are at a leaf node
                    if(i>0) {
                        //If we are at leaf, check for key that matches studentId and return record
                        if(current.keys[i-1]==studentId && current.leaf) {
                            return current.values[i];
                        }
                        //No match, return -1
                        else if(current.keys[i-1]<studentId && current.leaf) {
                            return -1;
                        }
                    }
                    //If we are at leaf and studentId is less than first key, no match
                    if(current.leaf) {
                        return -1;
                    }

                    //If we are not at a leaf, go to the appropriate child node
                    current = current.children[i];

                    
                }
                //If studentId is larger than all keys, go to the last pointer
                if(i==2*t-1) {
                    if(current.keys[i]<=studentId) {
                        //If key is match with studentId and we are at a leaf, return the record
                        if(current.keys[i]==studentId && current.leaf) {
                            return current.values[i+1];
                        }
                        //If key does not match and we are at a leaf, return no result 
                        else if(current.keys[i]>studentId && current.leaf) {
                            return -1;
                        }
                        //If we are not at a leaf, go to the correct child node
                        else {
                            current = current.children[i+1];
                        }
                    }
                }
            }
        }
        
        return -1;
    }

    BTree insert(Student student) {
        /**
         * TODO:
         * Implement this function to insert in the B+Tree.
         * Also, implement in student.csv after inserting in B+Tree.
         */
        return this;
    }

    boolean delete(long studentId) {
        /**
         * TODO:
         * @jyfoster - implement this function
         * Implement this function to delete in the B+Tree and student table.
         * Return true if the student is deleted successfully otherwise, return false.
         */
        return true;
    }

    List<Long> print() {

        List<Long> listOfRecordID = new ArrayList<>();

        /**
         * TODO:
         * Implement this function to print the B+Tree.
         * Return a list of recordIDs from left to right of leaf nodes.
         *
         */
        return listOfRecordID;
    }
}
