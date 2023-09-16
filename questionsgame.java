// Zacharia Kornas
// CSE 143 AE 
// TA: Kent Zeng
// QuestionsGame manages a game of "20 Questions" that
// tries to guess the object the user is thinking of.
import java.util.*;
import java.io.*;

public class QuestionsGame {
    private QuestionNode overallRoot;
    private Scanner console;
    
    // Constructs a new QuestionsGame, initially only
    // knowing one object, "computer".
    public QuestionsGame() {
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
    }
    
    // Reads a series of questions and answers from a file
    // to learn new objects and questions about those objects
    // Parameters:
    //    input - Scanner object used to read input from a file.
    public void read(Scanner input){
      overallRoot = readHelper(input);
    }
    
    // Private helper method to read a series of questions and
    // answers from a file to learn new objects and questions
    // about those objects.
    // Parameters:
    //    input - Scanner object used to read input from a file.
    // Returns the overallRoot of the new questions tree.
    private QuestionNode readHelper(Scanner input){
      String type = input.nextLine();
      String data = input.nextLine();
      QuestionNode root = new QuestionNode(data);
      if(type.contains("Q:")){
         root.yes = readHelper(input);
         root.no = readHelper(input);
      }
      return root;
    }
    
    // Writes all questions and objects currently known
    // by the computer to an output file.
    // Parameters: 
    //    output - PrintStream object used to print to a file.
    public void write(PrintStream output){
      write(output, overallRoot);
    }
    
    // Private helper method to write all questions
    // and objects currently known by the computer
    // to an output file.
    // Parameters:
    //    output - PrintStream object used to print to a file.
    //    root - current QuestionsNode that is being
    //           printed to the output file.
    private void write(PrintStream output, QuestionNode root){
      if(root != null){
        if(root.yes == null){
          output.println("A:");
        } else {
          output.println("Q:");
        }
        output.println(root.data);
        write(output, root.yes);
        write(output, root.no);
      }
    }
    
    // Plays one complete guessing game with the user, moving down a
    // series of questions as the user answers
    // yes or no while the computer attempts to guess the object.
    // If the computer successfully guesses the object, the computer will
    // print out a message saying so.
    // If the computer cannot guess the object, the user is prompted
    // to tell the computer what their object is and a yes or no question along with an answer
    // that can be used in the future to guess that object.
    public void askQuestions(){
      overallRoot = askQuestions(overallRoot);
    }

    // Private helper method that plays one complete guessing game with
    // the user. If the computer cannot guess the object, the user is prompted
    // to tell the computer what their object is and a yes or no identifying
    // question and its answer. The computer will then modify the tree to add in 
    // the new question and object.
    // Parameters:
    //    root - the current QuestionNode that the computer is evaluating while moving
    //           down the binary tree.
    // Returns the overallRoot of the question tree after the guessing game is complete.
    private QuestionNode askQuestions(QuestionNode root){
      if(root.no == null || root.yes == null){
        if(yesTo("Would your object happen to be " + root.data + "?")) {
          System.out.println("Great, I got it right!");
        } else {
          System.out.print("What is the name of your object? ");
          QuestionNode response = new QuestionNode(console.nextLine());
          System.out.println("Please give me a yes/no question that");
          System.out.println("distinguishes between your object");
          System.out.print("and mine--> ");
          String question = console.nextLine();
          if(yesTo("And what is the answer for your object? ")){
            root = new QuestionNode(question, response, root);
          } else {
            root = new QuestionNode(question, root, response);
          }
        }
      } else {
        if(yesTo(root.data)){
          root.yes = askQuestions(root.yes);
        } else {
          root.no = askQuestions(root.no);
        }
      }
      return root;
    }
    
    // Class that represents a single QuestionsNode in the tree
    private static class QuestionNode {
      public String data; // Data stored in this node
      public QuestionNode yes; // reference to the "yes" subtree of this node
      public QuestionNode no; // reference to the "no" subtree of this node
      
      // Constructs a leaf node with the given data
      public QuestionNode(String data){
         this(data, null, null);
      }
      
      // Constructs a leaf or branch node with the given data and links to subtrees
      public QuestionNode(String data, QuestionNode yes, QuestionNode no){
         this.data = data;
         this.yes = yes;
         this.no = no;
      }
    }

    // Do not modify this method in any way
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    private boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}
