import java.util.Random;
public class Magpie
{
  public String getGreeting() //Gives a default greeting
  {
    return "Hello, let's talk.";
  }
  
  public String getResponse(String statementIn) // Gives return statement to user input
  {
    String response = "";
    String statement = statementIn.toLowerCase(); // Makes string to lower case
    statement = expandContraction(statement); // Expands contractions
    //Regular responses
    if (findKeyword(statement, "no") >= 0) //Changed statement.indexOf("") to findKeyword(statement, "")
    {
      response = "Why so negative?";
    }
    else if (findKeyword(statement, "mother") >= 0 || findKeyword(statement, "father") >= 0 || findKeyword(statement, "sister") >= 0 || findKeyword(statement, "brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if(findKeyword(statement, "cat") >= 0 || findKeyword(statement, "dog") >= 0 || findKeyword(statement, "pet") >= 0 || findKeyword(statement, "fish") >= 0)
    {
      response = "Tell me more about your pets.";
    }
    else if(findKeyword(statement, "kiang") >= 0 || findKeyword(statement, "landgraf") >= 0)
    {
      response = "I've heard about that guy! He's a pretty smart and swell person!";
    }
    else if(statement.trim().length() < 1)
    {
      response = "Say somthing, please";
    }
    else if(findKeyword(statement, "hungry") >= 0 || findKeyword(statement, "food") >= 0)
    {
      response = "What foods do you enjoy?";
    }
    else if(findKeyword(statement, "school") >= 0 || findKeyword(statement, "grades") >= 0 || findKeyword(statement, "gpa") >= 0)
    {
      response = "Sounds difficult. It'll all be worth it in the end, though!";
    }
    else if(findKeyword(statement, "video games") >= 0 || findKeyword(statement, "games") >= 0 || findKeyword(statement, "controller") >= 0)
    {
      response = "I enjoy playing lots of video games! I enjoy playing Osu! in particular!";
    }         
    // Transform statements
    else if (findKeyword(statement, "I want", 0) >= 0)
    {
      response = transformIWantStatement(statement);
    }
    
    else if(findKeyword(statement, "is", 0) >= 0) // Finds "is"
    {
      response = transformIsStatement(statement);
    }
     
    else if(findKeyword(statement, "are", 0) >= 0 && findKeyword(statement, "you", 0) < 0) // Finds "are" and makes sure that "you" is not in the statement
    {
      response = transformAreStatement(statement);
    }
    
    else
    { // Look for a two word (you <something> me) pattern
      int psn = findKeyword(statement, "you", 0);
      int psnI = findKeyword (statement, "I", 0); // New integer for special one
      if (psn >= 0 && findKeyword(statement, "me", psn) >= 0)
      {
        response = transformYouMeStatement(statement);
      }
      else if(psnI >= 0 && findKeyword(statement, "you", psn) >= 0) // Create special
      {
        response = transformIYouStatement(statement);
      }
      else
      {
        response = getRandomResponse();
      }
    }
    return response;
  }
  
  private String transformIWantStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "I want", 0);
    String restOfStatement = statement.substring(psn + 7).trim();
    return "Would you really be happy if you had " + restOfStatement + "?";
  }
  
