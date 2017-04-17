package hihocoder.q1121;

/**
 * Created by zhuyuchao on 2017/4/17.
 */
import java.util.*;

public class Main{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(--t>=0){
            System.out.println(getResult(scan));
        }
    }

    public static String getResult(Scanner scan){
        int n = scan.nextInt();
        int m = scan.nextInt();
        Map<Integer,List<Integer>> graph = new HashMap(){};
        int[] mark = new int[n+1];
        for(int i=0;i<m;i++){
            Integer t1 = new Integer(scan.nextInt());
            Integer t2 = new Integer(scan.nextInt());
            if(graph.containsKey(t1)){
                graph.get(t1).add(t2);
            }else{
                List<Integer> temp = new ArrayList(){};
                temp.add(t2);
                graph.put(t1,temp);
            }
            if(graph.containsKey(t2)){
                graph.get(t2).add(t1);
            }else{
                List<Integer> temp = new ArrayList(){};
                temp.add(t1);
                graph.put(t2,temp);
            }
        }
        boolean end = false;
        while(!end){
            int temp = 0;
            while((++temp)<=n&&mark[temp]!=0){}
            if(temp==n+1){
                end = true;
                break;
            }
            mark[temp] = -1;
            int[] queue = new int[n];
            int head = 0;
            int tail = 1;
            queue[head] = temp;
            while(head<tail){
                int node = queue[head];
                int gender = mark[node];
                List<Integer> nodes = graph.get(node);
                for(Integer j:nodes){
                    if(mark[j]==0){
                        mark[j] = -gender;
                        queue[tail++] = j;
                    }else if(mark[j]==gender){
                        return "Wrong";
                    }
                }
                head++;
            }
        }
        return "Correct";
    }
}
