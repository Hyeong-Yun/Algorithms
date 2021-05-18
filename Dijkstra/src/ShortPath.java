import java.util.Scanner;
import java.util.Random;

class Graph{
    private int n;
    private int maps[][];
    public int[][] getMaps(){
        return maps;
    }
    Graph(int n){
        this.n = n;
        maps = new int[n+1][n+1];
    }
    void input(int i, int j, int v){
        maps[i][j] = v;
        maps[j][i] = v;
    }
    public void dijkstra(int v){
        int dist[] = new int[n+1];
        boolean check[] = new boolean[n+1];

        for(int i =1; i< n+1; i++){
         dist[i] = Integer.MAX_VALUE;
        }
        dist[v] = 0;
        check[v] =true;

        for(int i = 1; i< n+1; i++){
            if(!check[i] && maps[v][i] !=0){
                dist[i] = maps[v][i];
            }
        }

        for(int a = 0; a < n-1; a++){
            int min = Integer.MAX_VALUE;
            int min_index = -1;

            //최소값 찾기
            for(int i = 1; i < n+1; i++){
                if(!check[i] && dist[i] != Integer.MAX_VALUE){
                    if(dist[i] < min){
                        min = dist[i];
                        min_index = i;
                    }
                }
            }

            check[min_index] = true;
            for(int i = 1; i < n+1; i++){
                if(!check[i] && maps[min_index][i] != 0){
                    if(dist[i] > dist[min_index] + maps[min_index][i]){
                        dist[i] = dist[min_index] + maps[min_index][i];
                    }
                }
            }
        }

        for(int i =1 ;i <n+1; i++){
            System.out.println(i+"까지의 최단거리:"+dist[i]);
        }
        System.out.println("");
    }
}


public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("정점의 개수 : ");
        int n = sc.nextInt();
        Graph g = new Graph(n);

        int t = (n-1)+rand.nextInt(10);
        int maps[][] = new int[n+1][n+1];
        int cnt[] = new int[n+1];

        //노드에 weight  넣기
        for(int k =0; k<= t; k++){
            int i =rand.nextInt(n)+1, j = rand.nextInt(n)+1, v= rand.nextInt(20)+1;
            maps = g.getMaps();

            while(maps[i][j] == 0 && i != j) {
                System.out.println(i + " " + j + " " + v);
                cnt[i]++; cnt[j]++;
                g.input(i, j, v);
            }
        }

        //간선이 없는 노드가 존재할 경우
        for(int i=1; i <n+1; i++){
            if(cnt[i] == 0){
                int j = rand.nextInt(n)+1;

                while(i == j){
                    j = rand.nextInt(n)+1;
                }

                int w = rand.nextInt(10)+1;
                System.out.println(i + " " + j + " " + w);
                g.input(i, j, w);

            }
        }

        System.out.println("-------------------");
        g.dijkstra(1);
       
       
    }
}