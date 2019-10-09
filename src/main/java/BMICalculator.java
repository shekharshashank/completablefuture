import java.util.concurrent.CompletableFuture;

public class BMICalculator {

    public CompletableFuture<Double> getWeight(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("weight is getting calculated");
            return 83.0;

        }  );
    }

    public CompletableFuture<Integer> getHeight(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("Height is getting calculated");
            return 181;
        });

    }

    public CompletableFuture<Double> calulateBMI(){
        return  getWeight().thenCombine(getHeight(),(weightInKg,heightInCm)->{
            Double heightInMeter = (double)heightInCm/100;
            return  weightInKg/(heightInMeter*heightInMeter);

        });
    }

    public static void main(String [] args){
        BMICalculator bmic = new BMICalculator();
        CompletableFuture<Double> bmiFuture =  bmic.calulateBMI();
        try {
            System.out.println("bmi is =>"+bmiFuture.get());
        }catch (Exception e){
            System.err.println(e);
        }

    }
}
