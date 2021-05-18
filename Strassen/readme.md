## 연속 행렬 곱셈(Chained Matrix Multiplications)

연속 행렬 곱셈 문제는 연속된 행렬들의 곱셈에 필요한 원소 간의 최소 곱셈 횟수를 찾는 문제이다.
- 입력 : 연속된 행렬 A1 X A2 X...X An
- 출력 : 입력의 행렬 곱셈에 필요한 원소 간의 최소 곱셈 횟수
-----

## 코드
```java
 for (int i = 1; i <= n; i++) { 
            C[i][i] = 0; // 곱셈 횟수를 나타내는 2차원 배열 C의 대각선을 0으로 초기화
        }

        for (int L = 1; L <= n - 1; L++) {
            for (int i = 1; i <= n-L; i++){
                int j = i+L;
                C[i][j] = Integer.MAX_VALUE; // 최소 곱셈의 수를 찾기위해 C[i][j]를 정수중 가장 큰 수로 초기화
å
                for(int k = i; k<= i-1; k++){
                    int temp = C[i][k] + C[k+1][j] + d[i-1]*d[k]*d[j]; // 두 행렬을 곱했을 떄 나오는 곱셈 횟수
                    if(temp < C[i][j]) C[i][j] = temp; // 곱셈 횟수가 바로 직전 까지의 C[i][j]보다 작으면 그 값으로 C[i][j]를 갱신
                }
            }
        }
```

### 시간 복잡도와 성능
<img width = "723" height = "450" alt = "graph" src = "https://user-images.githubusercontent.com/81741589/116212808-30202e80-a780-11eb-8214-dbb5cac200b9.png">
2차원 배열 C의 원소의 개수가 n^2이므로 연산 수행 횟수는 대략 n^2/2가 되고, k루프가 최대 n-1번 수행될 수 있으므로 시간 복잡도는<br>
O(n^2) * O(n) = O(n^3)

## 문제점
곱셈 연산을 수행해야하는 행렬의 개수가 많아질수록 O(n^3)의 성능은 효율적이지 않다.

## 스트라센(strassen)알고리즘
- 컴퓨터의 구조상 곱하기 연산보다 더하기 연산이 빠르기 때문에 이 방법을 착안
- 일반적인 행렬의 곱은 8번의 곱셈과 4번의 덧셈으로 연산이 되지만, 스트라센 알고리즘은 7번의 곱셈과 10번의 덧셈으로 연산이 된다.
- O(n^2.81)의 성능을 갖는다.
----

## 방법
연속 행렬 곱셈 방식에서는 8번의 곱셈과 4번의 덧셈이 필요하다<br><br>
<img src ="https://t1.daumcdn.net/cfile/tistory/216A1B365818B6470D"><br><br>
스트라센 방식으로는 7번의 곱셈과 18번의 덧셈으로 해를 구할 수 있다.<br><br>
<img src = "http://yimoyimo.tk/images/strassen3.png">


### 분할정복(Divide and Conquer) 이용
<img src = "http://yimoyimo.tk/images/strassen2.png">
> 행렬 C는 A와 B의 연산으로 이루어지며 행렬 A와 B는 2n x 2n의 크기를 지닌다. 만일 크기가 2n x 2n의 크기가 아니라면 빈자리를 0으로 채워 2n x 2n꼴로 만든다.<br>
> A,B 행렬을 각각 4개씩 8개의 부분 행렬로 분할한 후 스트라센 알고리즘을 수행한다.

## 코드

### main

