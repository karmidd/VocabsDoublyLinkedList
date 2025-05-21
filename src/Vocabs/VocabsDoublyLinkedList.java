// -----------------------------------------------------
// Assignment #3
// Written by: Karam Midani 40277218
// -----------------------------------------------------
package Vocabs;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to create doubly linked lists that contain vocab objects in their nodes.
 * COMP249 Assignment #3
 * @author Karam Midani
 */
public class VocabsDoublyLinkedList {
    private Node head;
    private Node tail;
    private int linkCounter;

    /**
     * default constructor
     */
    public VocabsDoublyLinkedList(){
        head = null;
        tail = null;
        linkCounter = 0;
    }

    /**
     * returns an arraylist of all words from all nodes that start with a specific letter
     * @param firstLetter chosen first letter to look for
     * @return arraylist of all words that start with the chosen first letter, sorted alphabetically
     */
    public ArrayList<String> extractFirstLetterWordsToArrayList(char firstLetter){
        firstLetter = Character.toLowerCase(firstLetter);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 1; i <= linkCounter; i++) {
            ArrayList<String> arrayList2 = getVocab(i).extractVocabToArrayList(firstLetter);
            arrayList.addAll(arrayList2);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /**
     * chekcs if a specific topic exists
     * @param topic chosen topic to look for
     * @return boolean that represents existence of chosen topic
     */
    public boolean doesTopicExist(String topic){
        if(head == null)
            return false;
        else{
            Node position = head;
            if(position.vocab.getTopic().equalsIgnoreCase(topic))
                return true;
            else{
                while(position != null && !position.vocab.getTopic().equalsIgnoreCase(topic)){
                    position = position.linkForwards;
                }
                return position != null && position.vocab.getTopic().equalsIgnoreCase(topic);
            }
        }
    }

    /**
     * add node at head
     * @param vocab vocab object
     */
    public void addAtHead(Vocab vocab){
        Node temp = head;
        head = new Node(vocab, null,head);
        if(temp != null)
            temp.linkBackwards = head;
        if(tail == null)
            tail = head;
        linkCounter++;
    }

    /**
     * add node at tail
     * @param vocab vocab object
     */
    public void addAtTail(Vocab vocab){
        if(head == null) {
            addAtHead(vocab);
            return;
        }
        tail.linkForwards = new Node(vocab, tail,null);
        tail = tail.linkForwards;
        linkCounter++;
    }

    /**
     * add node after specific node chosen (if it exists)
     * @param refVocab data of node chosen
     * @param newVocab data of new node
     */
    public void addAfter(Vocab refVocab, Vocab newVocab){
        if(head == null){
            System.out.println("List of vocabs is empty.");
            return;
        }
        Node position = head;
        while(position != null && !position.vocab.equals(refVocab)){
            position = position.linkForwards;
        }
        if(position == null){
            System.out.println(refVocab + " was not found in the list of vocabs.");
            return;
        }
        if(position.linkForwards == null) {
            addAtTail(newVocab);
        }
        else {
            Node temp = position.linkForwards;
            position.linkForwards = new Node(newVocab, position, position.linkForwards);
            temp.linkBackwards = position.linkForwards;
            linkCounter++;
        }
    }
    /**
     * add node before specific node chosen (if it exists)
     * @param refVocab data of node chosen
     * @param newVocab data of new node
     */
    public void addBefore(Vocab refVocab, Vocab newVocab){
        if(head == null){
            System.out.println("List of vocabs is empty.");
            return;
        }
        Node position = head;
        if(position.vocab.equals(refVocab)){
            addAtHead(newVocab);
            return;
        }
        while(position.linkForwards != null && !position.linkForwards.vocab.equals(refVocab)){
            position = position.linkForwards;
        }
        if(position.linkForwards == null && !position.vocab.equals(refVocab)){
            System.out.println(refVocab + " was not found in the list of vocabs.");
        }
        else{
            addAfter(position.vocab, newVocab);
        }
    }

    /**
     * remove specific node (if it exists)
     * @param refVocab data of node to be deleted
     */
    public void removeNode(Vocab refVocab){
        if(head == null){
            System.out.println("List of vocabs is empty.");
            return;
        }
        Node position = head;
        if(position.vocab.equals(refVocab)){
            Node temp = head;
            head = head.linkForwards;
            linkCounter--;
            if(head == null){
                tail = null;
            }
            temp.linkForwards = null;
            return;
        }
        while(position.linkForwards != null && !position.linkForwards.vocab.equals(refVocab)){
            position = position.linkForwards;
        }
        if(position.linkForwards == null && !position.vocab.equals(refVocab)){
            System.out.println(refVocab + " was not found in the list of vocabs.");
        }
        else if(position.linkForwards == null && position.vocab.equals(refVocab)){
            head = null;
            tail = null;
            linkCounter--;
        }
        else{
            Node temp = position.linkForwards;
            position.linkForwards = position.linkForwards.linkForwards;
            temp.linkForwards = null;
            temp.linkBackwards = null;
            if(position.linkForwards == null)
                tail = position;
            linkCounter--;
        }
    }

    /**
     * get size of linked list
     * @return counter of nodes
     */
    public int getSize() {
        return linkCounter;
    }

    /**
     * get vocab object of node
     * @param index int index of node
     * @return vocab object
     */
    public Vocab getVocab(int index){
        Node position = head;
        int counter = 1;
        if (position == null)
            return null;
        else{
            while(position.linkForwards != null && counter != index){
                position = position.linkForwards;
                counter++;
            }
            return position.vocab;
        }
    }

    /**
     * inner class of node, contains data of vocab object, and 2 more nodes for backwards and forwards nodes
     */
    private class Node{
        private Vocab vocab;
        private Node linkForwards;
        private Node linkBackwards;

        /**
         * parameterized constructor that initializes all variables
         * @param vocab data of vocab object
         * @param back where backwards node will point to
         * @param forw where forwards node will point to
         */
        private Node(Vocab vocab, Node back, Node forw){
            this.vocab = vocab;
            this.linkForwards = forw;
            this.linkBackwards = back;
        }
    }
}