  private String transformIYouStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnIWant = findKeyword (statement, "I", 0);
    int psnYou = findKeyword(statement, "you", psnIWant + 1);
    String restOfStatement = statement.substring(psnIWant + 1, psnYou).trim();
    return "Why do you " + restOfStatement + " me?";
  }
  
  private String transformYouMeStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfYou = findKeyword (statement, "you", 0); // Finds "you"
    int psnOfMe = findKeyword (statement, "me", psnOfYou + 3); // finds "me" after "you"
    String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
    return "What makes you think that I " + restOfStatement + " you?";
  }
  
  private String transformIsStatement(String statement) // Transforms statement with "is"  in it to a question asking why
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword(statement, "is", 0); // Finds "is"
    String restOfStatement = statement.substring(psn+2); // Determines the rest of the statement
    String beginningOfStatement = statement.substring(0, psn-1); // Determines what was said before the "is"
    return "Why is " + beginningOfStatement + restOfStatement + "?"; // Returns question
  }
  
  private String transformAreStatement(String statement) // transforms statement with "are" in it to question asking why
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if(lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword(statement, "are", 0); // Finds "are"
    String restOfStatement = statement.substring(psn+3); // Determines rest of statement
    String beginningOfStatement = statement.substring(0, psn-1); // Determines what was said before the "are"
    return "Why are " + beginningOfStatement + restOfStatement + "?"; // Returns question
  }
  
  private int findKeyword(String statement, String goal, int startPos)
  {
    String phrase = statement.trim(); // The only change to incorporate the startPos is in the line below
    int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos); // Refinement--make sure the goal isn't part of a word
    while (psn >= 0)
    { // Find the string of length 1 before and after the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring(psn - 1, psn).toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(psn + goal.length(),psn + goal.length() + 1).toLowerCase();
      } // If before and after aren't letters, we've found the word
      if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
            && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
      {
        return psn;
      } // The last position didn't work, so let's find the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(),psn + 1);
    }
    return -1;
  }
  private int findKeyword(String statement, String goal)
  {
    return findKeyword(statement, goal, 0);
  }
  
    //expands contractions
  private String expandContraction(String statement)
  {
    String temp = statement;
    
    //expands can't <- exception
    while (findKeyword(temp, "can't") >= 0) {
      int psn = findKeyword(temp, "can't"); //the position of the contraction
      String beginning = temp.substring(0,psn); //the piece before the contraction
      String contraction = "can't"; //the contraction
      String end = temp.substring(psn + 5); // the piece after the contraction
      contraction = contraction.substring(0,contraction.length() - 2) + "not"; //expands the contraction
      temp = beginning + contraction + end; //pieces together the new contraction
    }
    
    //expands I'm <- exception
    while (findKeyword(temp, "I'm") >= 0) {
      int psn = findKeyword(temp, "I'm"); //the position of the contraction
      String beginning = temp.substring(0,psn); //the piece before the contraction
      String contraction = "I'm"; //the contraction
      String end = temp.substring(psn + 3); // the piece after the contraction
      contraction = contraction.substring(0,contraction.length() - 2) + " am"; //expands the contraction
      temp = beginning + contraction + end; //pieces together the new contraction
    }
    
    String [] nPattern = { // the words that follow the pattern <something>n't = <something> not
      "aren't",
      "couldn't",
      "didn't",
      "doesn't",
      "don't",
      "hadn't",
      "hasn't",
      "haven't",
      "shouldn't",
      "weren't",
      "wouldn't",
      "isn't",
      "mustn't",
      "mightn't"
    };
    
    //expands all the contractions with the <something>n't pattern
    for (int i = 0; i < nPattern.length; i++) {
      while (findKeyword(temp, nPattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, nPattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + nPattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 3) + " not"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    String [] rePattern = { // the words that follow the pattern <something>'re = <something> are
      "you're",
      "we're",
      "they're"
    };
    
    //expands all the contractions with the <something>'re pattern
    for (int i = 0; i < rePattern.length; i++) {
      while (findKeyword(temp, rePattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, rePattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + rePattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 3) + " are"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    String [] sPattern = { // the words that follow the pattern <something>'s = <something> is
      "he's",
      "she's",
      "it's",
      "that's",
      "who's",
      "what's",
      "when's",
      "where's",
      "why's",
      "how's"
    };
    
    //expands all the contractions with the <something>'s pattern
    for (int i = 0; i < sPattern.length; i++) {
      while (findKeyword(temp, sPattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, sPattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + sPattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 2) + " is"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    String [] willPattern = { // the words that follow the pattern <something>'ll = <something> will
      "I'll",
      "you'll",
      "he'll",
      "she'll",
      "it'll",
      "we'll",
      "they'll",
      "that'll",
      "who'll",
      "what'll",
      "when'll",
      "where'll",
      "why'll",
      "how'll"
    };
    
    //expands all the contractions with the <something>'ll pattern
    for (int i = 0; i < willPattern.length; i++) {
      while (findKeyword(temp, willPattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, willPattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + willPattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 3) + " will"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    String [] vePattern = { // the words that follow the pattern <something>'ve = <something> have
      "I've",
      "you've",
      "we've",
      "they've",
      "would've",
      "should've",
      "could've",
      "might've",
      "must've"
    };
    
    //expands all the contractions with the <something>'ve pattern
    for (int i = 0; i < vePattern.length; i++) {
      while (findKeyword(temp, vePattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, vePattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + vePattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 3) + " have"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    return temp;
  }
  
  private String getRandomResponse ()
  {
    Random r = new Random ();
    return randomResponses [r.nextInt(randomResponses.length)];
  }
  
  private String [] randomResponses = {"Interesting, tell me more",
    "Hmmm.",
    "Do you really think so?",
    "You don't say.",
    "Sounds great.", // 4 new statements
    "Ah, I see.",
    "Good for you.",
    "Of course."
  };
}
