/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
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
    String statement = statementIn.toLowerCase(); // ADDED IN ACTIVITY 2
    if (statement.indexOf("no") >= 0)
    {
      response = "Why so negative?";
    }
    else if (statement.indexOf("mother") >= 0
               || statement.indexOf("father") >= 0
               || statement.indexOf("sister") >= 0
               || statement.indexOf("brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    ////////ACTIVITY 2///////////
    else if(statement.indexOf("cat") >= 0
              || statement.indexOf("dog") >= 0
              || statement.indexOf("pet") >= 0
              || statement.indexOf("fish") >= 0)
    {
      response = "Tell me more about your pets.";
    }
    else if(statement.indexOf("kiang") >= 0
              || statement.indexOf("landgraf") >= 0)
    {
      response = "I've heard about that guy! He's a pretty smart and swell person!";
    }
    else if(statement.trim().length() < 1)
    {
      response = "Say somthing, please";
    }
    else if(statement.indexOf("hungry") >= 0
              || statement.indexOf("food") >= 0)
    {
      response = "What foods do you enjoy?";
    }
    else if(statement.indexOf("school") >= 0
              || statement.indexOf("grades") >= 0
              || statement.indexOf("gpa") >= 0)
    {
      response = "Sounds difficult. It'll all be worth it in the end, though!";
    }
    else if(statement.indexOf("video games") >= 0
              || statement.indexOf("games") >= 0
              || statement.indexOf("controller") >= 0)
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
