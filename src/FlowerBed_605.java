public class FlowerBed_605 {
	
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int max = canPlaceFlower(flowerbed, flowerbed.length-1);
        return n<=max;
    }
    
    private int canPlaceFlower(int[] flowerbed, int i) {
        if(i < 0) {
            return 0;
        }
        
        if(flowerbed[i] == 1 || (i-1 >= 0 && flowerbed[i-1] == 1) || (i+1<flowerbed.length && flowerbed[i+1] ==1)) {
            return canPlaceFlower(flowerbed, i-1);
        }
        
        if( i == 0) {
            return 1;
        }
        
       return Math.max(1+ canPlaceFlower(flowerbed, i-2), canPlaceFlower(flowerbed, i-1));
    }
	public static void main(String[] args) {
		FlowerBed_605 obj = new FlowerBed_605();
		int[] flowerbed = {1,0,0,0,1};
		System.out.println(obj.canPlaceFlowers(flowerbed, 2));

	}

}
