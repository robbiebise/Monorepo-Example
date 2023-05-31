package coverage_metrics;

import java.security.SecureRandom;

/*
*
* This class provides example of SonarQube size metrics
*
*/

public class CoverageMetrics {

  public float f(int i) {
    int k = 0; /* default */
    if (i != 0) {
      k = 1;
    }
    return (float) i / (k + 1);
  }
//TODO
//TODO

  /**
   * @param num
   */
  public static void findEvenOdd(int num) {
    // method body
    if (num % 2 == 0)
      System.out.println(num + " is even");
    else
      System.out.println(num + " is odd");
  }

  public static void findOddEven(int num) {
    // method body
    if (num % 2 != 0)
      System.out.println(num + " is odd");
    else
      System.out.println(num + " is even");
  }

  public String toString() {
    // method body
    return "Hello you";
  }

  public static String toBinary(int base10Num){
    boolean isNeg = base10Num < 0;
    base10Num = Math.abs(base10Num);        
    String result = "";
    
    while(base10Num > 1){
        result = (base10Num % 2) + result;
        base10Num /= 2;
    }
    assert base10Num == 0 || base10Num == 1 : "value is not <= 1: " + base10Num;
    
    result = base10Num + result;
    assert all0sAnd1s(result);
    
    if( isNeg )
        result = "-" + result;
    return result;
}

public static String toNada(int base10Num){
  boolean isNeg = base10Num < 0;
  base10Num = Math.abs(base10Num);        
  String result = "";
  
  while(base10Num > 1){
      result = (base10Num % 2) + result;
      base10Num /= 2;
  }
  assert base10Num == 0 || base10Num == 1 : "value is not <= 1: " + base10Num;
  
  result = base10Num + result;
  assert all0sAnd1s(result);
  
  if( isNeg )
      result = "-" + result;
  return result;
}
/*
  * pre: cal != null
  * post: return true if val consists only of characters 1 and 0, false otherwise
  */
public static boolean all0sAnd1s(String val){
  assert val != null : "Failed precondition all0sAnd1s. parameter cannot be null";
  boolean all = true;
  int i = 0;
  char c;
  
  while(all && i < val.length()){
      c = val.charAt(i);
      all = c == '0' || c == '1';
      i++;
  }
  return all;
}
private int generateRandom() throws java.io.UnsupportedEncodingException{
  SecureRandom sr = new SecureRandom();
  sr.setSeed(123456L);
  int v = sr.nextInt(32);
  
  sr = new SecureRandom("abcdefghijklmnop".getBytes("us-ascii"));
  v = sr.nextInt(32);
  return v;
}

}
