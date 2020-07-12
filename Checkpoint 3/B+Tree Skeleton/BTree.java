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

    /**
     *
     * @param studentId
     * @return
     */
    long search(long studentId) {
        
        if(root==null) {
            return -1;
        }   

        BTreeNode current = root;
        //System.out.println("\n BEGIN SEARCHING FOR "+studentId);
        
            
            for(int i = 0;i<current.n;i++) {
                //Find the first key that is bigger than studentId
                if(current.keys[i]>studentId) {
                    //System.out.println("FOUND BIGGER KEY"+current.keys[i]);
                    //Check if we are at a leaf node
                    if(i>0) {
                        //If we are at leaf, check for key that matches studentId and return record
                        if(current.keys[i-1]==studentId && current.leaf) {
                            //System.out.println("Normal case");
                            //System.out.println("SEARCH: "+current.keys[i-1]+" "+current.values[i-1]);
                            return current.values[i-1];
                        }
                        //No match, return -1
                        else if(current.keys[i-1]<studentId && current.leaf) {
                            //System.out.println("SMALLER KEY"+current.keys[i-1]);
                            return -1;
                        }
                    }
                    //If we are at leaf and studentId is less than first key, no match
                    if(current.leaf) {
                        //System.out.println("FIRST INDEX");
                        //System.out.print(current.keys[0]+" ");
                        //System.out.print(current.keys[1]+" ");
                        //System.out.println(current.keys[2]+" ");
                        return -1;
                    }
                    System.out.println("Normal case internal");
                    //If we are not at a leaf, go to the appropriate child node
                    current = current.children[i];
                    i = 0;

                    
                }
                //If studentId is larger than all keys, go to the last pointer
                else if(i==current.n-1) {
                    //System.out.println("LARGER than all keys");
                    if(current.keys[i]<=studentId) {
                        //If key is match with studentId and we are at a leaf, return the record
                        if(current.keys[i]==studentId && current.leaf) {
                           // System.out.println("Case last key");
                            //System.out.println("SEARCH: "+current.keys[i]+" "+current.values[i]);
                            return current.values[i];
                        }
                        //If key does not match and we are at a leaf, return no result 
                        else if(current.keys[i]<studentId && current.leaf) {
                            return -1;
                        }
                        //If we are not at a leaf, go to the correct child node
                        else {
                           // System.out.println("Last case internal");
                            current = current.children[i+1];
                            i=0;
                        }
                    }
                }
            }
        
        
        return -1;
    }

    /**
     *
     * @param student
     * @return
     */
