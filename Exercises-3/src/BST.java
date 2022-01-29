import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.sound.sampled.SourceDataLine;

public class BST implements WordCounter {
    // Class for the tree node
    public class TreeNode{
        WordFreq item; // The data of each node is of type WordFreq
        TreeNode leftNode; // Pointer to the left node
        TreeNode rightNode; // Pointer to the right node
        TreeNode parentNode; // Pointer to the parent node (used for rotations)
        int subtreeSize; // Size of each subtree

        //Constructors
        public TreeNode() {}

        public TreeNode(WordFreq data) {
            this.item = data;
            this.subtreeSize = 0;
            this.leftNode = null;
            this.rightNode = null;
        }

        // Returns the data of the node
        public WordFreq getData() { return item; }
        public void setData(WordFreq data) { this.item = data; }

        // Returns the pointer to the left node
        public TreeNode left() { return leftNode; }
        public void setLeft(TreeNode data) { this.leftNode = data; } // sets the pointer of the left node to point to another node

        // Returns the pointer to the left node
        public TreeNode right() { return rightNode; }
        public void setRight(TreeNode data) { this.rightNode = data; } // sets the pointer of the left node to point to another node

        // Sets the subtree size
        public void setSubtreeSize(int size) { this.subtreeSize = size; }
        public int getSubtreeSize() { return this.subtreeSize; } // Returns the size of the subtree
        public void incSubtreeSize() { this.subtreeSize++; }
        public void decrSubtreeSize() { this.subtreeSize--; }
        // sets the pointer of the parent node
        public void setParent(TreeNode parent) { this.parentNode = parent; }
        public TreeNode getParent() { return this.parentNode; } // returns the parent node
    }

    // head is the root node of the BST
    public TreeNode head;
    private LinkedList stopWords; // Stop words will be stored in a linked list
    private static boolean updating_head = false;
    private WordFreq[] items;
    private int index = 0;
    // Constructor
    public BST() {
        this.head = null;
        stopWords = new LinkedList();
    }

    // Inserts a node to the BST
    // Insertion has a complexity of O(logN) (Average)
    @Override
    public void insert(String word) {
        head = insertHelper(head, word, null); // update the head
    }

    // Function to call recursively for inserting a node
    private TreeNode insertHelper(TreeNode node, String word, TreeNode parent) {
        // if the current node is a leaf
        if (node == null) {
            TreeNode newNode = new TreeNode(new WordFreq(word));
            newNode.setParent(parent);
            return newNode;
        }

        // increase the subtree size since the node we are adding will be in its subtree
        node.incSubtreeSize();
        // if the word we want to insert is equal to the word of the current Node
        // then just increase the occurrence of that word
        if (word.equals(node.getData().key())){
            node.getData().addOccurrence();
            // decrease the size as we won't be inserting a new node
            node.decrSubtreeSize();
            // update the size of the previous nodes
            updatePreviousSize(word);
        }


        // if the word is smaller than the current node word
        // call insert recursively for the left subtree and its left subtree will include the new node
        else if (word.compareTo(node.getData().key()) < 0) {
            TreeNode left = insertHelper(node.left(), word, node);
            node.setLeft(left);
        }
        // if the word is greater than the current node word
        // call insert recursively for the right subtree and its right subtree will include the new node
        else {
            TreeNode right = insertHelper(node.right(), word, node);
            node.setRight(right);
        }

        return node;
    }

    // while inserting we update at each level the subtree size but if we
    // add a word that is already there we will be increasing the subtree size for no reason
    // so when this function is called it will decrease the size by 1 of those nodes
    // worst case: O(N), average case: O(NlogN)
    private void updatePreviousSize(String word) {
        TreeNode currNode = head;

        while (!word.equals(currNode.getData().key())) {
            currNode.decrSubtreeSize();

            if (word.compareTo(currNode.getData().key()) < 0) currNode = currNode.left();
            else if (word.compareTo(currNode.getData().key()) > 0) currNode = currNode.right();
            else break;
        }
    }

    // Searches for a word and makes it a root if its occurrence is greater than the mean occurrences
    // This runs in O(logN) (Average)
    @Override
    public WordFreq search(String word) {
        TreeNode currNode = head;

        // Loop the BST until we find the node with value word
        while (currNode != null) {
            String currWord = currNode.getData().key();
            if (word.equals(currWord)) break; // stop the loop if we find it
            else if (word.compareTo(currWord) < 0) currNode = currNode.left(); // go to the left if it's less than the current word
            else currNode = currNode.right(); // go to the right if it's greater than
        }

        // if the currNode is null then the word we are looking for is not in the BST
        if (currNode == null) return null;
        else {
            // Set this node as root if it occurs more than the mean occurrences
            if (currNode.getData().getOccurrences() > getMeanFrequency()) {
                // Store the node we will remove from the tree to set it as root
                TreeNode nodeToRemove = currNode;
                // store the size and save it later to set it as the total size
                int size = this.getDistinctWords();
                // update the updating_head to be true so we don't decrease the size
                // of the subtree as we don't need to decrease the size of the subtree
                BST.updating_head = true;
                // remove the node
                remove(nodeToRemove.getData().key());
                BST.updating_head = false;
                // replace the root with the new node
                head = insertAsHead(head, nodeToRemove.getData());

                return head.getData();
            } else return currNode.getData();
        }
    }

