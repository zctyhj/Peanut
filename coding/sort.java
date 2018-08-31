public void sort{
	public static void main(String args[]){
		int a[] = {1,4,2,3,6,5,4,8,7,9};
		for(int i =0;i<a.length;i++){
			int temp = a[i];
			int j = i;
			while(j>0 && a[j-1]>temp){
				a[j] = a[j-1];
				j--;
			}
			a[j] = temp;
		}
		for(int i=0;i<a.length;i++){
			System.out.print(" "+a[i]);
		}
	}
}