```java
 public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 행렬 원소의 개수

        int[][] matrix1 = new int[n][n]; initMatrix(matrix1); // 행렬1
        int[][] matrix2 = new int[n][n]; initMatrix(matrix2); // 행렬2

        int[][] strassenResult = startStrassen(matrix1, matrix2);

        for(int i =0; i <strassenResult.length; i++){
            for(int j=0; j < strassenResult.length; j++){
                System.out.print(strassenResult[i][j]+" ");
            }
            System.out.println();
        }
    }
```
### 스트라센 실행
```java
private static int[][] startStrassen(int[][] matrix1, int[][] matrix2){
        int x = matrix1.length;
        // 행렬 원소의 개수가 4(2*2)이면 연속 행렬 곱셈 수행
        if(x <= 2){
            return matrixMul(matrix1, matrix2);
        }

        int num = x/2;

        // 분할
        int[][] a11 = divMatrix(num, 0, 0, matrix1);
        int[][] a12 = divMatrix(num, 0, num, matrix1);
        int[][] a21 = divMatrix(num, num, 0, matrix1);
        int[][] a22 = divMatrix(num, num, num, matrix1);

        int[][] b11 = divMatrix(num, 0, 0, matrix2);
        int[][] b12 = divMatrix(num, 0, num, matrix2);
        int[][] b21 = divMatrix(num, num, 0, matrix2);
        int[][] b22 = divMatrix(num, num, num, matrix2);

        int[][] m1 = startStrassen(matrixSum(a11, a22), matrixSum(b11, b22));
        // m1 = (a11 + a11)(b11 + b22)
        int[][] m2 = startStrassen(matrixSum(a21, a22),b11);
        // m2 = (a21 + a22)b11
        int[][] m3 = startStrassen(a11, matrixSub(b12, b22));
        // m3 = a11(b12-b22)
        int[][] m4 = startStrassen(a22, matrixSub(b21, b11));
        // m4 = a22(b21-b11)
        int[][] m5 = startStrassen(matrixSum(a11, a12), b22);
        // m5 = (a11 + a12)b22
        int[][] m6 = startStrassen(matrixSub(a21, a11), matrixSum(b11, b12));
        // m6 = (a21-a11)(b11 + b22)
        int[][] m7 = startStrassen(matrixSub(a12, a22), matrixSum(b21, b22));
        // m7 = (a12-a22)(a21 + b22)

        
        int[][] c11 = matrixSum(matrixSub(matrixSum(m1, m4), m5), m7);
        // c11 = m1+m4 -m5+m7
        int[][] c12 = matrixSum(m3, m5);
        // c12 = m3 +m5
        int[][] c21 = matrixSum(m2, m4);
        // c21 = m2 + m4
        int[][] c22 = matrixSum(matrixSub(matrixSum(m1,m3), m2), m6);
        // c22 = m1+m3-m2+m6

        //정복
        return conquer(c11, c12, c21, c22);
    }

```
### 행렬 초기화
```java
    private static void initMatrix(int[][] matrix){ // 행렬 초기화
        Random r = new Random();

        for(int i =0; i < n; i++){
            for(int j = 0;j < n; j++){
                matrix[i][j] = r.nextInt(20)+1;
            }
        }
    }
```
### 연속 행렬 곱셈
```java
private static int[][] matrixMul(int[][] matrix1, int[][] matrix2){
        int[][] result = new int[n][n];
        for(int i =0; i < n; i++){
            for(int j =0; j < n; j++){
                for(int k = 0;k <n; k++){
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }
```
### 분할
```java
 private static int[][] divMatrix(int n, int start1, int start2, int[][] matrix){
        int[][] subMatrix = new int[n][n];

        for(int i =0, x = start1; i < n; i++, x++){
            for(int j = 0, y = start2; j< n; j++, y++){
                subMatrix[i][j] = matrix[x][y];
            }
        }
        return subMatrix;
    }
```
### 행렬 덧셈 뺄셈
```java
private static int[][] matrixSum(int[][] matrix1, int[][] matrix2){
        int n = matrix1.length;
        int[][] matrixResult = new int[n][n];

        for(int i =0; i <n; i++){
            for(int j = 0; j<n; j++){
                matrixResult[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return matrixResult;
    }

 private static int[][] matrixSub(int[][] matrix1, int[][] matrix2){
        int n = matrix1.length;
        int[][] matrixResult = new int[n][n];

        for(int i =0; i <n; i++){
            for(int j = 0; j<n; j++){
                matrixResult[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return matrixResult;
    }

```
### 정복
```java
private static int[][] conquer(int[][] c11, int[][] c12, int[][] c21, int[][] c22){
        int n =c11.length;
        int[][] result = new int[n*2][n*2];

        for(int i = 0; i <n; i++){
            for(int j =0; j < n; j++){
                result[i][j] = c11[i][j];
                result[i][j+n] = c12[i][j];
                result[i+n][j] = c21[i][j];
                result[i+n][j+n] = c22[i][j];
            }
        }
        return result;
    }
```

## 시간복잡도와 성능
곱셈 연산을 줄이고 수행시간이 더 빠른 덧셈연산을 늘렸기 때문에 시간복잡도는 O(n^3)보다 작은 O(n^2.81)이다. 
- 빨간색 라인 : 스트라센 알고리즘
- 초록색 라인 : 연속 행렬 곱셈

<img width = "723" height = "450" alt = graph src = "https://user-images.githubusercontent.com/81741589/116213087-72497000-a780-11eb-9314-0885133524d4.png">