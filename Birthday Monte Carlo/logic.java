import java.util.ArrayList;
import java.util.Random;

public class logic {
    public double probabilityOfSameBirthday(int studentsAmt, int iterations){
        Random rand = new Random();
        int count = 0;
        ArrayList<Integer> studentArr = new ArrayList<>();
        for(int i = 0;i<=studentsAmt;i++){
            studentArr.add(rand.nextInt(1,365));
        }
        for(int j = 0;j<studentsAmt;j++){
            for(int k = j+1;k<studentsAmt;k++){
                if(studentArr.get(j).equals(studentArr.get(k))){
                    count++;
                    break;
                }
            }
        }

        return count;

    }
}
