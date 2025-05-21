// -----------------------------------------------------
// Assignment #3
// Written by: Karam Midani 40277218
// -----------------------------------------------------
package Vocabs;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to create vocab objects that contain a topic name (string) and a linked list of strings.
 * COMP249 Assignment #3
 * @author Karam Midani
 */
public class Vocab {
    private String topic;
    private VocabSingleLinkedList list;

    /**
     * Parameterized constructor that initializes all instance variables
     * @param topic topic name (string)
     * @param list (single linked list of strings)
     */
    public Vocab(String topic, VocabSingleLinkedList list){
        this.topic = topic;
        this.list = list;
    }

    /**
     * Default constructor
     */
    public Vocab() {
        topic = "";
        list = null;
    }

    /**
     * getter for list size
     * @return int that represents the list's size
     */
    public int getListSize(){
        return list.getSize();
    }

    /**
     * getter for topic name
     * @return string that represents the topic's name
     */
    public String getTopic() {
        return topic;
    }

    /**
     * getter for vocab strings in list
     * @param index int index of nodes in list
     * @return string of vocab in linked list
     */
    public String getVocabString(int index){
        return list.extractVocab(index);
    }

    /**
     * adds vocab string by creating new node in linked list
     * @param vocab string word
     * @return boolean that represents if the action was don't successfully or not
     */
    public boolean addVocabString(String vocab){
        if(!doesVocabExist(vocab.toLowerCase())) {
            list.addAtTail(vocab);
            return true;
        }
        return false;
    }

    /**
     * removes vocab string by deleting a node in linked list
     * @param vocab string word
     * @return boolean that represents if the action was don't successfully or not
     */
    public boolean removeVocabString(String vocab){
        if(doesVocabExist(vocab.toLowerCase())) {
            list.removeNode(vocab);
            return true;
        }
        return false;
    }

    /**
     * changes a chosen vocab string to a new vocab string
     * @param refVocab vocab string that needs to be changed
     * @param newVocab new vocab string
     * @return boolean that represents if the action was don't successfully or not
     */
    public boolean changeVocabString(String refVocab, String newVocab){
        if(doesVocabExist(refVocab.toLowerCase())) {
            list.setVocab(refVocab, newVocab);
            return true;
        }
        return false;
    }