//the start of the insert algrithm 
// has two cases either it is creating the root or in need to search further in the tree
// @ param Student student- this is the student object that is attempting to be inserted into the tree
// return - void
    BTree insert(Student student) {
    	if(root==null) {
    		BTreeNode temproot = new BTreeNode(t,true);
    		temproot.keys[0] = student.studentId;
    		temproot.values[0] = student.recordId;
    		temproot.n=1;
    		root = temproot;	
    		
    		
    	}else {
    		int recursivesearchresponse = recursivesearch(root, student);
    		if(recursivesearchresponse ==-2) {
    			BTreeNode paparoot = new BTreeNode(t,false);
    			split(paparoot, root);
    			root = paparoot;
    		}else if(recursivesearchresponse==-1) {
    			
    			BTreeNode paparoot = new BTreeNode(t,false);
    			split(paparoot, root);
    			root = paparoot;
    		}	
    	}
        return this;
    }
    // this method searches through out the tree recursivly calling down 
    // until it reaches the leaf where the student needs to be placed in
    // then attempts to place it in the leaf
    //@ parm BTreeNode input- this is the current node that the method is traversing
    // @ parm- Student student- this is the student object attempting to be placed in the tree
    // returns 1 on sucsses 
    // returns -1 on needing a node split
    // return -2 on needing a leaf split
    int recursivesearch(BTreeNode input, Student student) {
    	if(input.leaf) {
    		// this is the base case and it is a leaf
    		int leafplacementreturn = input.placeinleaf(student);
    		return leafplacementreturn;
    		
    	}else {
    		// this means that it has to go deeper
    		boolean notfound =true;
    		for(int i=0; i< input.n;i++) {
    			if(student.studentId<input.keys[i]) {
    				// this means that it wants to place the value in that location
    				// i am going to have a method that returns here so the for loop
    				// will break
    				notfound = false;
    				int recursivesearchvalue =recursivesearch(input.children[i],  student);
    				if (recursivesearchvalue<0) {
    					return split(input, input.children[i]);
    				}
    				i=input.n;
    			}
    		}
    		// run this method outside the for loop on n, becuase 
    		// it means it will be placed in that method aka its is larger than every key
    		if(notfound) {
    			int recursivesearchvalue =recursivesearch(input.children[input.n],  student);
				if (recursivesearchvalue<0) {
					return split(input, input.children[input.n]);
				}
    		}
    	}
    	
    	return 1;
    }
    // this is the method that actully does te splitting the nodes
    // it has four cases:
    // the root is a leaf and it is splitting
    // the root is a node and it is spliting
    // a nonroot leaf is splitting
    // a nonroot node is spltting
    // @ parm BTreeNode parrent- this is the node that will be gaining more children
    // 2 parm BTreeNode child this is the child that has been split but now needs to return the children
    // return 1 is no other things need to be split
    // return -1 if the level above also needs to be split
    int split(BTreeNode parrent, BTreeNode child) {
    	// we need a speacial case if the child is a root and he is a leaf
    	if(root.leaf) {
    		BTreeNode newrightchild = new BTreeNode(t,true);
    		newrightchild.keys= root.subkeys;
    		newrightchild.values = root.subvalues;
    		newrightchild.n = t+1;// the right child always get the exstra one
    		root.next = newrightchild;
    		parrent.keys[0] = newrightchild.keys[0];
    		parrent.children[0]= root;
    		parrent.children[1] = newrightchild;
    		parrent.n=1;
    		// set theses to null so it takes up less space
    		child.subkeys =null;
    		child.subvalues =null;
    		// this is the special case that the root node is split
    	}else if(child==root) {
    		parrent.keys[0] = root.subkeys[0];
    		BTreeNode newrightchild = new BTreeNode(t,true);
    		newrightchild.keys= child.subkeys;
    		newrightchild.children = root.subchildren;
    		newrightchild.n = t+1;
    		
    		parrent.children[0] = root;
    		parrent.children[1] = newrightchild;
    		child.subkeys =null;
    		child.subvalues =null;
    		child.subchildren =null;
    	}
    	// the next case is if it is just a ragular leaf that needs splitting
    	else if (child.leaf) {
    		BTreeNode newrightchild = new BTreeNode(t,true);
    		newrightchild.keys= child.subkeys;
    		newrightchild.values = child.subvalues;
    		newrightchild.n = t+1;// the right child always get the exstra one
    		// now time for to insert the right in the line of nexts
    		BTreeNode temp = child.next;
    		child.next = newrightchild;
    		newrightchild.next = temp;
    		child.subkeys =null;
    		child.subvalues =null;
    		child.subchildren =null;
    		return parrent.placeinchild(newrightchild);
    		// now i need to make is so it places the child into the correct place
    		// now we have to have the case were it splits a node
    	}else {
    		BTreeNode newrightchild = new BTreeNode(t,false);
    		newrightchild.keys= child.subkeys;
    		newrightchild.children = child.subchildren;
    		newrightchild.n = t+1;
    		child.subkeys =null;
    		child.subvalues =null;
    		child.subchildren =null;
    		return parrent.placeinchild(newrightchild);
    		
    	}
    	
    	return 1;
    }

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
            //return recursive method result
            return delete(parentPtr,nodePtr, studentId, oldChildEntry);
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
        if(nodePtr.leaf = true) {
            //nodePtr IS pointing to a LEAF, in which case:
            // Check if there the current node will still be half full after deletion
            if (nodePtr.n > nodePtr.t) {
                //deleting the entry will not infringe on minimum degree
                nodePtr = deleteEntry(nodePtr, studentId);
                oldChildNode = null;
                return true;// we're done!
            } else {//Removing entry will result in violation of minimum degree!
                BTreeNode sibling = nodePtr.next;
                if (sibling.n > sibling.t) {
                    long lowKey = sibling.keys[0];
                    //if there is enough entries to ensure t entries are left (after deletion/redis.)
                    // 1st, actually delete the value
                    nodePtr = deleteEntry(nodePtr, studentId);//removed the entry, t - 1 entries
                    // TODO REDISTRIBUTE evenly between sibling and leaf node (nodePtr)
                    redistributeLeafNodes(nodePtr,sibling,parentPtr);
                    // find entry in parent for node on right (NOT the sibling, but right of sibling)
                    BTreeNode siblingEntry = findSiblingPtrFromParent(parentPtr,nodePtr);
                    // replace key value in parent entry by new low-key value in M
                    oldChildNode = null;
                    return true;
                } else {
                    // TODO MERGE Sibling and selected leaf node together, (call node on rhs M)
                    // Assign OldChildEntry
                    oldChildNode = findParentPtrToSibling(sibling, parentPtr);
                    //TODO move all entries from M (entry in parent for node on right)
                    //TODO discard empty node M, adjust sibling pointers, return;
                }
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
        //TODO Remove and SHIFT BUT DON"T CHANGE SIZE
        int removeIndex = -1; //index to remove values from both BTreeNode's key[] and value[] arrays
        long[] newKeys = new long[leafNode.n];
        long[] newValues = new long[leafNode.n];

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

        //Update N
        leafNode.n--;

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

    BTreeNode findParentPtrToSibling(BTreeNode sibling, BTreeNode parentNode){
        for(int i = 0; i < parentNode.children.length; i++){
            if(parentNode.children[i].equals(sibling)){
                return parentNode.children[i];
            }
        }//if no match between sibling and child of parent can be found (should NOT be the case)
        return null;
    }

    /**
     *
     * @param leaf
     * @param sibling
     * @param parent
     * @return
     */
    void redistributeLeafNodes(BTreeNode leaf, BTreeNode sibling, BTreeNode parent){
        //TODO expand
        //TODO UPDATE N FOR BOTH nodes
        //redistribute evenly between the node and its sibling
        int diffN = (sibling.n - leaf.n)/2;// Num keys moving to to left side (leaf) from sibling

        long[] tempKeys = new long[diffN];
        long[] tempVals = new long[diffN];
        for(int i = 0; i < diffN; i++){
            //Copy values to transfer
            tempKeys[i] = sibling.keys[i];
            tempVals[i] = sibling.values[i];

            //Now delete entry from Sibling
            sibling = deleteEntry(sibling,sibling.keys[i]);
        }

        return;
    }

    /**
     *
     * @param inner
     * @param sibling
     * @param parent
     * @return
     */
    BTreeNode redistributeInnerNode(BTreeNode inner, BTreeNode sibling, BTreeNode parent){
        //TODO expand
        return parent;
    }

    /**
     *
     * @param inner
     * @param M
     * @param parent
     * @return
     */
    BTreeNode mergeInnerNode(BTreeNode inner, BTreeNode M, BTreeNode parent){
        //TODO expand
        return parent;
    }

    /**
     *
     * @param leaf
     * @param M
     * @param parent
     * @return
     */
    BTreeNode mergeLeaf(BTreeNode leaf, BTreeNode M, BTreeNode parent){
        // In this specific case, M, the node on the RHS of the leaf, is also the sibling
        // We don't want to use the sibling pointer however, because the pointer isn't coming from the parent
        // but from the leaf
        //TODO expand
        return parent;
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
