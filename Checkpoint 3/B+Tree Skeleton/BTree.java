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
     *This method searches for a studentId key within the B+Tree
     * @param studentId
     * @return the recordID that corresponds to studentID key if it exists, else -1
     */
    long search(long studentId) {
        
        if(root==null) {
            return -1;
        }   

        BTreeNode current = root;
        //System.out.println("\n BEGIN SEARCHING FOR "+studentId);
        
            //System.out.println("current.n is: " + current.n);
            for(int i = 0;i<current.n;i++) {
                //System.out.println("Currently at: " + i);
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
                    //System.out.println("Normal case internal");
                    //If we are not at a leaf, go to the appropriate child node
                    current = current.children[i];
                    i = -1;

                    
                }
                //If studentId is larger than all keys, go to the last pointer
                else if(i==current.n-1) {
                    //System.out.println("LARGER than all keys");
                    if(current.keys[i]<=studentId) {
                        //If key is match with studentId and we are at a leaf, return the record
                        if(current.keys[i]==studentId && current.leaf) {
                          //  System.out.println("Case last key");
                          //  System.out.println("SEARCH: "+current.keys[i]+" "+current.values[i]);
                            return current.values[i];
                        }
                        //If key does not match and we are at a leaf, return no result 
                        else if(current.keys[i]<studentId && current.leaf) {
                            return -1;
                        }
                        //If we are not at a leaf, go to the correct child node
                        else {
                            //System.out.println("Last case internal");
                            current = current.children[i+1];
                            i=-1;
                        }
                    }
                }
            }
        
        //System.out.println("Outside of FOR loop");
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
    int split(BTreeNode parent, BTreeNode child) {
    	// we need a speacial case if the child is a root and he is a leaf
    	if(root.leaf) {
    		BTreeNode newrightchild = new BTreeNode(t,true);
    		newrightchild.keys= root.getsubkeys();
    		newrightchild.values = root.getsubvalues();
    		newrightchild.n = t+1;// the right child always get the exstra one
    		root.next = newrightchild;
    		parent.keys[0] = newrightchild.keys[0];
    		parent.children[0]= root;
    		parent.children[1] = newrightchild;
    		parent.n=1;
    		child.clear();
    	}else if(child==root) {
    		parent.keys[0] = root.getsplitvalue();
    		BTreeNode newrightchild = new BTreeNode(t,false);
    		newrightchild.keys= child.getsubkeys();
    		newrightchild.children =root.getsubchildren();
    		newrightchild.n = t;
    		parent.children[0] = root;
    		parent.children[1] = newrightchild;
    		root.children[root.n].next = newrightchild.children[0];
    		parent.n=parent.n+1;
    		child.clear();

    	}
    	// the next case is if it is just a ragular leaf that needs splitting
    	else if (child.leaf) {
    		BTreeNode newrightchild = new BTreeNode(t,true);
    		newrightchild.keys= child.getsubkeys();
    		newrightchild.values = child.getsubvalues();
    		newrightchild.n = t+1;// the right child always get the exstra one
    		// now time for to insert the right in the line of nexts
    		BTreeNode temp = child.next;
    		child.next = newrightchild;
    		newrightchild.next = temp;
    		child.clear();
    		return parent.placeinchild(newrightchild, child.getsplitvalue());
    		// now i need to make is so it places the child into the correct place
    		// now we have to have the case were it splits a node
    	}else {
    		BTreeNode newrightchild = new BTreeNode(t,false);
    		newrightchild.keys= child.getsubkeys();
    		newrightchild.children = child.getsubchildren();
    		newrightchild.n = t;
    		child.children[child.n].next = newrightchild.children[0];
    		child.clear();
    		return parent.placeinchild(newrightchild,child.getsplitvalue());
    		
    	}
    	
    	return 1;
    }
    

    boolean delete(long studentId) {
	      System.out.println("Starting delete: " + studentId);
        //   Deletes entry in the BTree structure given search-key, studentID
        //   Following psuedo-code in textbook, pg.353

        //   local vars
        BTreeNode parentPtr;//The parent of the current node we're pointing at, null for now
        BTreeNode nodePtr; //The current node we are pointing at
        //   long studentID passed in as arg (entry)
        BTreeNode oldChildEntry;//Null at start, only non-null when merge is required
        int currIndex = -1;

        //   First figure out if entry exists
        //   If value exists, execute, else return false (value doesn't exist or nothing in Btree)
        if(search(studentId) != -1){
            parentPtr = null;
            nodePtr = root;
            //studentId already passed from arg
            oldChildEntry = null;
            //return recursive method result
            return delete(parentPtr,nodePtr, studentId, oldChildEntry, currIndex);
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
    boolean delete(BTreeNode parentPtr, BTreeNode nodePtr, long studentId, BTreeNode oldChildNode, int currIndex){

        //Check if the current node we are at is an inner, non-leaf node:
        if(!nodePtr.leaf){
            // Find the correct subtree (index to use), by comparing key values to studentId
            //BTreeNode subTreeNode = findSubTree(nodePtr, studentId);
            int childIndex=0;
            for(int i = 0;i<nodePtr.n;i++) {
                System.out.println(nodePtr.keys[i]);
                //Find the first key that is bigger than studentId
                if(nodePtr.keys[i]>studentId) {
                    childIndex = i;
                    break;
                }
                //If studentId is larger than all keys, go to the last pointer
                else if(i==nodePtr.n-1) {
                    childIndex=i+1;
                    break;
                }
            }
            System.out.println("Nodeptr.n: " + nodePtr.n);
            System.out.println("Nodeptr not leaf, go to index: "+childIndex);
            //Now, child index is found, so we can recursively call delete again:
            return delete(nodePtr, nodePtr.children[childIndex], studentId, oldChildNode, childIndex);
            
            /** 
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
            */

        }
        //////////////////////////////////////////////////////////////////////////////////////////
        if(nodePtr.leaf) {
            System.out.println("Arrived at leaf");
            //nodePtr IS pointing to a LEAF, in which case:
            // Check if there the current node will still be half full after deletion
            if (nodePtr.n > nodePtr.t || nodePtr.equals(root)) {//BASE CASE
                System.out.println("Basic case");
                //deleting the entry will not infringe on minimum degree
                nodePtr.deleteEntry(studentId);
                oldChildNode = null;
                return true;// we're done!
            } else {//Removing entry will result in violation of minimum degree!
                System.out.println("Else case");

                //CASE WHERE THERE IS BOTH A LEFT AND RIGHT SIBLING
                int leftIndex = findSiblingPtrFromParent(parentPtr,nodePtr, false);
                System.out.println("leftIndex: " + leftIndex);
                int rightIndex = findSiblingPtrFromParent(parentPtr, nodePtr, true);
                System.out.println("rightIndex: " + rightIndex);


                boolean right;
                //COMPARE WHICH SIBLING HAS HIGHER N
                int bestIndex = 0;
                //Case where you are farthest right
                if(rightIndex == -1){
                    bestIndex = leftIndex;
                    right = false;
                }else if(leftIndex == -1){
                    bestIndex = rightIndex;
                    right = true;
                }else{
                    //CAN ACCESS BOTH SIBLINGS AT THS POINT
                    System.out.println("N value choosing leftIndex: " + parentPtr.children[leftIndex].n);
                    System.out.println("N value choosing rightIndex: " + parentPtr.children[rightIndex].n);
                    //CHOOSE SIBlING WITH HIGHEST N
                    if(parentPtr.children[rightIndex].n > parentPtr.children[leftIndex].n){
                        bestIndex = rightIndex;
                        right = true;
                    }else{
                        bestIndex = leftIndex;
                        right = false;
                    }
                }
                System.out.println("bestIndex: " + bestIndex);

                //USE THAT SIBLING TO SEE IF REDISTRIBUTION IS POSSIBLE
                if (parentPtr.children[bestIndex].n > parentPtr.children[bestIndex].t) {
                    //long lowKey = parentPtr.children[bestIndex].keys[0];
                    //if there is enough entries to ensure t entries are left (after deletion/redis.)
                    // 1st, actually delete the value
                    nodePtr.deleteEntry(studentId);//removed the entry, t - 1 entries now
                    //REDISTRIBUTE evenly between sibling and leaf node (nodePtr)
                    redistributeLeafNodes(nodePtr,parentPtr.children[bestIndex],parentPtr, right);
                    // find entry to replace original index in parent
                    // replace key value in parent entry by new low-key value in M
                    long keyToParent = -1;
                    int keyIndex = -1;
                    if(right){
                        //Key to parent is found in the right child
                        keyToParent = parentPtr.children[rightIndex].keys[0];
                        keyIndex = rightIndex - 1;
                    }else {
                        //Key to parent is found in the current child
                        keyToParent = parentPtr.children[currIndex].keys[0];
                        keyIndex = leftIndex;
                    }
                    System.out.print("Key to parent: " + keyToParent +  ".\nKey index: "+ keyIndex + ".\n");
                    parentPtr.keys[keyIndex] = keyToParent;
                    //Set oldChildNode to null and return (according to algorithm)
                    oldChildNode = null;
                    return true;
                } else {//REDISTRIBUTION NOT POSSIBLE -> MERGE
                    int deleteKeyIndex = -1;
                    int oldChildIndex = -1;

                    // 1st, actually remove entry
                    nodePtr.deleteEntry(studentId);//removed the entry, t - 1 entries now
                    // Assign OldChildEntry, oldChildEntry = &(current entry in parent for M, M is node on RHS)
                    // IF THERE EXISTS A RIGHT SIBLING
                    if(right){
                        //TODO MAKE SURE THIS OLDCHILDNODE REFERENCE IS USED TO ACTUALLY DELETE FROM BTREE
                        //Choose the right sibling to MERGE/BE REMOVED
                        oldChildIndex = currIndex + 1;
                    }else{
                        //Choose the leaf to MERGE/BE REMOVED
                        oldChildIndex = currIndex;
                    }
                    //Assign the node
                    oldChildNode = parentPtr.children[oldChildIndex];//always assigned to RHS, M

                    //move all entries from M (whichever node is on right hand side) to sibling node (could be
                    //either the original leaf or the sibling)
                    if(right){//Merge into the right side-child, which is the leaf
                        mergeLeaf(nodePtr,nodePtr.children[oldChildIndex]);
                    }else{//Merge into the left-side child, which is not the original leaf
                        mergeLeaf(nodePtr.children[currIndex - 1],nodePtr);
                    }

                    //Find index to delete
                    if(right){//matching up the Key index that sits between the two pointers to children
                        deleteKeyIndex = currIndex;
                    }else{
                        deleteKeyIndex = oldChildIndex;
                    }

                    //Delete from parent keys the key index
                    parentPtr.deleteInnerKey(deleteKeyIndex);


                    //UPDATE CHILDREN POINTERS (SHIFT DOWN)
                    //delete In child array @ oldChildIndex and shift down from there
                    parentPtr.deleteChild(oldChildIndex);

                    //TODO Update sibling pointers


                    //TODO discard empty node M, adjust sibling pointers, return;
                    return true;
                }

            }
        }
        //lies outside all if/else statements
        return false;
    }


    void removeChildNodeFromParent(BTreeNode oldChildNode, BTreeNode parent){
        for(int i = 0; i < parent.children.length; i++){
            if(parent.children[i].equals(oldChildNode)){
                parent.children[i] = null;
                //if
            }
        }
    }
    //HAVING THE CURRINDEX MOSTLY TAKES CARE OF THIS (we can just do parentNode.children[currentIndex] to get this reference
    BTreeNode findParentPtrToChild(BTreeNode child, BTreeNode parentNode){
        for(int i = 0; i < parentNode.children.length; i++){
            if(parentNode.children[i].equals(child)){
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
    void redistributeLeafNodes(BTreeNode leaf, BTreeNode sibling, BTreeNode parent, boolean right){
        //redistribute evenly between the node and its sibling
        int numTransferred = (sibling.n - leaf.n)/2;// Num keys moving to to left side (leaf) from sibling
        //will get the leaf node to t nodes at very minimum, or higher based on # in sibling

        long[] tempKeys = new long[numTransferred];
        long[] tempVals = new long[numTransferred];


        if(right){
            for(int i = 0; i < numTransferred; i++){
                tempKeys[i] = sibling.keys[i];
                System.out.println("Key: " + tempKeys[i]);
                tempVals[i] = sibling.values[i];
                System.out.println("Key: " + tempKeys[i]);
            }
        }else{
            int j = 0;
            for(int i = sibling.n - 1; i > sibling.n - 1 - numTransferred; i--){
                tempKeys[j] = sibling.keys[i];
                System.out.println("Key: " + tempKeys[j]);
                tempVals[j] = sibling.values[i];
                System.out.println("Value: " + tempVals[j]);
                j++;
            }
        }


        for(int i = 0; i < tempKeys.length; i++){
            //Insert key-value pair from sibling side to leaf
            leaf.insertEntry(tempKeys[i],tempVals[i]);

            //Now delete entry from Sibling
            sibling.deleteEntry(tempKeys[i]);
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
     * Job is to simply transfer all entries form M to leaf
     * @param leaf
     * @param M
     * @return
     */
    void mergeLeaf(BTreeNode leaf, BTreeNode M){
        // In this specific case, M, the node on the RHS of the leaf, is also the sibling
        // We don't want to use the sibling pointer however, because the pointer isn't coming from the parent
        // but from the leaf

        long[] transKeys = new long[M.n];
        long[] transVals = new long[M.n];
        //Store M values
        for(int i = 0; i < M.n; i++){
            transKeys[i] = M.keys[i];
            transVals[i] = M.values[i];
        }
        //Insert into leaf
        for(int i = 0; i < M.n; i++){
            leaf.insertEntry(M.keys[i], M.values[i]);
        }

        //TODO Remove from M? Or is M just trash collected
        return;
    }


    /**
     *
     * @param parentPtr
     * @param nodePtr
     * @param right - if true, pick the right sibling, if false, left. Will return -1 if there is no right/left sibling
     * @return
     */
    int findSiblingPtrFromParent(BTreeNode parentPtr, BTreeNode nodePtr, boolean right){
        int indexNode = 0;
        //Sibling should be one to the right in the children array
        for(int i = 0; i < parentPtr.n + 1; i++){
            if(parentPtr.children[i].equals(nodePtr)){
                indexNode = i;//returns the right-hand-side sibling of the current node
            }
        }
        if((indexNode == 0 && !right) || (indexNode == parentPtr.n && right)){
            return -1;
        }
        if(right){
            return indexNode + 1;
        }
        return indexNode - 1;
    }

    /**
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

        int height = 1;
        for(int i = 0; i<this.root.n;i++) {
            System.out.println("Key: "+this.root.keys[i]+" Value: "+this.root.values[i]);
        }
        /** 
        //find the leftmost leaf node
             System.out.println("----------NEW LEVEL----------");
        
            for(int k = 0; k<=current.n;k++) {
                BTreeNode current2= current.children[k];      
                System.out.println("New node size: "+current2.n);
                for(int j = 0; j<current2.n;j++) {
                    System.out.println("Key: "+current2.keys[j]+" Value: "+current2.values[j]);
                }
            }

        BTreeNode oldCurrent = current;

        
        if(!current.children[0].children[0].leaf) {
            System.out.println("------------NEW LEVEL------------");
            for(int z=0;z<=oldCurrent.n;z++) {
                current = oldCurrent.children[z];
                for(int k = 0; k<=current.n;k++) {
                    BTreeNode current2= current.children[k];      
                    System.out.println("New node size: "+current2.n);
                    for(int j = 0; j<current2.n;j++) {
                        System.out.println("Key: "+current2.keys[j]+" Value: "+current2.values[j]);
                    }
                }
            }
        }
    
        */
            
        
        current = this.root;

        while(!current.leaf) {
            current=current.children[0];
            height++;
        }
        System.out.println("Tree height:"+height);
        System.out.println("-------NEW LEVEL------");
        //print key and value in order, also add values to array list
        while(current!=null) {
            System.out.println("New node");
            for(int i = 0; i<current.n; i++) {
                System.out.println("Key: "+current.keys[i]+" Value:"+current.values[i]+" Num Keys:"+current.n);
                listOfRecordID.add(current.values[i]);
            }
            //use pointer to next leaf node
            current = current.next;
        }

        return listOfRecordID;
    }
}