    /**
     * checks list for strings that start with a specific letter, if the string exists, it will be added to an arraylist
     * @param firstLetter first letter that will be checked in the list
     * @return arraylist of strings that begin with the char parameter, sorted by alphabetically
     */
    public ArrayList<String> extractVocabToArrayList(char firstLetter){
        int listSize = list.getSize();
        firstLetter = Character.toLowerCase(firstLetter);
        ArrayList<String> arrayList = new ArrayList<>(listSize);
        for (int i = 1; i <= listSize; i++) {
            char letter = Character.toLowerCase(list.extractVocab(i).charAt(0));
            if(letter == firstLetter)
                arrayList.add(list.extractVocab(i).toLowerCase());
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /**
     * checks list for the existence of a specific string
     * @param vocab string vocab to look for
     * @return boolean that represents the existence of the vocab string parameter
     */
    public boolean doesVocabExist(String vocab){
        ArrayList<String> vocabs = extractVocabToArrayList(vocab.charAt(0));
        return vocabs.contains(vocab);
    }

    /**
     * overriding toString method of vocab class
     * @return string representation of vocab objects
     */
    @Override
    public String toString(){
        String topicString = "Topic: " + topic + "\n";
        String vocabString = "";
        int counter = 0;
        for (int i = 1; i <= list.getSize(); i++) {
            vocabString = vocabString + i + ": " + list.extractVocab(i) + "\t\t";
            counter++;
            if(counter == 4){
                vocabString = vocabString + "\n";
                counter = 0;
            }
        }
        return topicString + vocabString;
    }
    /**
     * overriding equals method of vocab class
     * @return boolean representation of equality between two vocab objects
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj.getClass() != getClass())
            return false;
        Vocab otherVocab = (Vocab) obj;
        return (topic.equalsIgnoreCase(otherVocab.topic));
    }

    /**
     * This inner class is used to create linked list that contains a vocab (string) and nodes for head and tail.
     * COMP249 Assignment #3
     * @author Karam Midani
     */
    static class VocabSingleLinkedList {
        private Node head;
        private Node tail;
        private int linkCounter;

        /**
         * default constructor that initializes everything to a default value
         */
        public VocabSingleLinkedList(){
            head = null;
            tail = null;
            linkCounter = 0;
        }

        /**
         * adds node at head
         * @param vocab string vocab
         */
        public void addAtHead(String vocab){
            head = new Node(vocab, head);
            if(tail == null)
                tail = head;
            linkCounter++;
        }
        /**
         * adds node at tail
         * @param vocab string vocab
         */
        public void addAtTail(String vocab){
            if(head == null) {
                addAtHead(vocab);
                return;
            }
            tail.link = new Node(vocab, null);
            tail = tail.link;
            linkCounter++;
        }

        /**
         * adds node after a specific node (if it exists)
         * @param refVocab vocab that will serve as a reference to the node before the new node
         * @param newVocab new vocab that will be in the new node
         */
        public void addAfter(String refVocab, String newVocab){
            if(head == null){
                System.out.println("List of vocabs is empty.");
                return;
            }
            Node position = head;
            while(position != null && !position.vocab.equalsIgnoreCase(refVocab)){
                position = position.link;
            }
            if(position == null){
                System.out.println(refVocab + " was not found in the list of vocabs.");
                return;
            }
            if(position.link == null) {
                addAtTail(newVocab);
            }
            else {
                position.link = new Node(newVocab, position.link);
                linkCounter++;
            }
        }
        /**
         * adds node before a specific node (if it exists)
         * @param refVocab vocab that will serve as a reference to the node after the new node
         * @param newVocab new vocab that will be in the new node
         */
        public void addBefore(String refVocab, String newVocab){
            if(head == null){
                System.out.println("List of vocabs is empty.");
                return;
            }
            Node position = head;
            if(position.vocab.equalsIgnoreCase(refVocab)){
                addAtHead(newVocab);
                return;
            }
            while(position.link != null && !position.link.vocab.equalsIgnoreCase(refVocab)){
                position = position.link;
            }
            if(position.link == null && !position.vocab.equalsIgnoreCase(refVocab)){
                System.out.println(refVocab + " was not found in the list of vocabs.");
            }
            else{
                addAfter(position.vocab, newVocab);
            }
        }

        /**
         * removes a specific node (if it exists)
         * @param refVocab vocab that will serve as a reference to the node that needs to be deleted
         */
        public void removeNode(String refVocab){
            if(head == null){
                System.out.println("List of vocabs is empty.");
                return;
            }
            Node position = head;
            if(position.vocab.equalsIgnoreCase(refVocab)){
                Node temp = head;
                head = head.link;
                linkCounter--;
                if(head == null){
                    tail = null;
                }
                temp.link = null;
                return;
            }
            while(position.link != null && !position.link.vocab.equalsIgnoreCase(refVocab)){
                position = position.link;
            }
            if(position.link == null && !position.vocab.equalsIgnoreCase(refVocab)){
                System.out.println(refVocab + " was not found in the list of vocabs.");
            }
            else if(position.link == null && position.vocab.equalsIgnoreCase(refVocab)){
                head = null;
                tail = null;
                linkCounter--;
            }
            else{
                position.link = position.link.link;
                linkCounter--;
            }
        }

        /**
         * changes chosen vocab string (if it exists) to a new vocab string
         * @param refVocab chosen vocab string
         * @param newVocab new vocab string
         */
        public void setVocab(String refVocab, String newVocab){
            if(head == null){
                System.out.println("List of vocabs is empty.");
                return;
            }
            Node position = head;
            if(position.vocab.equalsIgnoreCase(refVocab)){
                position.vocab = newVocab;
                return;
            }
            while(position != null && !position.vocab.equalsIgnoreCase(refVocab)){
                position = position.link;
            }
            if(position == null){
                System.out.println(refVocab + " was not found in the list of vocabs.");
            }
            else {
                position.vocab = newVocab;
            }
        }

        /**
         * getter for link count of nodes
         * @return
         */
        public int getSize(){
            return linkCounter;
        }

        /**
         * display method for the list
         */
        public void display(){
            if(head == null){
                System.out.println("List of vocabs is empty.");
                return;
            }
            System.out.println("Here are your " + linkCounter + " vocabs from the list: ");
            Node position = head;
            while (position != null) {
                System.out.println(position.vocab);
                position = position.link;
            }
        }

        /**
         * gets vocab string from specific node
         * @param index chosen node index
         * @return string from chosen node
         */
        public String extractVocab(int index){
            if(linkCounter == 0){
                return "EMPTY";
            }
            String vocab = "";
            Node position = head;
            for (int i = 1; i <= linkCounter; i++) {
                if(i == index){
                    vocab = position.vocab;
                    break;
                }
                position = position.link;
            }
            return vocab;
        }

        /**
         * inner inner class for nodes
         * contains data (string) and another node reference
         */
        private class Node{
            private String vocab;
            private Node link;

            /**
             * param constructor
             * @param vocab string
             * @param link node reference for next node
             */
            private Node(String vocab, Node link){
                this.vocab = vocab;
                this.link = link;
            }
        }
    }
}
