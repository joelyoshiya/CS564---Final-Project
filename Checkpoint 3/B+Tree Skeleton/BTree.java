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
                if(i==current.n-1) {
                    if(current.keys[i]<=studentId) {
                        //If key is match with studentId and we are at a leaf, return the record
                        if(current.keys[i]==studentId && current.leaf) {
                            return current.values[i+1];
                        }
                        //If key does not match and we are at a leaf, return no result 
                        else if(current.keys[i]<studentId && current.leaf) {
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

    /**
     * TODO:
     * @jyfoster - implement this function
     * Implement this function to delete in the B+Tree and student table.
     * Return true if the student is deleted successfully otherwise, return false.
     * @param studentId - the student entry we'd like to delete
     * @return - true or false, depending on a successful delete or not
     */
    boolean delete(long studentId) {
        //   Deletes entry in the BTree structure given search-key, studentID
        //   Following psuedo-code in textbook, pg.353

        //   local vars
        BTreeNode parentPtr;//The parent of the current node we're pointing at, null for now
        BTreeNode nodePtr; //The current node we are pointing at
        //   long studentID passed in as arg (entry)
        BTreeNode oldChildEntry;//Null at start, only non-null when merge is required

        //   First figure out if entry exists
        //   If value exists, execute, else return false (value doesn't exist or nothing in Btree)
        if(search(studentId) != -1 || root != null){
            parentPtr = null;
            nodePtr = root;
            //studentId already passed from arg
            oldChildEntry = null;
            if(delete(parentPtr,nodePtr, studentId, oldChildEntry) == true){
                // Essentially, if the actual recursive method returns true, return true
                return true;
            }//else, return false
            return false;
        }
        //entry does not exist, or root is empty -> return false
        return false;
    }

    /**
     * The actual recursive method called to perform the deletion,
     * Called in delete(long studentId) (overloaded method)
     * @param parentPtr
     * @param nodePtr
     * @param studentId
     * @param oldChildEntry
     * @return
     */
    boolean delete(BTreeNode parentPtr, BTreeNode nodePtr, long studentId, BTreeNode oldChildEntry){

        int childIndex = -1; // index we will use to get to correct subtree by accessing pointer in BTreeNodeChildren[]

        //Check if the current node we are at is an inner, non-leaf node:
        if(nodePtr.leaf == false){
            // Find the correct subtree (index to use), by comparing key values to studentId
            for (int i = 0; i < nodePtr.n; i++){
                //Check if studentId is less than all keys in current node
                if(studentId < nodePtr.keys[0]){
                    childIndex = 0;
                }else {//entry key is greater than or equal to current node's key indices
                    if (i < nodePtr.n - 1) {//As long as i is not at the last key in the keys field
                        if ((nodePtr.keys[i] <= studentId) && (nodePtr.keys[i + 1] > studentId)) {
                            childIndex = i + 1; //this is because the correct
                            //childIndex will always be the index of the correct key index plus one
                            //(except the case where the studenId is greater than or less than all index key values)
                        }
                    }
                    //   at this point, we can say that the studentId is greater than all keys
                    //   So, we will go the largest index for child
                    childIndex = nodePtr.children.length - 1;
                }
            }
            //Now, child index is found, so we can recursively call delete again:
            delete(nodePtr, nodePtr.children[childIndex], studentId, oldChildEntry);

            //Check if oldChildEntry is null

        }
        //////////////////////////////////////////////////////////////////////////////////////////
        //nodePtr IS pointing to a LEAF, in which case:
        // Check if there the current node will still be half full after deletion
        if( nodePtr.n > nodePtr.t){
            //deleting the entry will not infringe on minimum degree
            //Now, delete!

            return true;
        }


        return false;
    }

    /**
     * TODO:
     * Implement this function to print the B+Tree.
     * Return a list of recordIDs from left to right of leaf nodes.
     *
     */
    List<Long> print() {

        List<Long> listOfRecordID = new ArrayList<>();
        BTreeNode current = this.root;

        //check if tree is empty
        if(this.root==null) {
            return listOfRecordID;
        }

        //find the leftmost leaf node
        while(!current.leaf) {
            current=current.children[0];
        }

        //print key and value in order, also add values to array list
        while(current!=null) {
            for(int i = 0; i<current.n; i++) {
                System.out.println("Key: "+current.keys[i]+" Value:"+current.values[i]);
                listOfRecordID.add(current.values[i]);
            }
            //use pointer to next leaf node
            current = current.next;
        }

        return listOfRecordID;
    }
}
