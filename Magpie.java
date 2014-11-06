public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk.";
  }
  
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  public String getResponse(String statementIn)
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
    else
    {
      response = getRandomResponse();
    }
    return response;
  }
  
  private int findKeyword(String statement, String goal, int startPos)
  {
    String phrase = statement.trim();
    // The only change to incorporate the startPos is in
    // the line below
    int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
    // Refinement--make sure the goal isn't part of a
    // word
    while (psn >= 0)
    {
      // Find the string of length 1 before and after
      // the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring(psn - 1, psn).toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(psn + goal.length(),psn + goal.length() + 1).toLowerCase();
      }
      // If before and after aren't letters, we've
      // found the word
      if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
            && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
      {
        return psn;
      }
      // The last position didn't work, so let's find
      // the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(),psn + 1);
    }
    return -1;
  }
  private int findKeyword(String statement, String goal)
  {
    return findKeyword(statement, goal, 0);
  }
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
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
