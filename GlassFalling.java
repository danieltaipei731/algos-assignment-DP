/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    if(sheets == 1){
        return floors;
    }
    if(floors == 0){
        return 0;
    }
    int min = Integer.MAX_VALUE;
    for(int i = 1; i <= floors; i++){
        int val = 1 + Math.max(glassFallingRecur(i-1,sheets-1),glassFallingRecur(floors-i,sheets));
        if(val < min){
            min = val;
        }
    }
    return min;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets, int Trials[][]) {
    
    if(sheets == 1){
        return floors;
    }
    if(floors == 0){
        return 0;
    }
    if(Trials[sheets][floors] != 0){
        return Trials[sheets][floors];
    }else{
        
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= floors; i++){
            int val = 1 + Math.max(glassFallingMemoized(i-1,sheets-1,Trials),glassFallingMemoized(floors-i,sheets,Trials));
            if(val < min){
            min = val;
            }
            Trials[sheets][floors] = min;
        }
        return min;
    }
  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
      
    int Trials[][] = new int[sheets+1][floors+1];
    
    for(int i = 0; i <= floors; i++){
        Trials[1][i] = i;
    }
    
    for(int s = 2; s <= sheets; s++){
        for(int f = 1; f <= floors; f++){
            Trials[s][f] = Integer.MAX_VALUE;
            for(int t = 1; t <= f; t++){
                int val = 1 + Math.max(Trials[s-1][t-1], Trials[s][f-t]);
                if(val < Trials[s][f]){
                    Trials[s][f] = val;
                }
            }
        }
    }
    return Trials[sheets][floors];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();
      int Trials[][] = new int [4][101];
      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3, Trials);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}