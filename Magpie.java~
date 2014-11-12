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
    if (findKeyword(statement, "no") >= 0) //Changed statement.indexOf("") to findKeyword(statement, "")
    {
      response = "Why so negative?";
    }
    else if (findKeyword(statement, "mother") >= 0 
               || findKeyword(statement, "father") >= 0 
               || findKeyword(statement, "sister") >= 0 
               || findKeyword(statement, "brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if(findKeyword(statement, "cat") >= 0
              || findKeyword(statement, "dog") >= 0
              || findKeyword(statement, "pet") >= 0
              || findKeyword(statement, "fish") >= 0)
    {
      response = "Tell me more about your pets.";
    }
    else if(findKeyword(statement, "kiang") >= 0
              || findKeyword(statement, "landgraf") >= 0)
    {
      response = "I've heard about that guy! He's a pretty smart and swell person!";
    }
    else if(statement.trim().length() < 1)
    {
      response = "Say somthing, please";
    }
    else if(findKeyword(statement, "hungry") >= 0
              || findKeyword(statement, "food") >= 0)
    {
      response = "What foods do you enjoy?";
    }
    else if(findKeyword(statement, "school") >= 0
              || findKeyword(statement, "grades") >= 0
              || findKeyword(statement, "gpa") >= 0)
    {
      response = "Sounds difficult. It'll all be worth it in the end, though!";
    }
    else if(findKeyword(statement, "video games") >= 0
              || findKeyword(statement, "games") >= 0
              || findKeyword(statement, "controller") >= 0)
    {
      response = "I enjoy playing lots of video games! I enjoy playing Osu! in particular!";
    }         
    //////////////////////////////
    else if (findKeyword(statement, "I want", 0) >= 0)
    {
      response = transformIWantStatement(statement);
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
    int psnOfYou = findKeyword (statement, "you", 0);
    int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
    String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
    return "What makes you think that I " + restOfStatement + " you?";
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
  
  private String getRandomResponse()
  {
    final int NUMBER_OF_RESPONSES = 6;
    double r = Math.random();
    int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
    String response = "";
    
    if (whichResponse == 0)
    {
      response = "Interesting, tell me more.";
    }
    else if (whichResponse == 1)
    {
      response = "Hmmm.";
    }
    else if (whichResponse == 2)
    {
      response = "Do you really think so?";
    }
    else if (whichResponse == 3)
    {
      response = "You don't say.";
    }
    else if(whichResponse == 4)
    {
      response = "Wow, that sounds great!";
    }
    else if(whichResponse == 4)
    {
      response = "No way!";
    }
    return response;
  }
}