    // Replaces the current head with another node
    // This runs in O(logN) (Average)
    private TreeNode insertAsHead(TreeNode node, WordFreq word) {
        // if it's an empty BST or a leaf
        if (node == null) return new TreeNode(word);
        // Rotate left if the word we want to set as root is less than the node
        if (word.key().compareTo(node.getData().key()) < 0) {
            node.setLeft(insertAsHead(node.left(), word));
            node = rotateRight(node);
        }
        // Rotate right if the word we want to set as root is greater than the node
        else {
            node.setRight(insertAsHead(node.right(), word));
            node = rotateLeft(node);
        }

        return node;
    }

    // Removes a word from the bst
    // Runs in O(logN) (Average)
    @Override
    public void remove(String word) {
        // call the recursive function
        head = removeHelper(head, word);
    }

    // Recursive function to remove a word from a node
    private TreeNode removeHelper(TreeNode node, String word) {
        // we try to remove a word that isn't in the BST
        if (node == null) return null;
        String currWord = node.getData().key();

        // decrease the size if we don't call it from the insertAsHead function
        if (!updating_head) node.decrSubtreeSize();

        // if the word we want to remove is smaller than the root it will be in the left subtree
        // so recursively remove the word from its left subtree
        if (word.compareTo(currWord) < 0) node.setLeft(removeHelper(node.left(), word));
        // if it's greater than the root then recursively remove it from the right subtree
        else if (word.compareTo(currWord) > 0) node.setRight(removeHelper(node.right(), word));
        // we found the word
        else {
            if (node.left() == null) return node.right();
            else if (node.right() == null) return node.left();

            // the current root will be the smallest number in the right subtree
            node.setData(getMinValue(node.right()));
            // remove the smallest number in the right subtree as we will have it two times
            node.setRight(removeHelper(node.right(), node.getData().key()));
        }

        return node;
    }

    // Gets the min value of a subtree which will be used to remove a node
    private WordFreq getMinValue(TreeNode node) {
        WordFreq minWord = node.getData();

        // while it has a left node
        while (node.left() != null) {
            // decrease the size of that subtree since the item we want to
            // remove is a predecessor of that node
            node.decrSubtreeSize();
            minWord = node.left().getData();
            node = node.left();
        }

        return minWord;
    }

    // Rotate left
    // Updates the subtree sizes in O(1)
    private TreeNode rotateLeft(TreeNode pivot) {
        TreeNode parent = pivot.getParent();
        TreeNode child = pivot.right();

        int size = (parent == null ? pivot.getSubtreeSize() : parent.getSubtreeSize());

        if (parent == null) head = child;
        else if (parent.left() == pivot) parent.setLeft(child);
        else parent.setRight(child);

        child.setParent(pivot.getParent());
        pivot.setParent(child);
        pivot.setRight(child.left());
        if (child.left() != null) child.left().setParent(pivot);
        child.setLeft(pivot);

        child.setSubtreeSize(size);
        int child_size = 0;
        if (child.left().left() != null) child_size += child.left().left().getSubtreeSize() + 1;
        if (child.left().right() != null) child_size += child.left().right().getSubtreeSize() + 1;

        child.left().setSubtreeSize(child_size);
        return child;
    }

    // Rotate right, runs in O(1)
    // Updates the subtree sizes in O(1)
    public TreeNode rotateRight(TreeNode pivot) {
        TreeNode parent = pivot.getParent();
        TreeNode child = pivot.left();

        int size = (parent == null ? pivot.getSubtreeSize() : parent.getSubtreeSize());

        if (parent == null) head = child;
        else if (parent.left() == pivot) parent.setLeft(child);
        else parent.setRight(child);

        child.setParent(pivot.getParent());
        pivot.setParent(child);
        pivot.setLeft(child.right());
        if (child.right() != null) child.right().setParent(pivot);
        child.setRight(pivot);

        child.setSubtreeSize(size);
        int child_size = 0;
        if (child.right().left() != null) child_size += child.right().left().getSubtreeSize() + 1;
        if (child.right().right() != null) child_size += child.right().right().getSubtreeSize() + 1;

        child.right().setSubtreeSize(child_size);
        return child;
    }

    // checks if the given ASCII code maps to a letter
    private boolean isLetter(int char_code) {
        return char_code >= 65 && (char_code <= 90 || char_code >= 97) && char_code <= 122;
    }

