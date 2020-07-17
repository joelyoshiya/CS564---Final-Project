class BTreeNode {

    /**
     * Array of the keys stored in the node.
     */
    long[] keys;
    /**
     * Array of the values[recordID] stored in the node. This will only be filled when the node is a leaf node.
     */
    long[] values;
    /**
     * Minimum degree (defines the range for number of keys)
     **/
    int t;
    /**
     * Pointers to the children, if this node is not a leaf.  If
     * this node is a leaf, then null.
     */
    BTreeNode[] children;
    /**
     * number of key-value pairs in the B-tree
     */
    int n;
    /**
     * true when node is leaf. Otherwise false
     */
    boolean leaf;

    /**
     * point to other next node when it is a leaf node. Otherwise null
     */
    BTreeNode next;
    // this three vars are going to be null for the majority of there lives
    // they will be filled with the right side of the keys/children/values
    // for one step and then returned to null
    // made them private to not change the IO of the BTREE NODE
    private long[] subkeys;
    private long[] subvalues;
    private BTreeNode[] subchildren;
    private long splitvalue;
    // Constructor
    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new long[2 * t];
        this.children = new BTreeNode[2 * t+1];
        this.n = 0;
        this.next = null;
        this.values = new long[2 * t];
        
    }
    //this is when the node is a leaf and a student needs to be placed in
    // if the leaf is full then it calls anouther method but if itsn not then it shifts the keys
    // and values to a proper location and inserts the new keys and values
    // @ parm - Student student- this is the student that needs to be placed into the tree 
    // return -2 if the leaf is full
    // return 1 if the student was palced in the leaf
    int placeinleaf(Student student) {
    	n++;
    	if(n>2*t) {
    		leafsplitter(student);
    		return -2;
    	}else {
    		
    		// this one is just going to find the right place and stick it in
    		int position=0;
    		for(int i=0; i<n;i++) {
    			if(student.studentId<keys[i]|| keys[i]==0) {
    				position =i;
    				i=n;
    			}
    		}
    			keys = shifter(student.studentId,position,keys);
        		values = shifter(student.recordId,position,values);
    		return 1;
    	}
    
    }
    // very bassic shifter method used to movie the value after and index one over so a new value can be placed in
    // @ parm long value- this is the value trying to be placed in 
    // @ parm int index - this is the postion it needs to be placed in
    // @ parm long[] array- this is the rest of the values that need to be orderd allready in order
    // return long[]- this is the array with all values in the proper position
    private long[] shifter(long value, int index, long[] array) {
    	long[] temp = array.clone();
    	for(int i=index; i<n-1;i++) {
    		temp[i+1] = array[i];
    	}
    	temp[index] = value;
    	return temp;
    }
    /**
	 * Does similar action of shifter, but in this case removes an entry and shifts values downwards
	 * Leaves a default 0 (long value) in place at the end of the array, given that a value has been deleted
		 * @param index
	 * @param array
	 * @return
	 */
	private long[] downShifter(int index, long[] array) {
		long[] temp = array.clone();

		for(int i=index; i<n-1;i++) {
			temp[i]  = array[i + 1];
		}
		temp[n - 1] = 0;//Set the left-over index value from deleting one entry to the default long value

		return temp;
	}
    // this method is called when the node is a leaf and it is full
    // it goes by creating a larger array to place the student in its proper location and then it is split
    // with the right(larger) side takeing one more than the left side and then palces them in two 
    // array for a breif moment where its parrent will grab them
    // @ parm Student student- this is the object attempting to be palce inside the leaf node
    void leafsplitter(Student student){
    	// this are what is going to hold the right side after the split 
    	// and then when they are taken they will return to null
    	// so it doesnt take any exstra space
    	subkeys = new long[2 * t];
    	subvalues = new long[2 * t];
    	// this is so we can sort out where this new value fits
    	long[] tempsubkeys = new long[2 * t+1];
    	long[] tempsubvalues = new long[2 * t+1];
    	int position=-1;
    	for(int i=0; i<keys.length;i++) {
		    if(keys[i]<student.studentId) {
    			position =i;
    			tempsubkeys[i]=keys[i];
    			tempsubvalues[i]=values[i];
    		}else {
    			tempsubkeys[i+1]=keys[i];
    			tempsubvalues[i+1]=values[i];
    		}
    	}
    	if(position ==-1) {
    		tempsubkeys[0]=student.studentId;
        	tempsubvalues[0] = student.recordId;
    	}else {
    		tempsubkeys[position+1]=student.studentId;
        	tempsubvalues[position+1] = student.recordId;
    	}
    
    	keys = new long[2 * t];
		values = new long[2 * t];
    	for(int i=0; i<=(n)/2;i++) {
    		if(i<=n/2-1) {
    		keys[i] = tempsubkeys[i];
			values[i]= tempsubvalues[i];
    		}
			
			subkeys [i] = tempsubkeys[(n)/2+i];
			subvalues[i] = tempsubvalues[(n)/2+i];
			
    	}
    	splitvalue = subkeys[0];
    	n=t;// the node is now smaller so remeber to change n
    }
    // this is the method for when the node not a leaf and needs to have a hild placed into it
    // it attempts to place the child in the correct location but if it is full of children then it need to 
    // split
    // @ parm BTreeNode child- this is the child that is trying to be placed into the children array
    // return -1 when it is full
    // return 1 when the child was placed in
    int placeinchild(BTreeNode child, long key){
    	n++;
    	
    	if(n>2*t) {
    		splitnode(child, key);
    		return -1;
    	}else {
    		// searchers for proper location
    		int position =0;
    		long[] tempkeys = new long[2*t];
    		BTreeNode[] tempchildren = new BTreeNode[2 * t+1];
    		for(int i=0; i<n;i++) {
    			if(keys[i]<key && keys[i]!=0) {
    				position =i+1;
    					
    			}
    		}
//I can use the shifter to make the key change place but i need do one for the children
    		// also the children i guess do not technilly need to be in order bbecuase all the leaves have pointers to each 
    		// other. However, i knew how to do it and it will help joel with delete
    		keys = shifter(key,position,keys);
    		for(int i=0; i<n;i++) {
    			if(i<=position) {
    				tempchildren[i] = children[i];
    			}else {
    				tempchildren[i+1] = 
    						children[i];
    			}
    		}
    		tempchildren[position+1]=child;
    		if(position == n) {
    			tempchildren[position] = children[position];
    		}
    		for(int i=0; i<n;i++) {
    			tempchildren[i].next= tempchildren[i+1];
        	}
    		children = tempchildren;
    		
    		return 1;
    	}
    	
    }
    // this is going to run the same process as splitting the leafs except it when a nodes needs to be split 
    // it creates a larger array of keys and children to place the cild in its proper location
    // then it splits into two with the right being one larger than the left for a short time before its parrent can retreve it
    // @ parm BTreeNode child- this is the child that is trying to be place in the tree
    private void splitnode(BTreeNode child, long key) {
    	subchildren = new BTreeNode[2 * t+1];
    	subkeys = new long[2 * t];
    	
    	// this is to store the values and order them better
    	long[] tempsubkeys = new long[2 * t+1];
    	BTreeNode[] tempsubchildren= new BTreeNode[2 * t+2];
    	
    	// now time to place it in the list
    	int position =-1;
    	for(int i=0; i<n-1;i++) {
			if(keys[i]<key) {
				position =i;
					
			}
		}
    	// this shifts the key
    	for(int i=0; i<n-1;i++) {
    		if(i<position+1) {
    			tempsubkeys[i] = keys[i];
    		}else {
    			tempsubkeys[i+1] = keys[i];
    		}
    	}
    	tempsubkeys[position+1] = key;
    	
    	// this is going to shift the children
    	for(int i=0; i<n; i++) {
    		
    		if(i<=position+1) {
    			tempsubchildren[i] = children[i];
    		}else {
    			tempsubchildren[i+1] = children[i];
    		}
    		tempsubchildren[position+2] = child;
    	}
    	// this is not nesscary but it is just to cover the baises 
    	for(int i=0; i<tempsubchildren.length-1;i++) {
    		
    		tempsubchildren[i].next= tempsubchildren[i+1];
    	
    	}
		// clear the keys and the children
    	keys = new long[2 * t];
    	children = new BTreeNode[2 * t+1];
    	
    	
		// now we will split the children and keys into two nodes
    	for(int i=0; i<=t; i++) {
    		if(i<t) {
    			keys[i] = tempsubkeys[i];
    			subkeys[i] = tempsubkeys[t+i+1];
    		}
    		children[i] = tempsubchildren[i];
    		
    		subchildren[i] = tempsubchildren[i+t+1];
    		
    	}
    	splitvalue = tempsubkeys[t];
    	n=t;
	
    }
    // basic getter function
    // returns long[]- this is the right side of the keys
    long[] getsubkeys() {
    	return subkeys;
    }
    // basic getter function
    // returns  BTreeNode[]- this is the right side of the children
    BTreeNode[] getsubchildren() {
    	return subchildren;
    }
    // basic getter function
    // returns  long[]- this is the right side of the values
    long[] getsubvalues() {
    	return subvalues;
    }
    // basic getter function
    // return long - this is the value that will be middle key 
    long getsplitvalue() {
    	return splitvalue;
    }
    // no reason to keep space allocated for a ling time so 
    // clear them when not needed 
    void clear() {
    	subkeys=null;
    	subvalues = null;
    	subchildren = null;
    }
    /**
	 * Deletes the matching studentID/Key from both the keys and values
	 * @param key
	 */
	void deleteLeafEntry(long key){
    	int removeIndex = -1;

    	for(int i = 0; i < this.n; i++){
    		if(keys[i] == key){
				removeIndex = i;
			}
		}

    	//effectively removes value at found index by shifting down in place at the removeIndex
    	keys = downShifter(removeIndex,this.keys);
    	values = downShifter(removeIndex, this.values);
    	n--;

	}

	/**
	 * Deletes ONLY from the keys array, and the node must be a non-leaf node
	 * @param index
	 */
	void deleteInnerKey(int index){
		//check if non-leaf (inner)
		if(!leaf){
			keys = downShifter(index,this.keys);
			n--;
		}else{
			System.out.println("Calling deleteInnerKey on a leaf node!");
		}
	}

	void insertEntry(long newKey, long newValue) {

		// Find position where values will be inserted in leaf array
		int position=0;
		for(int i=0; i < n ; i++) {
			if(newKey < keys[i]) {
				position = i;
				break;
			}
			if( i == n - 1){
				if(newKey > keys[i]){
					position = i + 1;
					break;
				}
			}
		}

		//Shift array at appropriate index (position) to accommodate new values as part of redistribution
		keys = insertKey(newKey,position,keys);
		values = insertKey(newValue,position,values);
		//inserting a key-value pair exactly once, so increment n
		n++;
		return;
	}

	/**
	 * Actually insert the new value into the array
	 * @param newKey
	 * @param position
	 * @param array
	 * @return
	 */
	long[] insertKey(long newKey, int position, long[] array){
		long[] temp = array.clone();
		for(int i=position; i < n;i++) {
			temp[i+1] = array[i];
		}
		temp[position] = newKey;
		return temp;
	}

	/**
	 * Delete just a child form the children array
	 * @param deleteIndex
	 */
	void deleteChild(int deleteIndex){

		if(0 <= deleteIndex && deleteIndex < children.length){
			children[deleteIndex] = null;
			for(int i = deleteIndex; i < children.length - 1; i++){
				children[i] = children[i + 1];
			}
			children[children.length - 1] = null;//effectively keeps an empty
		}
	}
}

