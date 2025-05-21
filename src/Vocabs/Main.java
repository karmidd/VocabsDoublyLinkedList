// -----------------------------------------------------
// Assignment #3
// Written by: Karam Midani 40277218
// -----------------------------------------------------
package Vocabs;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * driver class that contains the menu of the program and all its options.
 * the program is a browsing system for certain topics and their own vocabs.
 * the user can add, remove, and modify any topic of their desire.
 * however, the program won't be usable if there isn't any data loaded from a file first.
 * meaning, the user cant add any topics if there are no preexisting topics.
 * the user can also load in all the new data they put in into a preexisting file, or a new file.
 * @author Karam Midani 40277218
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VocabsDoublyLinkedList dll = new VocabsDoublyLinkedList();
        String choice = "";
        System.out.println("Welcome to the Vocabulary Control Center, made by Karam Midani 40277218.");
        //do while loop that repeats the menu until exit option is chosen
        do{
            System.out.println("-----------------------------");
            System.out.println("\tVocabulary Control Center");
            System.out.println("-----------------------------");
            System.out.println("1\tbrowse a topic");
            System.out.println("2\tinsert a new topic before another one");
            System.out.println("3\tinsert a new topic after another one");
            System.out.println("4\tremove a topic");
            System.out.println("5\tmodify a topic");
            System.out.println("6\tsearch topics for a word");
            System.out.println("7\tload from a file");
            System.out.println("8\tshow all words starting with a given letter");
            System.out.println("9\tsave to file");
            System.out.println("0\texit");
            System.out.print("Enter your choice: ");
            choice = scanner.next();
            while(!choice.equals("0") && !choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6") && !choice.equals("7") && !choice.equals("8") && !choice.equals("9")){
                System.out.print("Invalid input, try again: ");
                choice = scanner.next();
            }
            //switch for all cases in the menu
            switch(choice){
                //browse a topic
                case "1":
                    if(dll.getSize() == 0){
                        System.out.println("There aren't any topics in the system yet.");
                        break;
                    }
                    else{
                        int case1choice1;
                        do{
                            System.out.println("-----------------------------");
                            System.out.println("\t Pick a topic");
                            System.out.println("-----------------------------");
                            for (int i = 1; i <= dll.getSize(); i++) {
                                System.out.println(i + " " + dll.getVocab(i).getTopic());
                            }
                            System.out.println("0 Exit");
                            System.out.println("-----------------------------");
                            System.out.print("Enter your choice: ");
                            while(true) {
                                try {
                                    case1choice1 = scanner.nextInt();
                                    if (case1choice1 < 0 || case1choice1 > dll.getSize())
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers or numbers that exceed the limit are invalid values, try again: ");
                                }
                            }
                            if(case1choice1 != 0)
                                System.out.println(dll.getVocab(case1choice1));
                        }while(case1choice1 != 0);
                        break;
                    }
                //insert topic before a topic
                case "2":
                    if(dll.getSize() == 0) {
                        System.out.println("There aren't any topics in the system.");
                        break;
                    }
                    else{
                        System.out.println("-----------------------------");
                        System.out.println("\t Pick a topic");
                        System.out.println("-----------------------------");
                        for (int i = 1; i <= dll.getSize(); i++) {
                            System.out.println(i + " " + dll.getVocab(i).getTopic());
                        }
                        System.out.println("0 Exit");
                        System.out.println("-----------------------------");
                        System.out.print("Enter your choice: ");
                        int case2choice1;
                        while(true) {
                            try {
                                case2choice1 = scanner.nextInt();
                                if (case2choice1 < 0 || case2choice1 > dll.getSize())
                                    throw (new Exception());
                                break;
                            }
                            catch (InputMismatchException ime) {
                                System.out.print("Not a valid number input, try again: ");
                                scanner.next();
                            }
                            catch(Exception exception){
                                System.out.print("Negative numbers or numbers that exceed the limit are invalid values, try again: ");
                            }
                        }
                        if(case2choice1 == 0)
                            break;
                        scanner.nextLine();
                        System.out.print("Enter a topic name: ");
                        String case2choice2 = scanner.nextLine();
                        boolean topicExists = false;
                        for (int i = 1; i <= dll.getSize(); i++) {
                            if (case2choice2.equalsIgnoreCase(dll.getVocab(i).getTopic())){
                                System.out.println(case2choice2 + " already exists.");
                                topicExists = true;
                                break;
                            }
                        }
                        if(topicExists)
                            break;
                        else{
                            dll.addBefore(dll.getVocab(case2choice1),new Vocab(case2choice2, new Vocab.VocabSingleLinkedList()));
                            System.out.println("Enter a word - to quit press enter: ");
                            String case2choice3 = scanner.nextLine();
                            case2choice3 = case2choice3.replace(" ", "");
                            while(!case2choice3.isBlank()){
                                if(!dll.getVocab(case2choice1).addVocabString(case2choice3)){
                                    System.out.println(case2choice3 + " already exists.");
                                }
                                case2choice3 = scanner.nextLine();
                                case2choice3 = case2choice3.replace(" ", "");
                            }
                        }
                    }
                    break;
                //insert topic after a topic
                case "3":
                    if(dll.getSize() == 0) {
                        System.out.println("There aren't any topics in the system.");
                        break;
                    }
                    else{
                        System.out.println("-----------------------------");
                        System.out.println("\t Pick a topic");
                        System.out.println("-----------------------------");
                        for (int i = 1; i <= dll.getSize(); i++) {
                            System.out.println(i + " " + dll.getVocab(i).getTopic());
                        }
                        System.out.println("0 Exit");
                        System.out.println("-----------------------------");
                        System.out.print("Enter your choice: ");
                        int case3choice1;
                        while(true) {
                            try {
                                case3choice1 = scanner.nextInt();
                                if (case3choice1 < 0 || case3choice1 > dll.getSize())
                                    throw (new Exception());
                                break;
                            }
                            catch (InputMismatchException ime) {
                                System.out.print("Not a valid number input, try again: ");
                                scanner.next();
                            }
                            catch(Exception exception){
                                System.out.print("Negative numbers or numbers that exceed the limit are invalid values, try again: ");
                            }
                        }
                        if(case3choice1 == 0)
                            break;
                        scanner.nextLine();
                        System.out.print("Enter a topic name: ");
                        String case3choice2 = scanner.nextLine();
                        boolean topicExists = false;
                        for (int i = 1; i <= dll.getSize(); i++) {
                            if (case3choice2.equalsIgnoreCase(dll.getVocab(i).getTopic())){
                                System.out.println(case3choice2 + " already exists.");
                                topicExists = true;
                                break;
                            }
                        }
                        if(topicExists)
                            break;
                        else{
                            dll.addAfter(dll.getVocab(case3choice1),new Vocab(case3choice2, new Vocab.VocabSingleLinkedList()));
                            System.out.println("Enter a word - to quit press enter: ");
                            String case3choice3 = scanner.nextLine();
                            case3choice3 = case3choice3.replace(" ", "");
                            while(!case3choice3.isBlank()){
                                if(!dll.getVocab(case3choice1+1).addVocabString(case3choice3)){
                                    System.out.println(case3choice3 + " already exists.");
                                }
                                case3choice3 = scanner.nextLine();
                                case3choice3 = case3choice3.replace(" ", "");
                            }
                        }
                    }
                    break;
                //remove a topic
                case "4":
                    if(dll.getSize() == 0) {
                        System.out.println("There aren't any topics in the system.");
                        break;
                    }
                    else {
                        System.out.println("-----------------------------");
                        System.out.println("\t Pick a topic");
                        System.out.println("-----------------------------");
                        for (int i = 1; i <= dll.getSize(); i++) {
                            System.out.println(i + " " + dll.getVocab(i).getTopic());
                        }
                        System.out.println("0 Exit");
                        System.out.println("-----------------------------");
                        System.out.print("Enter your choice: ");
                        int case4choice1;
                        while (true) {
                            try {
                                case4choice1 = scanner.nextInt();
                                if (case4choice1 < 0 || case4choice1 > dll.getSize())
                                    throw (new Exception());
                                break;
                            } catch (InputMismatchException ime) {
                                System.out.print("Not a valid number input, try again: ");
                                scanner.next();
                            } catch (Exception exception) {
                                System.out.print("Negative numbers or numbers that exceed the limit are invalid values, try again: ");
                            }
                        }
                        if(case4choice1 == 0)
                            break;
                        dll.removeNode(dll.getVocab(case4choice1));
                    }
                    break;
                //modify a topic (add a word, remove a word, change a word)
                case "5":
                    if(dll.getSize() == 0) {
                        System.out.println("There aren't any topics in the system.");
                        break;
                    }
                    else {
                        System.out.println("-----------------------------");
                        System.out.println("\t Pick a topic");
                        System.out.println("-----------------------------");
                        for (int i = 1; i <= dll.getSize(); i++) {
                            System.out.println(i + " " + dll.getVocab(i).getTopic());
                        }
                        System.out.println("0 Exit");
                        System.out.println("-----------------------------");
                        System.out.print("Enter your choice: ");
                        int case5choice1;
                        while (true) {
                            try {
                                case5choice1 = scanner.nextInt();
                                if (case5choice1 < 0 || case5choice1 > dll.getSize())
                                    throw (new Exception());
                                break;
                            } catch (InputMismatchException ime) {
                                System.out.print("Not a valid number input, try again: ");
                                scanner.next();
                            } catch (Exception exception) {
                                System.out.print("Negative numbers or numbers that exceed the limit are invalid values, try again: ");
                            }
                        }
                        if(case5choice1 == 0)
                            break;
                        System.out.println("-----------------------------");
                        System.out.println("\tModify Topics Menu");
                        System.out.println("-----------------------------");
                        System.out.println("a\tadd a word");
                        System.out.println("r\tremove a word");
                        System.out.println("c\tchange a word");
                        System.out.println("0\texit");
                        System.out.println("-----------------------------");
                        System.out.print("Enter your choice: ");
                        String case5choice2 = scanner.next();
                        while(!case5choice2.equals("a") && !case5choice2.equals("r") && !case5choice2.equals("c") && !case5choice2.equals("0")){
                            System.out.print("Invalid input, try again: ");
                            case5choice2 = scanner.next();
                        }
                        if(case5choice2.equals("0"))
                            break;
                        else{
                            boolean c5c3IsBlank = false;
                            String case5choice3;
                            switch (case5choice2){
                                case "a":
                                    System.out.print("Type a word and press Enter, or press Enter to end input: ");
                                    scanner.nextLine();
                                    case5choice3 = scanner.nextLine();
                                    case5choice3 = case5choice3.replace(" ", "");
                                    if(case5choice3.isBlank()) {
                                        c5c3IsBlank = true;
                                        break;
                                    }
                                    else if(!dll.getVocab(case5choice1).addVocabString(case5choice3)) {
                                        System.out.println("Sorry, there is no word: " + case5choice3);
                                        break;
                                    }
                                    break;
                                case "r":
                                    System.out.print("Enter a word: ");
                                    scanner.nextLine();
                                    case5choice3 = scanner.nextLine();
                                    case5choice3 = case5choice3.replace(" ", "");
                                    if(!dll.getVocab(case5choice1).removeVocabString(case5choice3)) {
                                        System.out.println("Sorry, there is no word: " + case5choice3);
                                        break;
                                    }
                                    break;
                                case "c":
                                    System.out.print("Enter the word you want to change: ");
                                    scanner.nextLine();
                                    case5choice3 = scanner.nextLine();
                                    case5choice3 = case5choice3.replace(" ", "");
                                    System.out.print("Enter the new word: ");
                                    String case5choice4 = scanner.nextLine();
                                    case5choice4 = case5choice4.replace(" ", "");
                                    if(!dll.getVocab(case5choice1).changeVocabString(case5choice3, case5choice4)) {
                                        System.out.println("Sorry, there is no word: " + case5choice3);
                                        break;
                                    }
                                    break;
                            }
                            if(c5c3IsBlank)
                                break;
                        }
                    }
                    break;
                //search all topics for a certain word
                case "6":
                    if(dll.getSize() == 0) {
                        System.out.println("There aren't any topics in the system.");
                        break;
                    }
                    else {
                        System.out.print("Enter the word you want to search for: ");
                        scanner.nextLine();
                        String case6choice1 = scanner.nextLine();
                        case6choice1 = case6choice1.replace(" ", "");
                        for (int i = 1; i <= dll.getSize(); i++) {
                            if(dll.getVocab(i).doesVocabExist(case6choice1)){
                                System.out.println(case6choice1 + " exists in the topic " + dll.getVocab(i).getTopic());
                                break;
                            }
                            else if(i == dll.getSize()){
                                System.out.println(case6choice1 + " doesn't exist in any topic.");
                                break;
                            }
                        }
                    }
                    System.out.println("Success!");
                    break;
                //load topics from a file in the depository
                case "7":
                    System.out.print("Enter file's name in the depository : ");
                    String case7choice1 = scanner.next();
                    Scanner scan = null;
                    try{
                        scan = new Scanner(new FileInputStream(case7choice1));
                    }
                    catch(FileNotFoundException fnfe){
                        System.out.println(case7choice1 + " doesn't exist.");
                        break;
                    }
                    int counter = dll.getSize();
                    while(scan.hasNextLine()){
                        String item = scan.nextLine();
                        if(item.isBlank()){
                            continue;
                        }
                        else if(item.charAt(0) == '#' && !dll.doesTopicExist(item.substring(1))){
                            dll.addAtTail(new Vocab(item.substring(1), new Vocab.VocabSingleLinkedList()));
                            counter++;
                        }
                        else if(item.charAt(0) == '#' && dll.doesTopicExist(item.substring(1))){
                            continue;
                        }
                        else{
                            dll.getVocab(counter).addVocabString(item);
                        }
                    }
                    System.out.println("Success!");
                    break;
                //display all words that start with a certain letter
                case "8":
                    if(dll.getSize() == 0) {
                        System.out.println("There aren't any topics in the system.");
                        break;
                    }
                    else {
                        System.out.print("Enter the desired letter: ");
                        char case8choice1;
                        while (true) {
                            try {
                                case8choice1 = scanner.next().toLowerCase().charAt(0);
                                if (!Character.isLetter(case8choice1))
                                    throw (new Exception());
                                break;
                            } catch (Exception exception) {
                                System.out.print("Numbers are invalid values, try again: ");
                            }
                        }
                        ArrayList<String> arrayList = dll.extractFirstLetterWordsToArrayList(case8choice1);
                        if (arrayList.isEmpty()){
                            System.out.println("No words with the letter " + case8choice1 + " were found.");
                            break;
                        }
                        else{
                            System.out.println(arrayList);
                        }
                        System.out.println("Success!");
                        break;
                    }
                //save current topics in the system into a file
                case "9":
                    if(dll.getSize() == 0) {
                        System.out.println("There aren't any topics in the system.");
                        break;
                    }
                    else{
                        System.out.println("Would you like to save to an existing file or create a new one?");
                        System.out.print("Enter 1 for an existing file, or 2 for a new one: ");
                        String case9choice1 = scanner.next();
                        while(!case9choice1.equals("1") && !case9choice1.equals("2")){
                            System.out.print("Invalid input, try again: ");
                            case9choice1 = scanner.next();
                        }
                        if(case9choice1.equals("1")){
                            System.out.print("Enter the file's name: ");
                            String fileName = scanner.next();
                            File existingFile = new File(fileName);
                            if(!existingFile.exists()){
                                System.out.println(fileName + " doesn't exist.");
                                break;
                            }
                            else{
                                PrintWriter printer = null;
                                try{
                                    printer = new PrintWriter(new FileOutputStream(fileName));
                                }
                                catch(FileNotFoundException fnfe){
                                    System.out.println("ERROR");
                                    System.exit(0);
                                }
                                for (int i = 1; i <= dll.getSize(); i++) {
                                    printer.println("#" + dll.getVocab(i).getTopic());
                                    printer.flush();
                                    for (int j = 1; j <= dll.getVocab(i).getListSize(); j++) {
                                        printer.println(dll.getVocab(i).getVocabString(j));
                                        printer.flush();
                                    }
                                    printer.println();
                                    printer.flush();
                                }
                                break;
                            }
                        }
                        else{
                            System.out.print("Enter the name of your new file: ");
                            String fileName = scanner.next();
                            File newFile = new File(fileName);
                            if(newFile.exists()){
                                System.out.println(fileName + " already exists.");
                                break;
                            }
                            PrintWriter printer = null;
                            try{
                                printer = new PrintWriter(new FileOutputStream(fileName));
                            }
                            catch(FileNotFoundException fnfe){
                                System.out.println("ERROR");
                                System.exit(0);
                            }
                            for (int i = 1; i <= dll.getSize(); i++) {
                                printer.println("#" + dll.getVocab(i).getTopic());
                                printer.flush();
                                for (int j = 1; j <= dll.getVocab(i).getListSize(); j++) {
                                    printer.println(dll.getVocab(i).getVocabString(j));
                                    printer.flush();
                                }
                                printer.println();
                                printer.flush();
                            }
                            break;
                        }
                    }
                //exit program
                case "0":
                    System.out.println("Program will now terminate.");
                    break;
            }
        }while(!choice.equals("0"));
    }
}