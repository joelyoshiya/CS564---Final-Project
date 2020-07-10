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
        if(search(studentId) != -1){
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
     * @param oldChildNode
     * @return
     */
    boolean delete(BTreeNode parentPtr, BTreeNode nodePtr, long studentId, BTreeNode oldChildNode){

        //Check if the current node we are at is an inner, non-leaf node:
        if(nodePtr.leaf == false){
            // Find the correct subtree (index to use), by comparing key values to studentId
            BTreeNode subTreeNode = findSubTree(nodePtr, studentId);

            //Now, child index is found, so we can recursively call delete again:
            delete(nodePtr, subTreeNode, studentId, oldChildNode);

            //Check if oldChildNode is null
            if(oldChildNode == null){//usual case: child not deleted
                return true; // done, there was no child that needed to be deleted
            }else{//there exists an oldChildNode -> have to remove
                  //remove oldChildNode from nodePtr
                removeChildNodeFromParent(oldChildNode, nodePtr);

                if(nodePtr.n > nodePtr.t){//if we can remove without violating minimum degree prop
                    oldChildNode = null; //delete doesn't go further
                    return true;//done we, don't have to modify any more
                }else{//This inner node VIOLATES minimum degree property
                      //need to either redistribute or merge
                    //TODO get sibling of nodePtr
                    BTreeNode sibling = findSiblingPtrFromParent(parentPtr, nodePtr);
                    if(sibling.n > sibling.t){
                        // TODO REDISTRIBUTE EVENLY between nodePtr and Sibling through parent
                        oldChildNode = null;
                        return true;
                    }else{
                        // TODO MERGE sibling and nodePtr, (Call node on RHS "M")
                        // reassign OldChildrenEntry to the current pointer entry in parent of M
                        // pull splitting key from parent down into node on left
                        // move all entries from M to node on left
                        //discard empty node M
                        return true;
                    }
                }
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////
        // @ THE LEAF LEVEL NOW
        //nodePtr IS pointing to a LEAF, in which case:
        // Check if there the current node will still be half full after deletion
        if( nodePtr.n > nodePtr.t){
            //deleting the entry will not infringe on minimum degree
            nodePtr = deleteEntry(nodePtr, studentId);
            return true;// we're done!
        }else{
            BTreeNode sibling = nodePtr.next;
            if(sibling.n > sibling.t){
                //if there is enough entries to ensure t entries are left (after redis.)
                //TODO REDISTRIBUTE evenly between sibling and leaf node (nodePtr)
            }else{
                // TODO MERGE Sibling and selected leaf node together, (call node on rhs M)
                // Assign OldChildEntry
                oldChildNode = findParentPtrFromSibling(sibling, parentPtr);
                //TODO move all entries from M (entry in parent for node on right)
                //TODO discard empty node M, adjust sibling pointers, return;
            }
        }
        //lies outside all if/else statements
        return false;
    }

    /**
     * Given a key, studentId, use the given node to find the correct sub-tree node that
     * expands the path towards the key
     * @param nodePtr
     * @param studentId
     * @return
     */
    BTreeNode findSubTree(BTreeNode nodePtr, long studentId) {
        int childIndex = -1;// index we will use to get to correct subtree by accessing pointer in BTreeNodeChildren[]
        // Find the correct subtree (index to use), by comparing key values to studentId
        for (int i = 0; i < nodePtr.n; i++) {
            //Check if studentId is less than all keys in current node
            if (studentId < nodePtr.keys[0]) {
                childIndex = 0;
            } else {//entry key is greater than or equal to current node's key indices
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
        return nodePtr.children[childIndex];
    }
    /**
     * Helper method to actually do the work of removing the entry from the leaf node
     * @param leafNode
     * @param studentID
     */
    BTreeNode deleteEntry(BTreeNode leafNode, long studentID){
        int removeIndex = -1; //index to remove values from both BTreeNode's key[] and value[] arrays
        long[] newKeys = new long[leafNode.n -1];
        long[] newValues = new long[leafNode.n -1];

        //Find a match between studentID to be removed, and nodes keys
        for(int i = 0; i < leafNode.n; i++){
            if(leafNode.keys[i] == studentID){
                removeIndex = i;
            }
        }
        int j = 0;// the index for the new arrays
        // Copies into arrays all except those to be removed, in same order
        for (int i = 0; i < leafNode.n; i++){
            if (i == removeIndex) {
                continue;// i still increments, but nothing is done (skips the removed index)
            }
            newKeys[j] = leafNode.keys[i];
            newValues[j] = leafNode.values[i];
            j++;
        }
        // Update arrays in the original node
        leafNode.keys = newKeys;
        leafNode.keys = newValues;

        return leafNode;

    }

    void removeChildNodeFromParent(BTreeNode oldChildNode, BTreeNode parent){
        for(int i = 0; i < parent.children.length; i++){
            if(parent.children[i].equals(oldChildNode)){
                parent.children[i] = null;
                //if
            }
        }
    }

    BTreeNode findParentPtrFromSibling(BTreeNode sibling, BTreeNode parentNode){
        for(int i = 0; i < parentNode.children.length; i++){
            if(parentNode.children[i].equals(sibling)){
                return parentNode.children[i];
            }
        }//if no match between sibling and child of parent can be found (should NOT be the case)
        return null;
    }

    /**
     * Find the sibling of the current inner node using the parent
     * Can't use node.next because not a leaf node
     * @param parentPtr
     * @param nodePtr
     * @return
     */
    BTreeNode findSiblingPtrFromParent(BTreeNode parentPtr, BTreeNode nodePtr){
        //Sibling should be one to the right in the children array
        for(int i = 0; i < parentPtr.children.length; i++){
            if(parentPtr.children[i].equals(nodePtr)){
                return parentPtr.children[i + 1];//returns the right-hand-side sibling of the current node
            }
        }
        return null;
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
