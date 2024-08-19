#include <iostream>
#define ll long long
using namespace std;

ll arr[100010], L[100010], R[100010];
ll merge(ll arr[], int low, int high){
    ll cnt = 0;
    if(low < high){
        
        int mid = low + (high - low)/2;

        cnt += merge(arr, low, mid)+merge(arr, mid+1, high);

        int i = 0, j = 0, k = 0;//i -> L; j -> R; k -> arr

        int Llen = mid - low + 1;
        int Rlen = high - mid;

        i = 0; k = low;
        while(i < Llen) L[i++] = arr[k++];
        i = 0; k = mid+1;
        while(i < Rlen) R[i++] = arr[k++];

        i = 0, j = 0, k = low;
        
        while(i < Llen && j < Rlen){
            if(L[i] < R[j]){
                arr[k++] = L[i++];
            } else {
                cnt += (Llen - i) * R[j];
                arr[k++] = R[j++];
            }
        }
        

        while(i < Llen) arr[k++] = L[i++];
        while(j < Rlen) arr[k++] = R[j++];
        
    }
    return cnt;
}

int main()
{
    ios::sync_with_stdio(0),cin.tie(0),cout.tie(0);
    int n;
    cin >> n;
    for(int i = 0; i < n; i++) cin >> arr[i];
    cout << merge(arr, 0, n-1) << endl;

    //for(int i = 0; i < n; i++) cout << q[i]<<" ";

    return 0;
}