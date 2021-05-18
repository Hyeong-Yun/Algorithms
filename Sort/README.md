# sorting algorithm
-----
## bubble sort
버블 정렬(Bubble Sort)은 이웃하는 숫자를 비교하여 작은 수를 앞쪽으로 이동시키는 과정을 반복하여 정렬하는 알고리즘이다.

### code
```java
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
```
## result
![bubble](https://user-images.githubusercontent.com/81741589/117104062-709e2e80-adb6-11eb-8651-95f839c82987.PNG)

## Selection Sort
선택 정렬(Selection Sort)은 입력 배열 전체에서 최솟값을 '선택'하여 배열의 0번 원소와 자리를 바꾸고, 다음에는 0번 원소를 제외한 나머지 원소에서 최솟값을 선택하여, 배열의 1번 원소와 자를 바꾼다. 이러한 방식으로 마지막에 2개의 원소 중에서 작은 값을 선택하여 자리를 바꿈으로써 오름차순 정렬을 마친다.

### code
```java
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

```
## result
![Selection](https://user-images.githubusercontent.com/81741589/117104505-38e3b680-adb7-11eb-90ce-b7b7bf9036b3.PNG)

## Insertion Sort
삽입 정렬(Insertion Sort)은 배열을 정렬된 부분(앞부분)과 정렬이 안 된 부분(뒷부분)으로 나누고, 정렬이 안 된 부분의 가장 왼쪽 원소를 정렬된 부분의 적절한 위치에 삽입하여 정렬되도록 한느 과정을 반복한다.

### code
```java
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
```
## result
![insertion](https://user-images.githubusercontent.com/81741589/117104571-57e24880-adb7-11eb-922b-27aefb9c30ef.PNG)

## Shell Sort
쉘 정렬(Shell sort)은 삽입 정렬을 이용하여 배열 뒷부분의 작은 숫자를 앞부분으로 빠르게 이동시키고, 동시에 앞부분의 큰 숫자는 뒷부분으로 이동시키며, 가장 마지막에는 삽입 정렬을 수행한다.

### code
```java
// 삽입정렬 for shell sort
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
// 쉡 정렬
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
```
## result
![Shell](https://user-images.githubusercontent.com/81741589/117104631-75afad80-adb7-11eb-986c-1e8295aa0120.PNG)

## 정렬 알고리즘 시간 복잡도

|정렬|worst|avg|best|
|----|---|---|---|
|버블|O(n^2)|O(n^2)|O(n^2)|
|선택|O(n^2)|O(n^2)|O(n^2)|
|삽입|O(n^2)|O(n^2)|O(n)|
|쉘|O(n^2)|O(n^1.5)|O(n)|

## 알고리즘 수행 시간 비교

|n|1000|10000|100000|
|:---:|:---:|:---:|:---:|
|버블|0.004(sec)|O.119(sec)|15.684(sec)|
|선택|O.003(sec))|O.051(sec)|4.718(sec)|
|삽입|0.002(sec)|0.014(sec)|0.835(sec)|
|쉘|0.001(sec)|O.002(sec)|O.011(sec)|





