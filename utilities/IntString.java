/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
import java.io.*;

public class IntString
{
  public static void main(String[] args) throws Exception
  {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String s = in.readLine();
    System.out.println(new IntString().stringFromInts(s));
  }

  public String stringFromInts(String input) throws Exception
  {
    input = input.substring(1,input.length()-1);
    StringBuilder builder = new StringBuilder();
    String[] stringInts = input.split(", ");
    int nullCharsFound = 0;
    for(String stringInt : stringInts)
    {
      byte b = new Integer(stringInt).byteValue();

      if(b == 0x00)
      {
        if(nullCharsFound > 1)
        {
          break;
        }
        nullCharsFound++;
        builder.append("{NULL}");
      }
      else
      {
        nullCharsFound = 0;
      }

      builder.append((char)b);
    }
    return builder.toString();
  }
}
