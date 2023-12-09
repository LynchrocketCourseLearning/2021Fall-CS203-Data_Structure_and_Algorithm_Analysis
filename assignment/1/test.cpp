#include <iostream>
#define ll long long

using namespace std;
const int N = 1e5+10 ;
const ll mod = 998244353;

int a[N],b[N],h,n;
ll suma, sumb;
int maxa,mina,maxb,minb;
bool check(int mid)
{
	ll res = suma - sumb + mid - maxa - mina + maxb + minb;
	if(res > 0) return 1;
	res =  suma - sumb + minb - maxa;
	return res > 0;
}
int main() {

    ios::sync_with_stdio(0),cin.tie(0),cout.tie(0);

    cin >> n >> h;

    maxa = 1, mina = h, maxb = 1, minb = h;
    for(int i = 1; i < n; i++)
	{
		cin >> a[i];
		suma += a[i];
		maxa = max(maxa,a[i]);
		mina = min(mina,a[i]);
	}
    for(int i = 1; i < n; i++)
	{
		cin >> b[i];
		sumb += b[i];
		maxb = max(maxb,b[i]);
		minb = min(minb,b[i]);
	}

    int l = 1-h , r = h-1, mid;
    while(l < r)
	{
		mid = (l+r)>>1;
		if(check(mid))r = mid;
		else l = mid + 1;
	}

    if(check(l))cout << l <<endl;
    else cout << "IMPOSSIBLE" <<endl;


    return 0;
}