# 二分
#### 两个升序数组中第m小的数

​	
```c++
int MthSmallest(long a[], long b[], int len, long M){//the M-th smallest
    long lo1 = 0, lo2 = 0, hi1 = len, hi2 = len, mid1, mid2;
    long count = 0; //to check whether it is the M-th smallest 
    while(lo1!=hi1 || lo2!=hi2){
        mid1 = lo1 + (hi1 - lo1) / 2;
        mid2 = lo2 + (hi2 - lo2) / 2;
        if(a[mid1] == b[mid2])
            return a[mid1];
        else if(a[mid1] < b[mid2]){  
            hi2 = mid2;
            if((hi1 - lo1) % 2) mid1++;         
            lo1 = mid1;
        }else{                                                 //
            hi1 = mid1;
            if((hi2 - lo2) % 2) mid2++;
            lo2 = mid2;
        }
        if(M == count) return a[mid1];
    }
    return -1;
}
```



#### n个升序数组中第m小的数



```c++
while(lo <= hi){
	ll mid = lo + (hi - lo)/2;
    if(BinarySearch(mid, M, N)){ 
    	res = mid;
        hi = mid - 1;
    }else lo = mid + 1;
}
bool BinarySearch(ll mid, ll M, ll N){
    ll cnt = 0;
    for(int j = 1; j <= N; j++){
        int lo = 1, hi = N, res = 0;
        while(lo <= hi){
            int i = lo + (hi - lo) / 2;
            if(matrix(i, j) <= mid){
                res = i;
                lo = i + 1;
            }
            else hi = i - 1;
        }
        cnt += res;
    }
    return cnt >= M;
}
```





# 字符串

