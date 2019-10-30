import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Run {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrays = new int[]{3,4,1,5,6,2,7};
        solution.findTwoSideMInValuePosition(arrays);

    }

    public List<Integer[]> findTwoSideMInValuePosition(int[] array){
        ArrayList<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            result.add(new Integer[2]);
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            handle(array,i,stack,result.get(i),0);
        }
        stack.clear();
        for (int i = array.length-1; i >-1; i--) {
            handle(array,i,stack,result.get(i),1);
        }
        return result;
    }

    private void handle(int[] array,int arrayIndex,LinkedList<Integer> stack,Integer[] positions,int positionIndex){
        while (!stack.isEmpty() && array[stack.peek()]>=array[arrayIndex]){
            stack.pop();
        }
        if(stack.isEmpty()){
            positions[positionIndex] = -1;
        }else{
            positions[positionIndex]= stack.peek();
        }
        stack.push(arrayIndex);
    }
}