    // reads the data from the file
    @Override
    public void load(String filename) {
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String content;

            while ((content = in.readLine()) != null) {
                // array that has all the items in each line
                String[] words = content.split(" ");
                for (String word : words) {
                    // iterate through each character of each word
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < word.length(); i++) {
                        int char_code = word.charAt(i);
                        // if it's not a letter check if it's an apostrophe and if it is
                        // check if it's between two letter so we can keep it
                        if (!isLetter(char_code)) {
                            if (i - 1 >= 0 && i + 1 < word.length() && (int)word.charAt(i) == 39 && isLetter(word.charAt(i-1)) && isLetter(word.charAt(i+1)))
                                result.append(word.charAt(i));
                            else if (!(i == word.length() - 1 || i == 0)) {
                                result = null;
                                break;
                            }
                        }
                        // if it's a letter add it to the string builder
                        else {
                            result.append(word.charAt(i));
                        }
                    }
                    // if it's not a stop word or an empty string insert it to the BST
                    if (result != null && !stopWords.hasItem(result.toString().toLowerCase()) && result.length() > 0)
                        this.insert(result.toString().toLowerCase());
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't find file");
        }
    }

    // returns all the words with their occurrences
    // Runs in O(N)
    @Override
    public int getTotalWords() {
        return getTotalWordsHelper(head);
    }

    private int getTotalWordsHelper(TreeNode node) {
        if (node == null) return 0;
        // return the occurences of the current node + occurences of the left node + occurences of the right node
        return node.getData().getOccurrences() + getTotalWordsHelper(node.left()) + getTotalWordsHelper(node.right());
    }

    // Returns the amount of unique words in the BST (or the size of the BST)
    // Runs in O(1)
    @Override
    public int getDistinctWords() {
        return head.getSubtreeSize() + 1;
    }

    // Finds the word in the BST and returns how many times it appears in the text
    // Runs in O(logN) (Average)
    @Override
    public int getFrequency(String word) {
        WordFreq res = search(word);

        if (res == null) return 0;
        return res.getOccurrences();
    }

    // Traverses the tree to find the max value of occurrences
    // runs in O(N)
    @Override
    public WordFreq getMaximumFrequency() {
        return getMaxHelper(head, head.getData());
    }

    // Depth first search to find the max number
    private WordFreq getMaxHelper(TreeNode node, WordFreq maxNum) {
        // if it's a leaf or empty BST then return null
        if (node == null) return null;

        // if the occurrences of the current node are more than the max, change the max
        if (node.getData().getOccurrences() > maxNum.getOccurrences()) maxNum = node.getData();

        // if the current node has a left subtree, get the max of the left subtree and compare it
        // to the current max
        if (node.left() != null){
            WordFreq maxLeft = getMaxHelper(node.left(), maxNum);
            if (maxLeft.getOccurrences() > maxNum.getOccurrences()) maxNum = maxLeft;
        }
        // if the current node has a right subtree, get the max of the right subtree and compare it
        // to the current max
        if (node.right() != null) {
            WordFreq maxRight = getMaxHelper(node.right(), maxNum);
            if (maxRight.getOccurrences() > maxNum.getOccurrences()) maxNum = maxRight;
        }

        return maxNum;
    }


    // Returns the mean frequency of occurrences
    // Runs in O(N) (for the function getOccurrencesSum)
    @Override
    public double getMeanFrequency() {
        return (double)getTotalWords() / getDistinctWords();
    }

    // Add a stop word as the head of the linked list
    // Runs in O(1)
    @Override
    public void addStopWord(String word) {
        stopWords.put(word);
    }

//     Remove a stop word from the linked list
//     Runs in O(N)
    @Override
    public void removeStopWord(String word) {
        String result = stopWords.remove(word);
    }

    // Prints the tree alphabetically, in order traverse (left, root, right)
    // this is because left < root < right in a BST
    // runs in O(N)
    @Override
    public void printTreeAlphabetically(PrintStream printStream) {
        printTreeAlphabeticallyHelper(head, printStream);
        System.out.println("NULL");
    }

    // helper function to make recursive calls
    // makes an inOrder traverse which prints them in the correct order
    // Runs in O(N)
    public void printTreeAlphabeticallyHelper(TreeNode node, PrintStream printStream) {
        if (node == null) return;

        printTreeAlphabeticallyHelper(node.left(), printStream);
        System.out.print(node.getData().key() + ": " + node.getData().getOccurrences() + " -> ");
        printTreeAlphabeticallyHelper(node.right(), printStream);
    }

    // Does an inorder traverse and adds the items in the items array
    private void inOrder(TreeNode node, WordFreq[] items) {
        if (node == null) return;
        inOrder(node.left(), items);
        items[index++] = node.getData();
        inOrder(node.right(), items);
    }

    // prints the tree based on frequency of the word
    // Runs in O(N)
    @Override
    public void printTreeByFrequency(PrintStream printStream) {
        if (head == null) {
            System.out.println("NULL");
            return;
        }
        // make new array to store the items of the BST so it can be sorted
        items = new WordFreq[head.getSubtreeSize() + 1];
        inOrder(head, items);
        // Sorts the items by frequency, O(NlogN) operation
        QuickSort.quicksort(items, 0, items.length - 1);
        for (WordFreq item : items) System.out.print(item.key() + ": " + item.getOccurrences() + " -> ");
        System.out.println("NULL");
        // resets the index
        index = 0;
    }
}