import java.util.*;
public class Sort {
   private static void bubbleSort(int[] arr){
       int n = arr.length;

    for(int i =0; i < n; i++){
        for(int j = 0; j <n-(i+1);j++ ){
            if(arr[j+1] < arr[j]){
                int tmp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = tmp;
            }
        }
    }
   }
   private static void insertionSort(int[] arr){
       int n = arr.length;
    int i, j;
       for(i =1; i < n; i++){
           int key = arr[i];

           j = i -1;
           while(j>=0 && arr[j] > key){
                arr[j+1] = arr[j];
                j--;
           }

           arr[j+1] = key;
       }
   }

   private static void selectionSort(int[] arr){
       int n = arr.length;

        for(int i = 0; i < n-1; i++){
            int min = i;
            for(int j = i+1; j<n; j++){
                if(arr[min] > arr[j]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
   }

   private static void ShellinsertionSort(int[] arr, int start, int end, int n){
        for(int i =start+n; i <= end; i = i+n){
            int key = arr[i];
            int j;
            for(j = i-n; j >=start && arr[j] > key; j = j-n){
                arr[j+n] = arr[j];
            }
            arr[j+n] = key;
        }
    }

    private static void shellSort(int[] arr){
       int n =arr.length;
       int gap;
       for(gap = n/2; gap > 0; gap = gap/2){
           if(gap%2 == 0){
               gap++;
           }
           for(int i =0 ; i < gap; i++){
               ShellinsertionSort(arr, i, n-1, gap);
           }
       }
    }
    public static void main(String args[]){
      Scanner sc = new Scanner(System.in);
      Random r = new Random();
        int n;
        n = sc.nextInt();

        int[] arr = new int[n];
        for(int i =0; i < n; i++){
            arr[i] = r.nextInt(100)+1;
        }

        System.out.println();

        long beforeTime = System.currentTimeMillis();

        insertionSort(arr);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);

        System.out.println();
    }
}
